package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.NewClientTelephone;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface NewClientTelephoneRepository extends CrudRepository<NewClientTelephone, Long> {
    List<NewClientTelephone> findByUserAndWeekAndYear(User user, int week, int year);
    List<NewClientTelephone> findByUserAndMonthAndYear(User user, int month, int year);
}
