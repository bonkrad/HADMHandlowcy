package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.MiscCallCenter;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface MiscCallCenterRepository extends CrudRepository<MiscCallCenter, Long> {
    List<MiscCallCenter> findByUserAndWeekAndYear(User user, int week, int year);
    List<MiscCallCenter> findByUserAndMonthAndYear(User user, int month, int year);
}
