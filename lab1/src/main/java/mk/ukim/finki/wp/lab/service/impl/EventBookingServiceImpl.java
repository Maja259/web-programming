package mk.ukim.finki.wp.lab.service.impl;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryEventBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final List<EventBooking> bookings = new ArrayList<>();

    private final InMemoryEventBookingRepository inMemoryEventBookingRepository;

    public EventBookingServiceImpl(InMemoryEventBookingRepository inMemoryEventBookingRepository) {
        this.inMemoryEventBookingRepository = inMemoryEventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
//        if (eventName == null || eventName.isEmpty()) {
//            throw new IllegalArgumentException("Choosing an event is required");
//        }
        EventBooking booking = new EventBooking(eventName,attendeeName,attendeeAddress, numberOfTickets);
//        bookings.add(booking);
        inMemoryEventBookingRepository.addBooking(booking);
        return booking;
    }

    @Override
    public List<EventBooking> listAll() {
        return inMemoryEventBookingRepository.findAllBookings();
    }

    @Override
    public List<EventBooking> searchBookings(String searchText, Long numberOfTickets) {
        return inMemoryEventBookingRepository.searchBookings(searchText, numberOfTickets);
    }

}
