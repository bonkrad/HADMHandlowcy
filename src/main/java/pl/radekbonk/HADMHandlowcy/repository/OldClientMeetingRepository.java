package pl.radekbonk.HADMHandlowcy.repository;

import org.springframework.data.repository.CrudRepository;
import pl.radekbonk.HADMHandlowcy.model.OldClientMeeting;
import pl.radekbonk.HADMHandlowcy.model.User;

import java.util.List;

public interface OldClientMeetingRepository extends CrudRepository<OldClientMeeting, Long> {
    List<OldClientMeeting> findByUserAndWeekAndYear(User user, int week, int year);
    List<OldClientMeeting> findByUserAndMonthAndYear(User user,int month, int year);
}
