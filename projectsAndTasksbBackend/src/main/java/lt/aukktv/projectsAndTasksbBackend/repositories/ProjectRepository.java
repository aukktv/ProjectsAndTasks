package lt.aukktv.projectsAndTasksbBackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lt.aukktv.projectsAndTasksbBackend.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	
	

}
