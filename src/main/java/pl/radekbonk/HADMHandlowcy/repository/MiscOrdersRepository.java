package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.MiscOrders;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface MiscOrdersRepository extends CrudRepository<MiscOrders, Long> {
    List<MiscOrders> findByUserAndWeekAndYear(User user, int week, int year);
    List<MiscOrders> findByUserAndMonthAndYear(User user, int month, int year);
}
