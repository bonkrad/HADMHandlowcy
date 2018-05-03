package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.MiscDras;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface MiscDrasRepository extends CrudRepository<MiscDras, Long> {
    List<MiscDras> findByUserAndWeekAndYear(User user, int week, int year);
    List<MiscDras> findByUserAndMonthAndYear(User user, int month, int year);
}
