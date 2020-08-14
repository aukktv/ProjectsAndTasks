package lt.aukktv.projectsAndTasksbBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.aukktv.projectsAndTasksbBackend.domain.Backlog;
import lt.aukktv.projectsAndTasksbBackend.domain.Project;
import lt.aukktv.projectsAndTasksbBackend.domain.User;
import lt.aukktv.projectsAndTasksbBackend.exceptions.ProjectIdException;
import lt.aukktv.projectsAndTasksbBackend.repositories.BacklogRepository;
import lt.aukktv.projectsAndTasksbBackend.repositories.ProjectRepository;
import lt.aukktv.projectsAndTasksbBackend.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private UserRepository userRepository;

	public Project saveOrUpdateProject(Project project, String username) {
		try {

			User user = userRepository.findByUsername(username);
			project.setUser(user);
			project.setProjectLeader(user.getUsername());
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			if (project.getId() == null) {
				Backlog backlog = new Backlog();
				// set the relationship:
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}

			if (project.getId() != null) {
				project.setBacklog(
						backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}

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

	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project " + projectId + " does not exist");
		}

		projectRepository.delete(project);
	}

}
