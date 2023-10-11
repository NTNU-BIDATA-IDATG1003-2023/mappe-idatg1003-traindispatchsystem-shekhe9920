package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class serves as a testing program for the TrainDispatchSystem class.
 */
public class EnhetsTester {
    public static void main(String[] args) {

        // Create an ArrayList to store TrainDispatchSystem objects.
        ArrayList<TrainDispatchSystem> trainDispatchList = new ArrayList<>();

        // Create two TrainDispatchSystem objects and add them to the list.
        TrainDispatchSystem trainDispatch0 = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4, 123);
        TrainDispatchSystem trainDispatch1 = new TrainDispatchSystem("Gjøvik", "Bergen", LocalTime.of(8,
                55),"F1", 2, 123);

        // Attempt to set the train number for both objects to 123.
        trainDispatchList.add(trainDispatch0);
        trainDispatchList.add(trainDispatch1);

        trainDispatch0.setTrainNumber(123);
        trainDispatch1.setTrainNumber(123);

        // Print the list of TrainDispatchSystem objects.
        System.out.println(trainDispatchList);

        //Iterate through the list and print details of each TrainDispatchSystem objects.
        for (TrainDispatchSystem trainDispatchFromList : trainDispatchList) {
            System.out.println(trainDispatchFromList.getDepartureStation());
            System.out.println(trainDispatchFromList.getDestination());
            System.out.println(trainDispatchFromList.getDepartureTime());
            System.out.println(trainDispatchFromList.getTrack());
            System.out.println(trainDispatchFromList.getLine());
            System.out.println(trainDispatchFromList.getTrainNumber());
        }


    }

}
