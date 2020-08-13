package lt.aukktv.projectsAndTasksbBackend.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.aukktv.projectsAndTasksbBackend.domain.User;
import lt.aukktv.projectsAndTasksbBackend.services.MapValidationErrorService;
import lt.aukktv.projectsAndTasksbBackend.services.UserService;
import lt.aukktv.projectsAndTasksbBackend.validator.UserValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		// Validate passwords match
		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		User newUser = userService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

}
