package lt.aukktv.projectsAndTasksbBackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.aukktv.projectsAndTasksbBackend.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User getById(Long id);

}
