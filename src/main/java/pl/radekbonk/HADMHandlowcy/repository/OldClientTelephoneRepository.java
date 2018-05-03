package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.OldClientTelephone;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface OldClientTelephoneRepository extends CrudRepository<OldClientTelephone, Long> {
    List<OldClientTelephone> findByUserAndWeekAndYear(User user, int week, int year);

    List<OldClientTelephone> findByUserAndMonthAndYear(User user, int month, int year);
}
