package study.security.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.security.Entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}