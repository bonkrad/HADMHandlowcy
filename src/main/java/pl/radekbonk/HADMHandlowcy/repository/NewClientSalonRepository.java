package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.NewClientSalon;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface NewClientSalonRepository extends CrudRepository<NewClientSalon, Long> {
    List<NewClientSalon> findByUserAndWeekAndYear(User user, int week, int year);
    List<NewClientSalon> findByUserAndMonthAndYear(User user, int month, int year);
}
