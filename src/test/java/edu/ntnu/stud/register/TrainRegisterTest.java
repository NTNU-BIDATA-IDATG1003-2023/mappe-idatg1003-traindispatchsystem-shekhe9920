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

  HashMap<String, TrainDispatchSystem> trainDispatchSystemRegisterTest;
  ArrayList<TrainDispatchSystem> dispatchSearchResultTest;
  TrainRegister registerTest;

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
  }

  @AfterEach
  void tearDown() {
    trainDispatchSystemRegisterTest = null;
    registerTest = null;
  }

  @Test
  void addTrainDeparturePositiveTest() {
    setUp();

    System.out.println("Before adding: " + trainDispatchSystemRegisterTest.size());

    TrainDispatchSystem trainDispatch2 =
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "9003");
    trainDispatchSystemRegisterTest.put(trainDispatch2.getTrainNumber() ,trainDispatch2);

    System.out.println("After adding: " + trainDispatchSystemRegisterTest.size());

    Iterator<TrainDispatchSystem> iterator = trainDispatchSystemRegisterTest.values().iterator();
    int count = 0;
    while (iterator.hasNext()) {
      iterator.next();
      count++;
    }

    System.out.println("Count after iteration: " + count);

    assertEquals(3, count, "There was a problem adding train to the register");

    System.out.println("Train was added successfully");
  }

  @Test
  void addTrainDepartureNegativeTest() {
    setUp();

    System.out.println("Before adding: " + trainDispatchSystemRegisterTest.size());

    TrainDispatchSystem trainDispatch3 =
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "9000");
    trainDispatchSystemRegisterTest.put(trainDispatch3.getTrainNumber() ,trainDispatch3);

    System.out.println("After adding: " + trainDispatchSystemRegisterTest.size());

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
  }

  private void printTable(Iterator<TrainDispatchSystem> iterator) {
    System.out.println("================================================");
    System.out.println("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |");
    System.out.println("================================================");

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatchSystem = iterator.next();
      System.out.println(String.format("| %-17s | %-11s | %-13s  | %-5d | %-4s | %-12s |%n",
          trainDispatchSystem.getDepartureStation(),
          trainDispatchSystem.getDestination(),
          trainDispatchSystem.getDepartureTime(),
          trainDispatchSystem.getTrack(),
          trainDispatchSystem.getLine(),
          trainDispatchSystem.getTrainNumber()));
    }

    System.out.println("================================================");
  }
  @Test
  void sortListByDepartureTimeTest() {
    // Arrange
    setUp();

    // Printing the table before sorting
    System.out.println("Table before sorting");
    printTable(registerTest.getTrainDispatchListIterator());

    // Act
    registerTest.sortListByDepartureTime();

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
    System.out.println("Table after sorting");
    printTable(registerTest.getTrainDispatchListIterator());

  }

  // Commit test
}