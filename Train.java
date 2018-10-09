
/**
 * This class deals with the train infomation. It contains a constructor with four instance variables named trainName, departure, arrival and distance,
 * three accessors named getDeparture, getArrival and getDistance, three mutators named setDeparture, setArrival and setDistance, an overloaded toString
 * method and two other methods named averageSpeed and travelTime.
 *
 * @author (Lee Mao)
 * @version (07242018)
 */
public class Train
{
    //fields
    private String trainName;
    private Time departure;
    private Time arrival;
    private int distance;

    /**
     * Constructor  initialize the state of created object
     * @param  trainName  the name of train
     * @param  departure  the departure time
     * @param  arrival  the arrival time
     * @param  distance  the distance between two cities
     */
    public Train(String trainName, Time departure, Time arrival, int distance) {
        this.trainName = trainName;
        setDeparture(departure);
        setArrival(arrival);
        setDistance(distance);
    }

    /**
     * toString method  help to print the content of an object
     */
    public String toString() {
        return this.trainName + "\n" +
                String.format("%-20s", "Departure") + departure + "\n" + //"%-20s" means the string will take 20 chars with the string on the left side
                String.format("%-20s", "Arrival") + arrival + "\n" +
                String.format("%-20s", "Travel Time") + travelTime() + "\n" +
                String.format("%-20s", "Average speed") + averageSpeed() + " km/h\n\n";
    }

    /**
     * getDeparture method  returns the departure time
     * @return  departure time
     */
    public Time getDeparture() {
        return this.departure;
    }

    /**
     * getArrival method  returns the arrival time
     * @return  arrival time
     */
    public Time getArrival() {
        return this.arrival;
    }

    /**
     * getDistance method  returns the distance between two cities
     * @return  distance between two cities
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * setDeparture method  change the departure time of train
     */
    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    /**
     * setArrival method  change the arrival time of train
     */
    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    /**
     * setDistance method  change the distance between two cities
     */
    public void setDistance(int distance) {
        if(distance <= 0) throw new IllegalArgumentException("Distance should be greater than 0!"); //make sure distance is greater than 0
        this.distance = distance;
    }

    /**
     * travelTime method  call the timeBetween method in Time class to get the time between departure time and arrival time
     * @return  travel time which is the time between departure time and arrival time
     */
    public Time travelTime() {
        return departure.timeBetween(arrival); //call the timeBetween method in Time class
    }

    /**
     * averageSpeed method  calculate the average speed of train
     * @return  the average speed of train
     */
    public int averageSpeed() {
        return (int)Math.round(distance/(travelTime().getHour() + travelTime().getMinute()/60.0)); //the default value is long and needs to be casted to integer
    }

    /**
     * testTrain method  test if there is any problem in the constructor or methods, this is the only static method allowed in supplier class
     */
    public static void testTrain() {
        // check full constructor
        Time testTime = new Time(23, 59);
        Time other = new Time(4, 37);
        Train testTrain = new Train("Train name", testTime, other, 650);
        if(testTrain.getDeparture().getHour() != 23) System.out.println("Hour must be equal to 23 but the result is" + testTrain.getDeparture().getHour());
        if(testTrain.getDeparture().getMinute() != 59) System.out.println("Minute must be equal to 59 but the result is" + testTrain.getDeparture().getMinute());
        if(testTrain.getArrival().getHour() != 4) System.out.println("Hour must be equal to 4 but the result is" + testTrain.getArrival().getHour());
        if(testTrain.getArrival().getMinute() != 37) System.out.println("Minute must be equal to 37 but the result is" + testTrain.getArrival().getMinute());
        if(testTrain.getDistance() != 650) System.out.println("Distance must be equal to 650 but the result is" + testTrain.getDistance());

        //check travelTime
        if(testTrain.travelTime().getHour() != 4) System.out.println("Hour must be equal to 4 but the result is" + testTrain.travelTime().getHour());
        if(testTrain.travelTime().getMinute() != 38) System.out.println("Minute must be equal to 38 but the result is" + testTrain.travelTime().getMinute());

        //check averageSpeed
        if(testTrain.averageSpeed() != 140) System.out.println("Average Speed must be equal to 140 but the result is" + testTrain.averageSpeed());

        //check setDeparture
        Time testTime2 = new Time(12, 34);
        testTrain.setDeparture(testTime2);
        if(testTrain.getDeparture().getHour() != 12) System.out.println("Hour must be equal to 12 but the result is" + testTrain.getDeparture().getHour());
        if(testTrain.getDeparture().getMinute() != 34) System.out.println("Minute must be equal to 34 but the result is" + testTrain.getDeparture().getMinute());

        //check setArrival
        Time other2 = new Time(18, 13);
        testTrain.setArrival(other2);
        if(testTrain.getArrival().getHour() != 18) System.out.println("Hour must be equal to 18 but the result is" + testTrain.getArrival().getHour());
        if(testTrain.getArrival().getMinute() != 13) System.out.println("Minute must be equal to 13 but the result is" + testTrain.getArrival().getMinute());

        //check setDistance
        testTrain.setDistance(666);
        if(testTrain.getDistance() != 666) System.out.println("Distance must be equal to 666 but the result is" + testTrain.getDistance());

        System.out.println("The testTrain is completed!");
    }
}
