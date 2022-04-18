package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;
import com.infy.repository.MovieRepository;
import com.infy.validator.Validator;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	public String addMovie(MovieDTO movieDTO) throws DreamMakersException {
		Validator.validate(movieDTO);
		String str=movieRepository.addMovie(movieDTO);
		return str;
	}

	public List<MovieDTO> getMovies(String directorName) throws DreamMakersException {
		List<MovieDTO> list=movieRepository.getAllMovies();
		List<MovieDTO> filter=new ArrayList<>();
		for(MovieDTO m:list) {
			if(m.getDirector().getDirectorName()==directorName) {
				filter.add(m);
			}
		}
		if(filter.isEmpty()) {
			throw new DreamMakersException("Service.MOVIE_NOT_FOUND");
		}
		return filter;	
	}
}
