package filesource.project16.repository;

import filesource.project16.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long>{
	
	SiteUser findAllByusername(String username);
	Optional<SiteUser> findByusername(String username);
	
	
	
}
