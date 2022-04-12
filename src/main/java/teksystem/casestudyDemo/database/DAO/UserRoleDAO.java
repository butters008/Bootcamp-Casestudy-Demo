package teksystem.casestudyDemo.database.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystem.casestudyDemo.database.entity.UserRole;

import java.util.List;

/*
 * Boilerplate code
 * */
@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(@Param("user_id") Integer userId);
}
