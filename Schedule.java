import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * This class reads information form file and creates the train schedule. It contains two instance variables named trains and distance,
 * a constructor with one parameter fileName and four methods named fillArray, fastestTrain, sortDeparture and to String
 * @author (Lee Mao)
 * @version (08012018)
 */

public class Schedule {

    //fields
    private Train[] trains;
    private int distance;

    /**
     * Constructor  initialize the state of created object
     * @param  fileName  the name of file to be read
     * @throws FileNotFoundException in case fileName cannot be found
     */
    public Schedule(String fileName) throws FileNotFoundException {
        fillArray(fileName); //call fillArray method
    }

    /**
     * fillArray method  read the train information from file and fill each train as an object in array
     * @param  fileName  the name of file to be read
     * @throws FileNotFoundException in case fileName cannot be found
     * @throws IndexOutOfBoundsException  in case array index out of bounds
     */
    public void fillArray(String fileName) throws FileNotFoundException, IndexOutOfBoundsException {
        File f = new File(fileName);
        Scanner input = new Scanner(f);

        this.distance = input.nextInt(); //get distance
        int noOfTrains = input.nextInt(); //get the number of trains

        this.trains = new Train[noOfTrains];

        for(int i = 0; i < noOfTrains; i++) {
            String trainName = input.next() + input.nextLine(); //get train name

            int departureHour = input.nextInt();
            int departureMinute = input.nextInt(); //get departure time information

            int arrivalHour = input.nextInt();
            int arrivalMinute = input.nextInt(); //get arrival time information

            Time departureTime = new Time(departureHour, departureMinute); //construct departureTime
            Time arrivalTime = new Time(arrivalHour, arrivalMinute); //construct arrivalTime
            trains[i] = new Train(trainName, departureTime, arrivalTime, distance); //assign information of each train in the array
        }
    }

    /**
     * toString method  help to print all the contents of the array
     */
    public String toString() {
        String res = "";
        int size = this.trains.length;
        for(int i = 0; i < size; i++) {
            res += this.trains[i].toString(); //call toString method in Train class to get information of each train
        }
        return res;
    }

    /**
     * sortDeparture method  sorts all the train in the array according to the departure time
     */
    public void sortDeparture() {
        Train temp;
        int size = this.trains.length;
        //Bubble sort is used here to sort the departure time
        for(int i = 0; i < size; i++) { //first for loop
            for(int j = 0; j < size - 1 - i; j++) { //second for loop inside the first for loop
                int depTimeInMinOfTrainJ = this.trains[j].getDeparture().getMinute() + this.trains[j].getDeparture().getHour()*60;
                int depTimeInMinOfTrainJPlusOne = this.trains[j+1].getDeparture().getMinute() + this.trains[j+1].getDeparture().getHour()*60;
                if(depTimeInMinOfTrainJ > depTimeInMinOfTrainJPlusOne) {
                    temp = this.trains[j];
                    this.trains[j] = this.trains[j+1];
                    this.trains[j+1] = temp;
                }
            }
        }
    }

    /**
     * fastestTrain method  get the train with the fastest speed
     * @return  the train with the fastest speed
     */
    public Train fastestTrain() {
       int indexOfMax = 0; //index of Max average speed equals to 0(the first train)
       int size = this.trains.length;
       for(int i = 1; i < size; i++) {
           if(this.trains[i].averageSpeed() > this.trains[indexOfMax].averageSpeed())
               indexOfMax = i; //if average speed of train i is greater, then the index of Max average equals to i
       }
       return this.trains[indexOfMax];
    }
}