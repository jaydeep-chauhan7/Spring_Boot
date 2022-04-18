package com.infy.validator;

import com.infy.dto.MovieDTO;
import com.infy.exception.DreamMakersException;

public class Validator {

	public static void validate(MovieDTO movieDTO) throws DreamMakersException {
		if(!validateMovie(movieDTO)) {
			throw new DreamMakersException("Validator.INVALID_NAMES");
		}
	}

	public static Boolean validateMovie(MovieDTO movieDTO) {
		if(movieDTO.getDirector().getDirectorName() != null && movieDTO.getMovieName() != null) {
			return true;
		}
		return false;
	}
}
