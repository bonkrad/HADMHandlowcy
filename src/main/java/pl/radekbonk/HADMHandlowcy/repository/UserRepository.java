package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
