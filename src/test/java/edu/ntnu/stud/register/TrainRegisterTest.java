package edu.ntnu.stud.register;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainRegisterTest {

  // TODO: Maybe add a method for removing a train departure from the register, and also a method for removing all train departures from the register.
  // TODO: Maybe add a method for removing the delay from a train departure.

  HashMap<String, TrainDispatchSystem> trainDispatchSystemRegisterTest;
  ArrayList<TrainDispatchSystem> dispatchSearchResultTest;
  TrainRegister registerTest;
  String LINE = "==================================================================================";
  String YELLOW_SEPARATOR_LINE =
      "\u001B[33m-------------------------------------------"
          + "----------------------------------------------------\u001B[0m";

  @BeforeEach
  void setUp() {

    trainDispatchSystemRegisterTest = new HashMap<>();
    dispatchSearchResultTest = new ArrayList<>();


    TrainDispatchSystem trainDispatch0 =
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "9000");
    trainDispatchSystemRegisterTest.put(trainDispatch0.getTrainNumber(), trainDispatch0);
    dispatchSearchResultTest.add(trainDispatch0);

    TrainDispatchSystem trainDispatch1 =
        new TrainDispatchSystem("Gjøvik", "Bergen",
            LocalTime.of(18, 55), "F1", 2, "9001");
    trainDispatchSystemRegisterTest.put(trainDispatch1.getTrainNumber(), trainDispatch1);
    dispatchSearchResultTest.add(trainDispatch1);

    registerTest = new TrainRegister();

    registerTest.initializeDepartureRegister();

    for (TrainDispatchSystem train : trainDispatchSystemRegisterTest.values()) {
      System.out.println("Train " + train.getTrainNumber() + " - Departure Time: " + train.getDepartureTime());
    }
  }

  @AfterEach
  void tearDown() {
    //trainDispatchSystemRegisterTest = null;
    registerTest = null;
    System.out.println(YELLOW_SEPARATOR_LINE);
  }



  /**
   * Tests the positive case of adding a train departure to the register.
   * Verifies that the train is successfully added to the register.
   */
  @Test
  void addTrainDeparturePositiveTest() {
    setUp();

    System.out.println("Adding train with the different train number. (Positive test):\n");
    System.out.println("Before adding: " + trainDispatchSystemRegisterTest.size());

    TrainDispatchSystem trainDispatch2 =
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "9003");
    trainDispatchSystemRegisterTest.put(trainDispatch2.getTrainNumber() ,trainDispatch2);

    System.out.println("After adding: " + trainDispatchSystemRegisterTest.size());

    // Counting the number of trains in the register after adding the train
    Iterator<TrainDispatchSystem> iterator = trainDispatchSystemRegisterTest.values().iterator();
    int count = 0;
    while (iterator.hasNext()) {
      iterator.next();
      count++;
    }

    System.out.println("Count after iteration: " + count);

    assertEquals(3, count, "There was a problem adding train to the register");

    System.out.println("Train was added successfully");

    // Cleanup
    tearDown();
  }


  /**
   * Tests the negative case of adding a train with the same train number.
   * Verifies that the train is not added to the register due to the duplicate
   * train number.
   */
  @Test
  void addTrainDepartureNegativeTest() {
    setUp();

    System.out.println("Adding train with the same train number. (Negative test):\n");
    System.out.println("Before adding: " + trainDispatchSystemRegisterTest.size());

    TrainDispatchSystem trainDispatch3 =
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "9000");
    trainDispatchSystemRegisterTest.put(trainDispatch3.getTrainNumber() ,trainDispatch3);

    System.out.println("After adding: " + trainDispatchSystemRegisterTest.size());

    // Counting the number of trains in the register after adding the train
    Iterator<TrainDispatchSystem> iterator = trainDispatchSystemRegisterTest.values().iterator();
    int count = 0;
    while (iterator.hasNext()) {
      iterator.next();
      count++;
    }

    System.out.println("Count after iteration: " + count);

    assertEquals(2, count, "Train was added to the register, "
        + "even though it shouldn't have been added, because this departure already exists");

    System.out.println("Train was not added, because this departure already exists");

    // Cleanup
    tearDown();
  }

  /**
   * A simple method to print the table of train departures to be used in the tests.
   *
   * @param iterator The iterator for the collection of train departures.
   */
  private void printTable(Iterator<TrainDispatchSystem> iterator) {

    System.out.println(LINE);
    System.out.println("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |");
    System.out.println(LINE);

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatchSystem = iterator.next();
      System.out.printf("| %-17s | %-11s | %-13s  | %-5d | %-4s | %-12s |%n%n",
          trainDispatchSystem.getDepartureStation(),
          trainDispatchSystem.getDestination(),
          trainDispatchSystem.getDepartureTime(),
          trainDispatchSystem.getTrack(),
          trainDispatchSystem.getLine(),
          trainDispatchSystem.getTrainNumber());
    }

    System.out.println(LINE);
  }


  /** Test for {@code sortListByDepartureTime} method.
   * <p>
   * Tests the sorting of the train departures by departure time.
   * Verifies that the list is correctly sorted after calling the sort method.
   */
  @Test
  void sortListByDepartureTimeTest() {
    // Arrange
    setUp();
    System.out.println("Testing if the list is sorted by departure time:\n");

    // Printing the table before sorting
    System.out.println("Table before sorting:");
    printTable(registerTest.getTrainDispatchListIterator());

    // Act
    registerTest.sortListByDepartureTime(); //Sorting the list by departure time

    // Assert
    Iterator<TrainDispatchSystem> iterator = registerTest.getTrainDispatchListIterator();
    LocalTime previousDepartureTime = null;

    while (iterator.hasNext()) {
      TrainDispatchSystem currentTrain = iterator.next();
      LocalTime currentDepartureTime = currentTrain.getDepartureTime();

      if (previousDepartureTime != null) {
        assertTrue(currentDepartureTime.equals(previousDepartureTime) ||
                currentDepartureTime.isAfter(previousDepartureTime),
            "Trains are not sorted by departure time");
      }
      previousDepartureTime = currentDepartureTime;
    }

    // Print the table after sorting (outside the loop)
    System.out.println("\nTable after sorting:");
    printTable(registerTest.getTrainDispatchListIterator());

    // Cleanup
    tearDown();
  }


  /**
   * Test for {@code removeTrainsIfDepartureTimePassed}.
   * <p>
   * Tests the removal of trains if their departure time has passed.
   * Verifies that the correct trains are removed based on the updated station time.
   * <p>
   * (This test also checks if the {@code searchByAttributeAndValue} method works as expected.)
   */
  @Test
  void removingTrainThatTheDepartureTimeIsPassed() {

    // Creating a new TrainRegister instance
    TrainRegister registerTest = new TrainRegister();

    // New train departure with departure time in the past
    registerTest.addTrainDeparture(new TrainDispatchSystem("Gjøvik", "Oslo",
        LocalTime.of(8, 55), "F1", 1, "TEST101"));

    // Search for the train by its number before the update
    boolean isTrainPresentBeforeUpdate = registerTest
        .searchByAttributeAndValue("trainNumber", "TEST101")
        .hasNext();


    // Checking if train is present before the station time update
    assertTrue(isTrainPresentBeforeUpdate, "Train should be present before the update");

    // Updating the station time to simulate the passage of time
    LocalTime stationTime = LocalTime.of(12, 0);
    registerTest.updateStationTime(stationTime);

    // Removing trains with departure times that have passed
    registerTest.removeTrainsIfDepartureTimePassed();

    // Search for the train again after the update
    boolean isTrainPresentAfterUpdate = registerTest
        .searchByAttributeAndValue("trainNumber", "TEST101")
        .hasNext();


    // Checking if train is no longer present after the station time update
    assertFalse(isTrainPresentAfterUpdate, "Train should be removed after the update");

    System.out.println("Train departure list updated successfully");

    // Cleanup
    tearDown();
  }

}