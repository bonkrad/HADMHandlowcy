package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.radekbonk.HADMHandlowcy.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
