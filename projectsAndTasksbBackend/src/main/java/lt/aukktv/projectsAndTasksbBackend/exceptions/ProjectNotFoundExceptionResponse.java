package lt.aukktv.projectsAndTasksbBackend.exceptions;

public class ProjectNotFoundExceptionResponse {

	private String projectNotFoundMessage;

	public ProjectNotFoundExceptionResponse(String projectNotFoundMessage) {
		this.projectNotFoundMessage = projectNotFoundMessage;
	}

	public String getProjectNotFoundMessage() {
		return projectNotFoundMessage;
	}

	public void setProjectNotFoundMessage(String projectNotFoundMessage) {
		this.projectNotFoundMessage = projectNotFoundMessage;
	}

}
