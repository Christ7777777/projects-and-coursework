package hw1;
/**
Model simulation of Cyride Bus Service
 **/
public class CyrideBus {
    /**
     * Initial position of the bus
     */
    public static final int BUS_GARAGE = -1;
    /**
     * Maximum capacity of the bus
     */
    private int maxCapacity;
    /**
     * Number of stops
     */
    private int stops;
    /**
     * The current stop of the bus
     */
    private int currentStop;
    /**
     * Current number of passengers on the bus
     */
    private int passengers ;
    /**
     * total number of passengers to get onto the bus
     */
   private int totalPassengers;
    /**
     * current capacity of the bus with respect to current passengers
     */
    private int currentCapacity;

    /**
     *
     *
     *  sets the position of the bus in the garage(-1)
     * @param givenMaxCapacity
     * Constructs a bus with a specific max capacity
     * @param givenNumStops
     *  Specific number of stops
     */

    public CyrideBus(int givenMaxCapacity, int givenNumStops) {
        maxCapacity = givenMaxCapacity;
        
        stops = givenNumStops;
        currentStop = BUS_GARAGE ;
        currentCapacity = maxCapacity;
    }

    /**
     * returns the current capacity of the bus
     * @return
     * the max capacity of the bus if the bus is in service
     * 0 if the bus is not in service
     */
    public int getCurrentCapacity() {
return currentCapacity;
    }

    /**
     * returns the current stop at which the bus is
     * @return
     * -1 when the bus is in the garage
     */
    public int getCurrentStop(){

        return currentStop;
    }

    /**
     * returns the current number of passengers in the bus after every stop
     * @return
     * 0 when the bus is not in service
     */
    public int getNumPassengers() {
        return passengers;
    }

    /**
     * returns the total number of passengers to get on the bus
     * @return
     * returns 0 when the bus is not in service
     */
    public int getTotalRiders() {
 return totalPassengers;

    }

    /**
     * returns the status of the bus
     * @return
     *  false when the currentCapacity is 0 when the bus is out of service
     */
    public boolean isInService() {
return currentCapacity > 0;
    }

    /**
     * assigns a value to the instance variables
     * updates the stop at which the bus is
     * updates the current number of passengers on the bus
     * updates the total number of passengers to get onto the bus
     * @param peopleOff
     * the people that get off the bus
     * @param peopleOn
     * the people that get on the bus
     */
    public void nextStop(int peopleOff, int peopleOn) {
    	currentStop += 1;
    	currentStop =  currentStop % stops;
passengers = passengers - peopleOff;
passengers = Math.max(passengers, 0);
int actualPeopleOn = passengers;
passengers = passengers + peopleOn;
passengers = Math.min(passengers, currentCapacity);
actualPeopleOn = passengers - actualPeopleOn;
totalPassengers += Math.max(actualPeopleOn, 0);
    }

    /**
     * updates the status of the bus based on the current capacity
     */
    public void placeInService() {
currentCapacity = maxCapacity;
    }

    /**
     * updates the status of the bus based on the current capacity, number of stops and current number of passengers
     */
    public void removeFromService() {
currentCapacity = 0;
currentStop = -1;
passengers = 0;


    }
}