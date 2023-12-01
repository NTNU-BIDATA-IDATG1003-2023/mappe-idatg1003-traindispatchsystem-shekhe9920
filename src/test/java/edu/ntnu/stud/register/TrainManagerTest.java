package edu.ntnu.stud.register;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainManagerTest {

  TrainManager trainManagerTest;
  @BeforeEach
  void setUp() {

    trainManagerTest = new TrainManager();

    trainManagerTest.markTrainNumberAsAllocated("804",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "804"));

    trainManagerTest.markTrainNumberAsAllocated("809",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "809"));
  }

  @AfterEach
  void tearDown() {
    trainManagerTest = null;
  }

  @Test
  void isTrainNumberAvailablePositiveTest() {

    setUp();

    assertTrue(trainManagerTest.isTrainNumberAvailable("810"),
        "Train number is not available");
    System.out.println("Train number is available");

  }

  @Test
  void isTrainNumberAvailableNegativeTest() {

    setUp();

    assertFalse(trainManagerTest.isTrainNumberAvailable("809"),
        "Train number is available");
    System.out.println("Train number is not available");
  }

  @Test
void markTrainNumberAsAllocatedPositiveTest() {

    trainManagerTest.markTrainNumberAsAllocated("821",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "821"));
    assertFalse(trainManagerTest.isTrainNumberAvailable("821"),
        "Marking the train number as allocated failed");

    System.out.println("Train number allocated successfully");
  }

  @Test
  void markTrainNumberAsAllocatedNegativeTest() {

    setUp();

    assertThrows(IllegalArgumentException.class, () -> {
      trainManagerTest.markTrainNumberAsAllocated("804",
          new TrainDispatchSystem("Gjøvik", "Oslo",
              LocalTime.of(8, 55), "F1", 1, "804"));
    }, "Expected IllegalArgumentException was not thrown");

    System.out.println("Train number was not allocated, because it was already allocated");
  }
  // Commit test
}