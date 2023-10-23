package trainmanager;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The {@code TrainManager} class manages train dispatch systems
 * and the allocation of train numbers.
 * It maintains a mapping of allocated train numbers to their allocation times and corresponding
 * train dispatch systems.
 *
 * <p>The class provides methods to allocate train numbers, check if a train number is available,
 * get the allocated train numbers, find the train number associated with a given dispatch system,
 * and reset allocations.
 *
 * @author Karwan Shekhe
 * @version 0.0.1
 * @since 0.0.1
 */
public class TrainManager {
  private Map<Integer, LocalDateTime> allocatedTrainNumbers = new HashMap<>();
  private Map<Integer, TrainDispatchSystem> trainNumberToDispatchSystem = new HashMap<>();

  /**
   * Default constructor for TrainManager.
   * This constructor is provided for compatibility and inheritance.
   * It allows creating an instance of TrainManager with default configurations.
   *
   * @since 0.0.1
   */
  public TrainManager() {
    // No custom initialization needed here.
  }

  /**
   * Allocates a new train number to a given train dispatch system.
   * This method assigns a unique train number to the specified train dispatch system,
   * records the allocation time, and associates the train number with the system.
   *
   * @param trainDispatchSystem The allocated train number, or a negative value if allocation fails due to
   *                            unavailability of valid train numbers.
   * @throws IllegalStateException If the allocation fails due to the unavailability of
   *                               valid train numbers.
   * @since 0.0.1
   */
  public int allocateTrainNumber(TrainDispatchSystem trainDispatchSystem) {
    int newTrainNumber = trainDispatchSystem.getTrainNumber();
    if (newTrainNumber > 0) {
      allocatedTrainNumbers.put(newTrainNumber, LocalDateTime.now());
      trainNumberToDispatchSystem.put(newTrainNumber, trainDispatchSystem);
      trainDispatchSystem.setTrainNumber(newTrainNumber, LocalDateTime.now());
      return newTrainNumber;
    } else {
      throw new IllegalStateException("Train number " + newTrainNumber + " is not available.");
    }

  }

  /**
   * Returns a mapping of allocated train numbers to their allocation times.
   *
   * @return A map of allocated train numbers to allocation times.
   * @since 0.0.1
   */
  public Map<Integer, LocalDateTime> getAllocatedTrainNumbers() {
    return allocatedTrainNumbers;
  }

  /**
   * Retrieves the train number associated with a given train dispatch system.
   *
   * @param trainDispatchSystem The train dispatch system to find the train number for.
   * @return The associated train number or -1 if not found.
   * @since 0.0.1
   */
  public int getTrainNumberForDispatchSystem(TrainDispatchSystem trainDispatchSystem) {
    for (Entry<Integer, TrainDispatchSystem> entry : trainNumberToDispatchSystem.entrySet()) {
      if (entry.getValue() == trainDispatchSystem) {
        return entry.getKey();
      }
    }
    return -1; // Return -1 if the TrainDispatchSystem is not found in the map
  }

  /**
   * Checks if a train number is available for allocation.
   *
   * @param trainNumber The train number to check.
   * @return True if the train number is available, false otherwise.
   * @since 0.0.1
   */
  public boolean isTrainNumberAvailable(int trainNumber) {
    // Check if the train number is not allocated or has been allocated more than 24 hours ago.
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime allocationTime = allocatedTrainNumbers.get(trainNumber);
    if (allocationTime == null) {
      return true; // Train number not allocated yet.
    } else {
      Duration duration = Duration.between(allocationTime, currentTime);
      return duration.toHours() >= 24;
    }
  }

  /**
   * Returns a mapping of allocated train numbers to their corresponding dispatch systems.
   *
   * @return A map of allocated train numbers to associated train dispatch systems.
   * @since 0.0.1
   */
  public Map<Integer, TrainDispatchSystem> getTrainNumberToDispatchSystem() {
    return trainNumberToDispatchSystem;
  }

  /**
   * Resets all allocations, clearing the allocated train numbers and associated dispatch systems.
   *
   * @since 0.0.1
   */
  public void resetAllocations() {
    allocatedTrainNumbers.clear();
    trainNumberToDispatchSystem.clear();
  }
}