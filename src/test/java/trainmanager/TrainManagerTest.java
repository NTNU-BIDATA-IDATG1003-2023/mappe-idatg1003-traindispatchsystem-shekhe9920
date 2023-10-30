package trainmanager;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class TrainManagerTest {

  @Test
  void isTrainNumberAvailable() {
  }

  @Test
  void markTrainNumberAsAllocatedPositiveTest() {
    HashSet<TrainDispatchSystem> trainDispatchList = new HashSet<>();
    TrainManager trainManager = new TrainManager();
    TrainDispatchSystem trainA = new TrainDispatchSystem("City1", "City2",
        LocalTime.of(8, 55), "RE31", 1);
    trainDispatchList.add(trainA);

    trainA.setTrainNumber("123");
    trainManager.markTrainNumberAsAllocated(trainA.getTrainNumber(), trainA);

    assertEquals("123", trainA.getTrainNumber(), "Train number is allocated");
  }

  @Test
  void allocateTrainNumbers() {
  }

  @Test
  void findTrainDeparture() {
    HashSet<TrainDispatchSystem> trainDispatchList = new HashSet<>();
    TrainManager trainManager = new TrainManager();
    TrainDispatchSystem trainA = new TrainDispatchSystem("City1", "City2",
        LocalTime.of(8, 55), "RE31", 1);
    trainDispatchList.add(trainA);

    trainA.setTrainNumber("123");
    trainManager.allocateTrainNumbers(trainA.getTrainNumber(), trainA);
    trainManager.findTrainDeparture(trainA.getTrainNumber());
    for (TrainDispatchSystem train : trainDispatchList) {
      assertEquals("123", trainA.getTrainNumber(), "Train number is allocated");
    }
  }
}