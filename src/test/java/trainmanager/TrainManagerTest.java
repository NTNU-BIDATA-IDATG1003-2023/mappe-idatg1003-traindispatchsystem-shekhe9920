package trainmanager;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class TrainManagerTest {

  @Test
  void isTrainNumberAvailablePosetiveTest() {
    HashSet<TrainDispatchSystem> trainDispatchList = new HashSet<>();
    TrainManager trainManager = new TrainManager();
    TrainDispatchSystem trainA = new TrainDispatchSystem("City1", "City2",
        LocalTime.of(8, 55), "RE31", 1);
    trainDispatchList.add(trainA);

    trainA.setTrainNumber("756");
    trainManager.markTrainNumberAsAllocated(trainA.getTrainNumber(), trainA);
    assertNotEquals(true, trainManager.isTrainNumberAvailable(trainA.getTrainNumber()), " TrainNumber is not allocated");

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

    System.out.println(trainManager.isTrainNumberAvailable(trainA.getTrainNumber()));
    assertEquals(false, trainManager.isTrainNumberAvailable(trainA.getTrainNumber()), " TrainNumber is allocated");
  }

}