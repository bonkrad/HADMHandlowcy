package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.MiscDelivery;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface MiscDeliveryRepository extends CrudRepository<MiscDelivery, Long> {
    List<MiscDelivery> findByUserAndWeekAndYear(User user, int week, int year);
    List<MiscDelivery> findByUserAndMonthAndYear(User user, int month, int year);
}
