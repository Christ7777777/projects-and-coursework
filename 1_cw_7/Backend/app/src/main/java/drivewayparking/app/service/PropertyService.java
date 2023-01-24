package drivewayparking.app.service;

import drivewayparking.app.dto.PropertyRequest;
import drivewayparking.app.entity.Booking;
import drivewayparking.app.entity.Property;
import drivewayparking.app.entity.Rating;
import drivewayparking.app.entity.User;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RatingService ratingService;

    public Property getPropertyById(Long id) { return repository.findById(id).orElse(null); }

    public Property getPropertyByTitle(String title) { return repository.findByTitle(title); }

    public List<Property> getProperties() { return repository.findAll(); }

    public List<Property> getPropertiesByUserId(Long id) {

        User existingUser = userService.getUserById(id);
        if (existingUser != null) return repository.findByUser(id);
        else return null;
    }

    public List<Property> getPropertiesByLocation(Double latitude, Double longitude, Double range) {

        List<Property> result = new ArrayList<>();
        LinkedHashMap<Property, Double> distanceMap = new LinkedHashMap<>();

        // find property closest to the given latitude and longitude
        // within a minute accuracy (~1.15 miles) to narrow down the search to state and city
        Property closestProperty = repository.findByMinuteLatitudeAndLongitude(latitude, longitude);

        if (closestProperty != null) {

            List<Property> properties = repository.findAllByCityState(closestProperty.getCity(), closestProperty.getState());
            for (Property property : properties) {

                Double propertyLatitude = property.getLatitude();
                Double propertyLongitude = property.getLongitude();

                Double distance = getDistance(latitude, propertyLatitude, longitude, propertyLongitude);

                if (Double.compare(distance, range) <= 0 && property.getApproved()) distanceMap.put(property, distance);
            }

            // sort properties by closest to the requested location
            distanceMap = sortLinkedHashMapByValues(distanceMap);
            List<Map.Entry<Property, Double> > sortedDistanceMapList = new LinkedList<Map.Entry<Property, Double> >(distanceMap.entrySet());
            for (Map.Entry<Property, Double> pair : sortedDistanceMapList) {
                result.add(pair.getKey());
            }

        }
        return result;
    }


    public List<Property> filterPropertiesByAvailability(List<Property> properties, Timestamp check_in, Timestamp check_out) {

        List<Property> result = new ArrayList<>();

        // we must iterate through each property in the table
        for (Property p : properties) {

            Property existingProperty = repository.findById(p.getId()).orElse(null);
            if (existingProperty != null) {

                // make a fake booking for this property with the desired check-in and check-out times
                Booking b = new Booking();
                b.setProperty(existingProperty);
                b.setCheck_in(check_in);
                b.setCheck_out(check_out);

                // if there's no conflict with this booking, add it to the result array
                if (!bookingService.conflictingBooking(b)) {
                    result.add(existingProperty);
                }
            }
        }

        return result;
    }

    public List<Property> filterPropertiesByParkingType(List<Property> properties, Boolean driveway, Boolean garage, Boolean parkingLot, Boolean handicapped) {

        List<Property> result = new ArrayList<>();

        for (Property property : properties) {
            if ((driveway == false || property.getDriveway()) &&
                    (garage == false || property.getGarage()) &&
                    (parkingLot == false || property.getParkingLot()) &&
                    (handicapped == false || property.getHandicapped()))

                result.add(property);
        }
        return result;
    }

    public List<Property> filterPropertiesByVehicleType(List<Property> properties, Boolean car, Boolean truck, Boolean motorcycle, Boolean oversized) {

        List<Property> result = new ArrayList<>();

        for (Property property: properties) {
            if ((car == false || property.getCar()) &&
                    (truck == false || property.getTruck()) &&
                    (motorcycle == false || property.getMotorcycle()) &&
                    (oversized == false || property.getOversized()))

                result.add(property);
        }
        return result;
    }

    public List<Property> filterPropertiesByAmenities(List<Property> properties, Boolean EVcharging, Boolean inOut, Boolean tailgating, Boolean shuttle) {

        List<Property> result = new ArrayList<>();

        for (Property property: properties) {
            if ((EVcharging == false || property.getEVcharging()) &&
                    (inOut == false || property.getInOut()) &&
                    (tailgating == false || property.getTailgating()) &&
                    (shuttle == false ||  property.getShuttle()))

                result.add(property);
        }
        return result;
    }

    public List<Property> sortPropertiesByRating(List<Property> properties) {

        List<Property> result = new LinkedList<>();
        LinkedHashMap<Property, Float> ratingMap = new LinkedHashMap<>();

        for (Property property : properties) {

            Property existingProperty = repository.findById(property.getId()).orElse(null);
            if (existingProperty != null) {

                float averageRating = 0;
                float weightedRating = 0;

                List<Rating> ratings = ratingService.getRatingsByProperty(existingProperty.getId());
                for (Rating rating : ratings) {
                    averageRating += (rating.getAccommodation() + rating.getSafety() + rating.getResponsiveness())/3.0;
                    weightedRating = averageRating / ratings.size();
                }
                ratingMap.put(existingProperty, weightedRating);
            }
        }

        // sort properties by rating
        ratingMap = sortLinkedHashMapByValues(ratingMap);

        // create a sorted list of properties and populate it from the sorted hashmap
        List<Map.Entry<Property, Float> > sortedRatingMapList = new LinkedList<Map.Entry<Property, Float> >(ratingMap.entrySet());
        for (Map.Entry<Property, Float> pair : sortedRatingMapList) {
            result.add(pair.getKey());
        }
        // arrange properties in descending order
        Collections.reverse(result);

        return result;
    }

    public List<Property> sortPropertiesByCreationDate(List<Property> properties) {

        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            Property existingProperty = repository.findById(property.getId()).orElse(null);
            if (existingProperty != null) result.add(existingProperty);
        }

        Collections.sort(result, Comparator.comparing(Property::getCreated));
        return result;
    }

    public List<Property> sortPropertiesByRate(List<Property> properties) {

        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            Property existingProperty = repository.findById(property.getId()).orElse(null);
            if (existingProperty != null) result.add(existingProperty);
        }

        Collections.sort(result, Comparator.comparing(Property::getRatePerHour));
        return result;
    }

    public Integer saveProperty(PropertyRequest request) {

        if (request.getTitle() != null && request.getUserId() != null && request.getStreet() != null && request.getCity() != null &&
            request.getState() != null && request.getZipcode() != null && request.getCountry() != null && request.getLatitude() != null &&
            request.getLongitude() != null && request.getDescription() != null && request.getRatePerHour() != null) {

            // checking if the property already exists
            Property existingProperty = repository.findByTitle(request.getTitle());

            // checking if the user for the new property exists
            User existingUser = userService.getUserById(request.getUserId());

            if (existingProperty == null && existingUser != null) {

                Property property = new Property();
                property.setUser(existingUser); // setting many-to-one relationship between property and user
                repository.save(property);

                request.setPropertyId(property.getId());

                return updateProperty(request);
            }
        }
        return Status.Error.getValue();
    }

    public Integer saveProperties(List<PropertyRequest> requests) {

        int status = Status.OK.getValue();
        for (PropertyRequest request : requests) {
            status += saveProperty(request);
        }
        if (status == Status.OK.getValue()) return Status.OK.getValue();
        else return Status.Error.getValue();
    }

    public Integer updateProperty(PropertyRequest request) {

        Property existingProperty = repository.findById(request.getPropertyId()).orElse(null);
        if (existingProperty != null) {

            // updating key attributes
            if (request.getTitle() != null) existingProperty.setTitle(request.getTitle());
            if (request.getStreet() != null) existingProperty.setStreet(request.getStreet());
            if (request.getCity() != null) existingProperty.setCity(request.getCity());
            if (request.getState() != null) existingProperty.setState(request.getState());
            if (request.getZipcode() != null) existingProperty.setZipcode(request.getZipcode());
            if (request.getCountry() != null) existingProperty.setCountry(request.getCountry());
            if (request.getLatitude() != null) existingProperty.setLatitude(request.getLatitude());
            if (request.getLongitude() != null) existingProperty.setLongitude(request.getLongitude());
            if (request.getDescription() != null) existingProperty.setDescription(request.getDescription());
            if (request.getRatePerHour() != null) existingProperty.setRatePerHour(request.getRatePerHour());

            // updating type of parking
            if (request.getDriveway() != null) existingProperty.setDriveway(request.getDriveway());
            if (request.getGarage() != null) existingProperty.setGarage(request.getGarage());
            if (request.getParkingLot() != null) existingProperty.setParkingLot(request.getParkingLot());
            if (request.getHandicapped() != null) existingProperty.setHandicapped(request.getHandicapped());

            // updating vehicle type
            if (request.getCar() != null) existingProperty.setCar(request.getCar());
            if (request.getTruck() != null) existingProperty.setTruck(request.getTruck());
            if (request.getMotorcycle() != null) existingProperty.setMotorcycle(request.getMotorcycle());
            if (request.getOversized() != null) existingProperty.setOversized(request.getOversized());

            // updating amenities
            if (request.getEVcharging() != null) existingProperty.setEVcharging(request.getEVcharging());
            if (request.getInOut() != null) existingProperty.setInOut(request.getInOut());
            if (request.getTailgating() != null) existingProperty.setTailgating(request.getTailgating());
            if (request.getShuttle() != null) existingProperty.setShuttle(request.getShuttle());

            repository.save(existingProperty);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }

    public Integer approveProperty(PropertyRequest request) {

        Property existingProperty = repository.findById(request.getPropertyId()).orElse(null);
        if (existingProperty != null) {
            existingProperty.setApproved(request.getApproved());
            repository.save(existingProperty);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }

    public Integer deleteProperty(Long id) {

        Property existingProperty = repository.findById(id).orElse(null);
        if (existingProperty != null) {
            repository.deleteById(id);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }

    // Helper method to calculate distance between two points given latitude and longitude
    public static double getDistance(double lat1, double lat2, double lon1, double lon2) {

        // convert from degrees to radians
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // radius of Earth in miles
        double r = 3956;

        return c * r;
    }

    // Helper method to sort a given hashmap by values in ascending order
    public static <K, V extends Comparable<V>> LinkedHashMap<K, V> sortLinkedHashMapByValues(LinkedHashMap<K, V> linkedHashMap)
    {
        // create a list from elements of HashMap
        List<Map.Entry<K, V>> list = new LinkedList<>(linkedHashMap.entrySet());

        // sort the list
        Collections.sort(list, Map.Entry.comparingByValue());

        // put data from sorted list to hashmap
        LinkedHashMap<K, V> sortedLinkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> pair : list) {
            sortedLinkedHashMap.put(pair.getKey(), pair.getValue());
        }

        return sortedLinkedHashMap;
    }
}
