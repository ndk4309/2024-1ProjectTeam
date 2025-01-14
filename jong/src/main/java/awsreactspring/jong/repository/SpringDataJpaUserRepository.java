package awsreactspring.jong.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import awsreactspring.jong.domain.SiteUser;

public interface SpringDataJpaUserRepository extends JpaRepository<SiteUser, Long>, UserRepository {

    Optional<SiteUser> findByEmail(String email);

    Optional<SiteUser> findByName(String name);

    Optional<SiteUser> findById(Long id);    

    Optional<SiteUser> findByPhone(String phone);

    List<SiteUser> findByWorker(boolean worker);

    List<SiteUser> findByScore(int score);

    List<SiteUser> findAll();
}
