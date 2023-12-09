package edu.ntnu.stud.traindispatchsystem;

import edu.ntnu.stud.register.TrainManager;
import java.time.LocalTime;

/**
 * The {@code TrainDispatchSystem} class represents a train dispatch system.
 * Each instance of this class represents a specific train departure with details such as
 * departure station, destination, departure time, line, track, an allocated train number
 * and delay information.
 * It ensures that the provided data adheres to
 * specific criteria to maintain consistency within the Train Dispatch System.
 *
 * <p><strong>Example Usage:</strong></p>
 * <blockquote><pre>
 *{@code
 * // Creating a TrainDispatchSystem instance with sample data:
 *
 * TrainDispatchSystem exampleTrain = new TrainDispatchSystem("Oslo", "Bergen",
 *     LocalTime.of(14, 30), "F1", 3, "T123");
 * }
 *
 * </pre></blockquote>
 *
 * <p>The {@code TrainDispatchSystem} class includes methods for managing train departure
 * information and adding delays if a train is not on time.
 *
 * @author Karwan Shekhe
 * @version 0.0.9 (Version of this class)
 * @since 0.0.1 (Introduced in Version 0.0.1 of the Train Dispatch System application)
 */
public class TrainDispatchSystem {

  private String departureStation;             // The departure station for the train.
  private String destination;                  // The destination of the train.
  private LocalTime departureTime;             // The time of departure.
  private String line;                         // The train line identifier.
  private int track;                           // The track number.
  private String trainNumber;                  // The allocated train number.
  private int delay;                           // The delay in minutes.
  private static final TrainManager trainManager = new TrainManager();

  /**
   * Constructs a {@code TrainDispatchSystem} instance with provided details.
   *
   * @param departureStation The departure station of the train.
   * @param destination The destination of the train.
   * @param departureTime The time of departure.
   * @param line The train line identifier.
   * @param track The track number.
   * @param trainNumber The allocated train number.
   * @throws IllegalArgumentException If any input parameters violate specified criteria.
   * @since 0.0.1
   */
  public TrainDispatchSystem(String departureStation, String destination,
      LocalTime departureTime, String line, int track, String trainNumber) {

    setDepartureStation(departureStation);
    setDepartureTime(departureTime);
    setDestination(destination);
    setLine(line);
    setTrack(track);
    setTrainNumber(trainNumber);

  }

  /**
   * Sets the departure station for the train.
   *
   * @param departureStation The departure station to be set.
   * @throws IllegalArgumentException If the departure station is null or
   *                                  contains non-alphabetic characters.
   * @since 0.0.2
   */
  public void setDepartureStation(String departureStation) {
    if (departureStation != null && departureStation.matches("[a-zA-ZæøåÆØÅ]+")) {
      this.departureStation = departureStation;
    } else {
      throw new IllegalArgumentException(
          "Departure Station must contain only alphabets and cannot be null.");
    }
  }

  /**
   * Provides the departure station.
   *
   * @return The name of the departure station.
   * @since 0.0.1
   */
  public String getDepartureStation() {
    return departureStation;
  }

  /**
   * Sets the destination for a specific object.
   *
   * @param destination The destination to be set.
   * @throws IllegalArgumentException If the departure station is null or
   *                                  contains non-alphabetic characters.
   * @since 0.0.2
   */
  public void setDestination(String destination) {
    if (destination != null && destination.matches("[a-zA-ZæøåÆØÅ]+")) {
      this.destination = destination;
    } else {
      throw new IllegalArgumentException(
          "Destination must contain only alphabets and cannot be null.");
    }
  }

  /**
   * Provides the destination of the train.
   *
   * @return The destination.
   * @since 0.0.1
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Sets the departure time.
   *
   * @param departureTime The departure time to set
   * @since 0.0.2
   */
  public void setDepartureTime(LocalTime departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Provides the departure time of a train.
   *
   * @return The departure time.
   * @since 0.0.1
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }


  /**
   * Sets a delay in minutes for a specific train departure.
   *
   * @param delayMinutes The delay in minutes to set.
   * @since 0.0.1
   */
  public void setDelay(int delayMinutes) {
    if (delayMinutes >= 0 && delayMinutes <= 60) {
      this.delay = delayMinutes;
    } else {
      throw new
          IllegalArgumentException(" The delay should be within the range of 1 to 60 minutes. ");
    }
  }

  /**
   * Provides the delay in minutes (int).
   *
   * @return The delay in minutes.
   * @since 0.0.8
   */
  public int getDelay() {
    return delay;
  }

  /**
   * Sets the train line identifier.
   *
   * @param line The train line identifier to set.
   * @throws IllegalArgumentException If the line is null.
   * @since 0.0.1
   */
  public void setLine(String line) {
    if (line != null) {
      this.line = line;
    } else {
      throw new IllegalArgumentException("Line cannot be null");
    }
  }

  /**
   * Provides the train line identifier.
   *
   * @return The train line identifier.
   * @since 0.0.1
   */
  public String getLine() {
    return line;
  }

  /**
   * Sets the track number within the range [1, 10].
   *
   * @param track                       The track number to set.
   * @throws IllegalArgumentException   If the track number is outside the valid range.
   * @since 0.0.1
   */
  public void setTrack(int track) {
    if (track >= 1 && track <= 10) {
      this.track = track;
    } else {
      throw new IllegalArgumentException("Track must be between 1 and 10 inclusive.");
    }
  }

  /**
   * Provides the track number.
   *
   * @return The track number.
   * @since 0.0.1
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the allocated train number for the train.
   *
   * @param trainNumber The train number to be set.
   * @throws IllegalStateException If the train number is already allocated to another instance.
   * @throws IllegalArgumentException If the train number is null.
   * @since 0.0.1
   */
  public void setTrainNumber(String trainNumber) {
    if (trainNumber == null) {
      throw new IllegalArgumentException("Train number cannot be null");
    }
    // Checking if the train number is already assigned to another instance
    if (!trainManager.isTrainNumberAvailable(trainNumber)) {
      throw new IllegalStateException("Train number " + trainNumber + " is already allocated");
    } else {
      this.trainNumber = trainNumber;
    }
  }

  /**
   * Gets the allocated train number of the train.
   *
   * @return The allocated train number.
   * @since 0.0.1
   */
  public String getTrainNumber() {
    return trainNumber;
  }
}