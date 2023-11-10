package trainmanager;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainManagerTest {

  TrainManager trainManagerTest = new TrainManager();
  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void isTrainNumberAvailablePositiveTest() {
    assertTrue(trainManagerTest.isTrainNumberAvailable("804"),
        "Train number is available");

  }

  @Test
  void isTrainNumberAvailableNegativeTest() {
    trainManagerTest.markTrainNumberAsAllocated("809",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "809"));

    assertFalse(trainManagerTest.isTrainNumberAvailable("809"),
        "Train number is not available");
  }

  @Test
void markTrainNumberAsAllocatedPositiveTest() {
    trainManagerTest.markTrainNumberAsAllocated("804",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "804"));

    assertFalse(trainManagerTest.isTrainNumberAvailable("804"),
        "Train number is available");
  }

  @Test
  void markTrainNumberAsAllocatedNegativeTest() {
    trainManagerTest.markTrainNumberAsAllocated("806",
        new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "806"));

    assertFalse(trainManagerTest.isTrainNumberAvailable("806"),
        "Train number is not available");
  }
}