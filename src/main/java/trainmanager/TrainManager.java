package trainmanager;

import edu.ntnu.stud.TrainDispatchSystem;
import java.util.HashMap;
import java.util.Map;



/**
 * The {@code TrainManager} class manages train dispatch systems
 * and the allocation of train numbers.
 * It tracks the association between allocated train numbers and
 * the corresponding train departures.
 *
 * <p>Example:
 * *
 * * <blockquote><pre>
 *    TrainDispatchSystem trainA = new TrainDispatchSystem("City1", "City2",
 *        LocalTime.of(8, 55), "RE31", 2);
 *    TrainDispatchSystem trainB = new TrainDispatchSystem("City3", "City4",
 *        LocalTime.of(9, 55), "RE30", 2);
 *
 *    // Allocate train numbers using TrainManager
 *    TrainManager.markTrainNumberAsAllocated("123", trainA);
 *    TrainManager.markTrainNumberAsAllocated("456", trainB);
 * </pre></blockquote>
 *
 * <p>The primary purpose of this class is to prevent
 * the allocation of the same train number to multiple train departures,
 * ensuring each train number is unique within the system.
 *
 * @author Karwan Shekhe
 * @version 0.0.2
 * @since 0.0.1
 */
public class TrainManager {
  // A map to keep track of all allocated train numbers and their associations
  private Map<String, TrainDispatchSystem> allocatedTrainNumbers = new HashMap<>();

  /**
   * Checks the availability of a train number.
   *
   * @param trainNumber The train number to check for availability.
   * @return {@code true} if the train number is available.
   * @since 0.0.2
   */
  public boolean isTrainNumberAvailable(String trainNumber) {
    return !allocatedTrainNumbers.containsKey(trainNumber);
  }

  /**
   * Marks a train number as allocated and associates it with a specific train departure.
   *
   * @param trainNumber The train number to mark as allocated.
   * @param train The train departure associate with the allocated train number
   * @since 0.0.2
   */
  public void markTrainNumberAsAllocated(String trainNumber, TrainDispatchSystem train) {
    allocatedTrainNumbers.put(trainNumber, train);
  }
}