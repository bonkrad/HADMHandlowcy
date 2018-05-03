package pl.radekbonk.HADMHandlowcy.model;

import javax.persistence.*;
import java.util.Calendar;

@MappedSuperclass
public abstract class DummyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timestamp;

    private int week;

    private int day;

    private int month;

    private int year;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        Calendar calendar = Calendar.getInstance();
        this.timestamp = calendar;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.week = calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getWeek() {
        return week;
    }

    public int getMonth() {
        return month;
    }


    public int getYear() {
        return year;
    }

}
