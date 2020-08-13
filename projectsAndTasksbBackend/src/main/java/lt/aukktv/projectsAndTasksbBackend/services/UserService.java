package lt.aukktv.projectsAndTasksbBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.aukktv.projectsAndTasksbBackend.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

}
