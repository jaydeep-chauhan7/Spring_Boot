package com.infy.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.ProjectDTO;
import com.infy.dto.TeamMemberDTO;
import com.infy.exception.AbcException;
import com.infy.repository.ProjectRepository;
import com.infy.validator.Validator;

@Service(value="projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public Integer addProject(ProjectDTO project) throws AbcException {
		List<TeamMemberDTO> list=project.getMemberList();
		for(TeamMemberDTO t:list) {
			Validator.validate(t);
		}
		int i=projectRepository.addProject(project);
		return i;
	}


	
	@Override
	public List<ProjectDTO> getProjectDetails(String technology) throws AbcException {
		List<ProjectDTO> list=projectRepository.getProjectDetails();
		List<ProjectDTO> filter=new ArrayList<ProjectDTO>();
		for(ProjectDTO p:list) {
			if(p.getTechnologyUsed() == technology) {
				filter.add(p);
			}
		}
		if(filter.isEmpty()) {
			throw new AbcException("Service.PROJECTS_NOT_FOUND");
		}
		return filter;
	}


	
}
