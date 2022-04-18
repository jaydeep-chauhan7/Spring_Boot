package com.infy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.ProjectDTO;
import com.infy.dto.TeamMemberDTO;
import com.infy.service.ProjectService;

@SpringBootApplication
public class AbcToTraineeApplication implements CommandLineRunner{
	
	public static final Log LOGGER = LogFactory.getLog(AbcToTraineeApplication.class);
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	Environment	environment;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AbcToTraineeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		addProject();
		//getProjectDetails();
	}

	public void addProject() {
		TeamMemberDTO t1=new TeamMemberDTO();
		t1.setDesignation("SSC");
		t1.setEmployeeId(722009);
		t1.setEmployeeName("Robin");
		t1.setSkills("Java,Oracle");
		
		List<TeamMemberDTO> list=new ArrayList<>();
		list.add(t1);
		
		ProjectDTO p1=new ProjectDTO();
		p1.setCost(200000);
		p1.setMemberList(list);
		p1.setProjectName("FSADM8");
		p1.setProjectId(5005);
		p1.setTeamSize(5);
		p1.setTechnologyUsed("Java");
		try {
			projectService.addProject(p1);
			LOGGER.info(environment.getProperty("UserInterface.PROJECT_ADDED_SUCCESS" + p1.getProjectId()));
			
		}catch(Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}

	}


	public void getProjectDetails() {
		try {
			List<ProjectDTO> list=projectService.getProjectDetails("Java");
			for(ProjectDTO p:list) {
				System.out.println(p);
			}
			
		}catch(Exception e) {
			LOGGER.error(environment.getProperty(e.getMessage()));
		}
	}

}