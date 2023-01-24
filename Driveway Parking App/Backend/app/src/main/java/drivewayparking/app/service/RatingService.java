package drivewayparking.app.service;

import drivewayparking.app.dto.RatingRequest;
import drivewayparking.app.dto.RatingResponse;
import drivewayparking.app.entity.Booking;
import drivewayparking.app.entity.Rating;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repository;

    @Autowired
    private BookingService service;

    /**
     * save a rating to the repository
     * @param rating
     *          a request object that will be converted to a rating
     * @return 1: error, if rating already exists
     *         0: okay, if rating is saved
     */
    public Integer saveRating(RatingRequest rating) {
        // check if rating exists
        if (repository.existsById(rating.getId())) {
            return Status.Error.getValue();
        }

        Rating r = requestToRating(rating, service.getBookingById(rating.getId()));
        repository.save(r);
        return Status.OK.getValue();
    }

    /**
     * return all ratings in the repository
     * @return  a list of rating objects
     */
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    /**
     * delete a rating from rating repository
     * @param id
     *          id of the rating to be deleted
     * @return 0: successful deletion of rating
     *         1: error, unable to delete rating
     */
    public Integer deleteRating(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return Status.OK.getValue();
        }

        return Status.Not_Found.getValue();

    }

    /**
     * find a specific rating based on id number
     * @param id
     *          id of the rating to be found
     * @return a rating object
     */
    public Rating getRatingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * update a rating already in the repository
     * @param rating
     *          rating with updated information
     * @return 0: successful update
     *         1: rating to update not found
     */
    public Integer updateRating(Rating rating) {
        Rating existingRating = repository.findById(rating.getId()).orElse(null);

        if (existingRating != null) {
            existingRating.setAccommodation(rating.getAccommodation());
            existingRating.setSafety(rating.getSafety());
            existingRating.setResponsiveness(rating.getResponsiveness());
            existingRating.setComments(rating.getComments());
            return Status.OK.getValue();
        }

        return Status.Not_Found.getValue();

    }

    public Rating requestToRating(RatingRequest request, Booking b) {
        Rating r = new Rating();
        r.setBooking(b);
        r.setComments(request.getComments());
        r.setSafety(request.getSafety());
        r.setResponsiveness(request.getResponsiveness());
        r.setAccommodation(request.getAccommodation());

        return r;
    }

    public RatingResponse ratingToResponse(Rating rating) {
        RatingResponse r = new RatingResponse();
        r.setRid(rating.getId());
        r.setUid(rating.getBooking().getUser().getId());
        r.setPid(rating.getBooking().getProperty().getId());
        r.setAccommodation(rating.getAccommodation());
        r.setResponsiveness(rating.getResponsiveness());
        r.setSafety(rating.getSafety());
        r.setComments(rating.getComments());

        return r;
    }

    public List<RatingResponse> RatingsToResponse(List<Rating> ratings) {
        List<RatingResponse> result = new ArrayList<>();
        for (Rating r : ratings) {
            RatingResponse temp = ratingToResponse(r);
            result.add(temp);
        }

        return result;
    }

    public List<Rating> getRatingsByProperty(Long id) {
        return repository.findByProperty(id);
    }
}
