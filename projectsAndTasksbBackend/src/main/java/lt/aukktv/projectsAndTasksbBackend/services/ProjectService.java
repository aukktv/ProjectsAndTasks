package lt.aukktv.projectsAndTasksbBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.aukktv.projectsAndTasksbBackend.domain.Project;
import lt.aukktv.projectsAndTasksbBackend.exceptions.ProjectIdException;
import lt.aukktv.projectsAndTasksbBackend.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project identifier " + project.getProjectIdentifier().toUpperCase() + " allredy exist");
		}

	}

	public Project findByProjectIdentifier(String projectId) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project identifier " + projectId + " does not exist");
		}

		return project;
	}

}
