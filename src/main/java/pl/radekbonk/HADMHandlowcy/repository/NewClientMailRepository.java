package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.NewClientMail;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface NewClientMailRepository extends CrudRepository<NewClientMail, Long> {
    List<NewClientMail> findByUserAndWeekAndYear(User user, int week, int year);
    List<NewClientMail> findByUserAndMonthAndYear(User user, int month, int year);
}
