package teksystem.casestudyDemo.database.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystem.casestudyDemo.database.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmail(@Param("email") String email);

    public List<User> findByFirstName(@Param("firstName") String firstName);

    public List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT u FROM User u WHERE u.password = :password")
    public List<User> getByPassword(@Param("password") String password);

}
