package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.Optional;
// Spring
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}