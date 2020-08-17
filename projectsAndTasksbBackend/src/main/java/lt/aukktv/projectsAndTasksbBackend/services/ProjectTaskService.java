package lt.aukktv.projectsAndTasksbBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.aukktv.projectsAndTasksbBackend.domain.Backlog;
import lt.aukktv.projectsAndTasksbBackend.domain.ProjectTask;
import lt.aukktv.projectsAndTasksbBackend.exceptions.ProjectNotFoundException;
import lt.aukktv.projectsAndTasksbBackend.repositories.BacklogRepository;
import lt.aukktv.projectsAndTasksbBackend.repositories.ProjectRepository;
import lt.aukktv.projectsAndTasksbBackend.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectService projectService;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
		// Exceptions: project not found

		// PTs to be added to a specific project, project != null, BL exist
		Backlog backlog = projectService.findByProjectIdentifier(projectIdentifier, username).getBacklog();
		// = backlogRepository.findByProjectIdentifier(projectIdentifier);

		// set the BL to PT
		projectTask.setBacklog(backlog);

		// we want our PT sequence to be like this: IDPRO-1, IDPRO-2 ... 10
		Integer backlogSequence = backlog.getPTSequence();

		// Update the BL sequence
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);

		// add sequence to PT
		projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);

		// initial priority when priority null
		if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
			projectTask.setPriority(3);
		}

		// initial status when status null
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}

		return projectTaskRepository.save(projectTask);

	}

	public Iterable<ProjectTask> findBacklogById(String id, String username) {

		projectService.findByProjectIdentifier(id, username);

		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}

	public ProjectTask findPTByPTSequence(String backlog_id, String pt_id, String username) {

		// make sure we are searching on an existing backlog
		projectService.findByProjectIdentifier(backlog_id, username);

		// make sure that our task exsist
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if (projectTask == null) {
			throw new ProjectNotFoundException("Project task with identifier " + pt_id + " does not exist");
		}

		// make sure that the backlog/project id in the path corresponds to the right
		// project
		if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project task " + pt_id + " does not exist in project " + backlog_id);
		}

		return projectTask;
	}

	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id,
			String username) {
		ProjectTask projectTask = findPTByPTSequence(backlog_id, pt_id, username);

		projectTask = updatedTask;

		return projectTaskRepository.save(projectTask);
	}

	public void deletePTByPTSequence(String backlog_id, String pt_id, String username) {
		ProjectTask projectTask = findPTByPTSequence(backlog_id, pt_id, username);

		projectTaskRepository.delete(projectTask);

	}
}
