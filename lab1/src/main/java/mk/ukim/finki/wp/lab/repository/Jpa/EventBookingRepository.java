package mk.ukim.finki.wp.lab.repository.Jpa;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventBookingRepository extends JpaRepository<EventBooking,Long> {
    List<EventBooking> findByAttendeeName(String name);
    List<EventBooking> findByAttendeeNameAndEventName(String attendeeName, String eventName);

}
