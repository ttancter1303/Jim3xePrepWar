package jim3xe.web.jim3xeprepwar.repository;

import jim3xe.web.jim3xeprepwar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "FROM User WHERE username = :username")
    User getDataByUsername(String username);
    User findAccountByUsername(String username);
    Boolean existsByUsername(String username);
}
