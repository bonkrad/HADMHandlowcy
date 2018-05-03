package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.NewClientService;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface NewClientServiceRepository extends CrudRepository<NewClientService, Long> {
    List<NewClientService> findByUserAndWeekAndYear(User user, int week, int year);
    List<NewClientService> findByUserAndMonthAndYear(User user, int month, int year);
}
