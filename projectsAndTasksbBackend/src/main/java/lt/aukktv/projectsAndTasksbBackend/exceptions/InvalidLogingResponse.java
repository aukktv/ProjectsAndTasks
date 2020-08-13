package lt.aukktv.projectsAndTasksbBackend.exceptions;

public class InvalidLogingResponse {

	private String username;
	private String password;

	public InvalidLogingResponse() {
		this.username = "Invalid Username";
		this.password = "Invalid Password";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
