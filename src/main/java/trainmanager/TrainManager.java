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
 *    public void setTrainNumber(String trainNumber) {
 *
 *      if (trainManager.isTrainNumberAvailable(trainNumber)) {
 *        trainManager.markTrainNumberAsAllocated(trainNumber, this);
 *        this.trainNumber = trainNumber;
 *      }
 *
 *    }
 * </pre></blockquote>
 *
 * <p>The primary purpose of this class is to prevent
 * the allocation of the same train number to multiple train departures,
 * ensuring each train number is unique within the system.
 *
 * @author Karwan Shekhe
 * @version 0.0.5 (Version of this class)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
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
}