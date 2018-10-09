
/**
 * This class deals with time in the format of XX(hour):XX(minute). It contains a constructor with two instance variables named hour and minute,
 * two accessors named getHour and getMinute, two mutators named setHour and setMinute, an overloaded toString method and two other methods named timeBetween
 * and zeroTime.
 *
 * @author (Lee Mao)
 * @version (07242018)
 */
public class Time
{
    //fields
    private int hour;
    private int minute;

    /**
     * Constructor  initialize the state of created object
     * @param  hour
     * @param  minute
     */
    public Time(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }

    /**
     * toString method  help to print the content of an object
     */
    public String toString() {
        return zeroTime(this.hour) + ":" + zeroTime(this.minute); //calls zeroTime method to put a zreo before a single digit number
    }

    /**
     * getHour method  return the hour of time
     * @return  hour
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * getMinute method  return the minute of time
     * @return  minute
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * setHour method  change the hour in the object
     */
    public void setHour(int hour) {
        if(hour < 0 || hour > 23) throw new IllegalArgumentException("Hour should be from 0 to 23!"); //make sure hour is from 0 to 23
        this.hour = hour;
    }

    /**
     * setMinute method  change the minute in the object
     */
    public void setMinute(int minute) {
        if(minute < 0 || minute > 59) throw new IllegalArgumentException("Minute should be from 0 to 59!"); //make sure minute is from 0 to 59
        this.minute = minute;
    }

    /**
     * timeBetween method  return the time between two different times
     * @return  time between two different times
     */
    public Time timeBetween(Time other) {
        //this method was re-written in a new way(only 8 lines of code)
        Time timeBetween = new Time(hour, minute);
        int otherInMin = other.getHour()*60 + other.getMinute();
        int thisInMin = this.getHour()*60 + this.getMinute();

        int timeBetweenInMin = otherInMin - thisInMin;
        if(timeBetweenInMin < 0) timeBetweenInMin += 24 * 60;

        timeBetween.hour = timeBetweenInMin / 60;
        timeBetween.minute = timeBetweenInMin % 60;

        return timeBetween;
        /* the old method which took 31 lines of code
        Time timeBetween = new Time(hour, minute);
        int hourDifference = other.hour - this.hour;
        int minuteDifference = other.minute - this.minute;
        if(hourDifference > 0) { //two times are in the same day
            if(minuteDifference >= 0) {
                timeBetween.hour = hourDifference;
                timeBetween.minute = minuteDifference;
            }else { //hour subtracts 1 and minute adds 60
                timeBetween.hour = hourDifference - 1;
                timeBetween.minute = minuteDifference + 60;
            }
        }
        else if(hourDifference < 0) { //the second time is in the next day
            if(minuteDifference >= 0) {
                timeBetween.hour = hourDifference + 24; //hour adds 24 because it's next day
                timeBetween.minute = minuteDifference;
            }else { //hour adds 24 and then subtracts 1 and minute adds 60
                timeBetween.hour = hourDifference + 23;
                timeBetween.minute = minuteDifference + 60;
            }
        }
        else { //the train leaves and arrives at the same hour in the same day or in different days
            if(minuteDifference > 0) { //train leaves and arrives at the same hour in the same day, the minute difference will be the travel time
                timeBetween.hour = 0;
                timeBetween.minute = minuteDifference;
            }else { //train leaves and arrives at the same hour in different days, the travel time is close to one day
                timeBetween.hour = 23;
                timeBetween.minute = minuteDifference + 60;
            }
        }
        return timeBetween;
        */
    }

    /**
     * zeroString method  add a zreo before a single digit number in hour or minute
     * @return  a string with two digits represents hour or minute
     */
    public String zeroTime(int num) {
        if(num / 10 == 0) return "0" + num; //num has only one digit
        return "" + num; //num has two digits, this statement can also be written as "return String.valueOf(num);"
    }

    /**
     * testTime method  test if there is any problem in the constructor or methods, this is the only static method allowed in supplier class
     */
    public static void testTime() {
        // check full constructor
        Time testTime = new Time(23, 59);
        if(testTime.getHour() != 23) System.out.println("Hour must be equal to 24 but the result is" + testTime.getHour());
        if(testTime.getMinute() != 59) System.out.println("Minute must be equal to 60 but the result is" + testTime.getMinute());

        // check timeBetween
        Time other = new Time(4, 37);
        if(testTime.timeBetween(other).getHour() != 4 ) System.out.println("Hour must be equal to 4 but the result is" + testTime.timeBetween(other).getHour());
        if(testTime.timeBetween(other).getMinute() != 38 ) System.out.println("Minute must be equal to 38 but the result is" + testTime.timeBetween(other).getMinute());

        //check setHour & setMinute
        testTime.setHour(0);
        testTime.setMinute(23);
        if(testTime.getHour() != 0) System.out.println("Hour must be equal to 0 but the result is" + testTime.getHour());
        if(testTime.getMinute() != 23) System.out.println("Minute must be equal to 23 but the result is" + testTime.getMinute());

        System.out.println("The testTime is completed!");
    }
}
