package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.OldClientMail;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface OldClientMailRepository extends CrudRepository<OldClientMail, Long> {
    List<OldClientMail> findByUserAndWeekAndYear(User user, int week, int year);

    List<OldClientMail> findByUserAndMonthAndYear(User user, int month, int year);
}
