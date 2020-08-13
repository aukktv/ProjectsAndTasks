package lt.aukktv.projectsAndTasksbBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlredyExistException extends RuntimeException {

	public UsernameAlredyExistException(String message) {
		super(message);

	}

}
