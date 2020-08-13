package lt.aukktv.projectsAndTasksbBackend.exceptions;

public class UsernameAlredyExistResponse {

	private String username;

	public UsernameAlredyExistResponse(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
