package lt.aukktv.projectsAndTasksbBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.aukktv.projectsAndTasksbBackend.domain.Backlog;
import lt.aukktv.projectsAndTasksbBackend.domain.ProjectTask;
import lt.aukktv.projectsAndTasksbBackend.repositories.BacklogRepository;
import lt.aukktv.projectsAndTasksbBackend.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

		// Exceptions: project not found

		// PTs to be added to a specific project, project != null, BL exist
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

		// set the BL to PT
		projectTask.setBacklog(backlog);

		// we want our PT sequence to be like this: IDPRO-1, IDPRO-2 ... 10
		Integer backlogSequence = backlog.getPTSequence();

		// Update the BL sequence
		backlogSequence++;

		// add sequence to PT
		projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);

		// initial priority when priority null
//		if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
//			projectTask.setPriority(3);
//		}

		// initial status when status null
		if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}

		return projectTaskRepository.save(projectTask);
	}
}
