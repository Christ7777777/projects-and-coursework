package drivewayparking.app.service;

import drivewayparking.app.dto.BookingRequest;
import drivewayparking.app.dto.BookingResponse;
import drivewayparking.app.entity.Booking;
import drivewayparking.app.entity.Property;
import drivewayparking.app.entity.User;
import drivewayparking.app.enums.BookingStatus;
import drivewayparking.app.repository.BookingRepository;
import drivewayparking.app.repository.PropertyRepository;
import drivewayparking.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Integer saveBooking(BookingRequest b) {
        if (b.getCheck_in() == null || b.getCheck_out() == null || b.getCheck_in().after(b.getCheck_out())) {
            return BookingStatus.Invalid_Time_Request.getValue();
        }

        User u = userRepository.findById(b.getUid()).orElse(null);
        Property p = propertyRepository.findById(b.getPid()).orElse(null);

        if (u == null) {
            return BookingStatus.User_Not_Found.getValue();
        }
        if (p == null) {
            return BookingStatus.Property_Not_Found.getValue();
        }

        Booking booking = requestToBooking(b, u, p);

        if (conflictingBooking(booking)) {
            return BookingStatus.Conflicting_Booking.getValue();
        }

        repository.save(booking);

        return BookingStatus.OK.getValue();
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public List<Booking> getBookingsByProperty(Long id) {
        return repository.findByProperty(id);
    }

    public List<Booking> getBookingsByUser(Long id) {
        return repository.findByUser(id);
    }

    public Booking getBookingById(Long bid) {
        return repository.findById(bid).orElse(null);
    }

    public int deleteBooking(Long bid) {
        if (repository.existsById(bid)) {
            repository.deleteById(bid);
            return BookingStatus.OK.getValue();
        }

        return BookingStatus.Booking_Not_Found.getValue();
    }

    public boolean conflictingBooking(Booking b) {
        // we know the property and user exists already from our check
        // number of bookings for the property
        int total = getBookingsByProperty(b.getProperty().getId()).size();

        // now, check the number of non-conflicting bookings
        int nonConflicts = repository.findNonConflictingBookings(b.getProperty().getId(), b.getCheck_in(), b.getCheck_out());

        if (total > nonConflicts) {
            return true;
        }

        return false;
    }

    public Integer updateBooking(Booking booking) {
        Booking existingBooking = repository.findById(booking.getId()).orElse(null);

        if (existingBooking != null && !conflictingBooking(booking)) {
            existingBooking.setCheck_in(booking.getCheck_in());
            existingBooking.setCheck_out(booking.getCheck_out());
            repository.save(existingBooking);
            return BookingStatus.OK.getValue();
        }

        return BookingStatus.Conflicting_Booking.getValue();
    }

    public BookingResponse bookingRequest(BookingRequest request) {
        Property p = propertyRepository.findById(request.getPid()).orElse(null);

        if (p == null || request.getCheck_in() == null || request.getCheck_out() == null
                || request.getCheck_in().after(request.getCheck_out()))  {
            return null;
        }

        BookingResponse b = new BookingResponse();
        b.setPid(p.getId());
        b.setCheck_in(request.getCheck_in());
        b.setCheck_out(request.getCheck_out());
        b.setHours(bookingTime(b.getCheck_in(), b.getCheck_out()));
        b.setCost(bookingCost(b.getCheck_in(), b.getCheck_out(), p));

        return b;
    }

    public Booking requestToBooking(BookingRequest request, User u, Property p) {
        if (request.getCheck_in().after(request.getCheck_out())) {
            return null;
        }
        Booking b = new Booking();
        b.setCheck_in(request.getCheck_in());
        b.setCheck_out(request.getCheck_out());
        b.setUser(u);
        b.setProperty(p);
        b.setCost(bookingCost(request.getCheck_in(), request.getCheck_out(), p));
        return b;
    }

    public BookingResponse bookingToResponse(Booking b) {
        BookingResponse result = new BookingResponse();
        result.setId(b.getId());
        result.setPid(b.getProperty().getId());
        result.setUid(b.getUser().getId());
        result.setCheck_in(b.getCheck_in());
        result.setCheck_out(b.getCheck_out());
        result.setCost(b.getCost());
        result.setHours(bookingTime(b.getCheck_in(), b.getCheck_out()));

        return result;
    }

    public List<BookingResponse> bookingsToResponse(List<Booking> bookings) {
        List<BookingResponse> result = new ArrayList<>();

        for (Booking b : bookings) {
            result.add(bookingToResponse(b));
        }

        return result;
    }

    // helper method to calculate the cost of booking
    public double bookingCost(Timestamp in, Timestamp out, Property p) {
        double hours = bookingTime(in, out);
        return Math.round(hours * p.getRatePerHour() * 100.0)/100.0;
    }

    public double bookingTime(Timestamp in, Timestamp out) {
        long diff = out.getTime() - in.getTime();

        // to get to hours, hr = ms / 1000 / 3600
        return (diff / 1000.0 / 3600);
    }
}