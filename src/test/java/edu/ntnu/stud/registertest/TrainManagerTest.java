package edu.ntnu.stud.registertest;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.register.TrainManager;
import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainManagerTest {

  TrainManager trainManagerTest;
  TrainRegister trainRegisterTest;
  TrainDispatchSystem trainDispatchSystemTest;
  @BeforeEach
  void setUp() {

    trainManagerTest = new TrainManager();
    trainRegisterTest = new TrainRegister();

    trainManagerTest.markTrainNumberAsAllocated("ManagerTest1",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "ManagerTest1"));

    trainManagerTest.markTrainNumberAsAllocated("ManagerTest2",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "ManagerTest2"));

  }

  @AfterEach
  void tearDown() {
    trainManagerTest = null;
    trainDispatchSystemTest = null;
    trainRegisterTest = null;
  }


  /**
   * Tests the positive case of checking the availability of a train number.
   * Verifies that the method correctly returns true for an available train number.
   */
  @Test
  void isTrainNumberAvailablePositiveTest() {

    setUp();

    assertTrue(trainManagerTest.isTrainNumberAvailable("1.ManagerTest"),
        "Train number is not available");
    System.out.println("Train number is available");

    tearDown();
  }


  /**
   * Tests the negative case of checking the availability of a train number.
   * Verifies that the method correctly returns false for an allocated train number.
   */
  @Test
  void isTrainNumberAvailableNegativeTest() {

    setUp();

    assertFalse(trainManagerTest.isTrainNumberAvailable("ManagerTest2"),
        "Train number is available, ");
    System.out.println("Train number is not available");

    tearDown();
  }


  /**
   * Tests the positive case of marking a train number as allocated.
   * Verifies that the train number is successfully marked as allocated.
   */
  @Test
void markTrainNumberAsAllocatedPositiveTest() {

    trainManagerTest.markTrainNumberAsAllocated("821",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "821"));
    assertFalse(trainManagerTest.isTrainNumberAvailable("821"),
        "Marking the train number as allocated failed");

    System.out.println("Train number allocated successfully");
  }



  /**
   * Tests the negative case of marking a train number as allocated.
   * Verifies that the method correctly throws an IllegalArgumentException
   * when trying to mark an already allocated train number.
   */
  @Test
  void markTrainNumberAsAllocatedNegativeTest() {

    setUp();

    TrainDispatchSystem trainTest = new TrainDispatchSystem("Gjøvik", "Oslo",
        LocalTime.of(8, 55), "F1", 1, "888");

    assertThrows(IllegalArgumentException.class,
        () -> trainManagerTest.markTrainNumberAsAllocated("ManagerTest1", trainTest),
        "Expected IllegalArgumentException was not thrown");

    System.out.println("Train number was not allocated, because it is already been allocated");

  }
}