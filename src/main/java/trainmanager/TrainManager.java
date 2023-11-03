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
 *
 * <blockquote><pre>
 *    TrainDispatchSystem trainA = new TrainDispatchSystem("City1", "City2",
 *        LocalTime.of(8, 55), "RE31", 1);
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
 * @version 0.0.4
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

  /** (CHANGE THIS METHOD WITH allocateTrainNumbers)
   * Marks a train number as allocated and associates it with a specific train departure.
   * This method is responsible for marking a train number as allocated to a particular
   * train departure. If the train number is already in use,
   * an IllegalArgumentException is thrown to indicate that the number is already allocated.
   * Otherwise, the train number is marked as allocated for the provided train departure.
   *
   * @param trainNumber The train number to mark as allocated.
   * @param train The train departure associate with the allocated train number.
   * @throws IllegalArgumentException if the train number is already allocated.
   * @since 0.0.2
   */
  public void markTrainNumberAsAllocated(String trainNumber, TrainDispatchSystem train) {
    // Check if the train number is already allocated to another train departure
    if (!isTrainNumberAvailable(trainNumber)) {
      throw new IllegalArgumentException("Train number " + trainNumber + " is already allocated");
    }
    allocatedTrainNumbers.put(trainNumber, train);
  }

  /** (NEW METHOD)
   * Allocates a train number to a specific train departure, if valid.
   * This method checks if both the train number and train departure are not null.
   * If either is null, it throws an IllegalArgumentException.
   * Additionally, it checks if the train number is available for allocation.
   * If available, it associates the train number with the provided train departure.
   *
   * @param trainNumber The train number to mark as allocated.
   * @param train The train departure associate with the allocated train number
   * @since 0.0.3
   */
  public void allocateTrainNumbers(String trainNumber, TrainDispatchSystem train) {
    if (trainNumber != null && train != null) {
      if (isTrainNumberAvailable(trainNumber)) {
        allocatedTrainNumbers.put(trainNumber, train);
      } else {
        throw new IllegalArgumentException("Train number is already allocated");
      }
    } else {
      throw new IllegalArgumentException("Invalid train number or train departure data");
    }
  }

}