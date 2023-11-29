package edu.ntnu.stud.traindispatchsystem;

import edu.ntnu.stud.register.TrainManager;
import java.time.LocalTime;
import java.util.Objects;


/**
 * The {@code TrainDispatchSystem} class represents a train dispatch system.
 * Each instance of this class represents a specific train departure with details such as
 * departure station, destination, departure time, line, track, and an allocated train number.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 * TrainDispatchSystem trainDispatch =
 * new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8, 55), "F1", 4);
 * </pre></blockquote>
 *
 * <p>The {@code TrainDispatchSystem} class includes methods for managing train departure
 * information and adding delays if a train is not on time.
 *
 * @author Karwan Shekhe
 * @version 0.0.8 (Version of this class)
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
   * Constructor to initialize a {@code TrainDispatchSystem} object.
   * Constructs a new {@code TrainDispatchSystem} by taking departure station,
   * destination, departure time, line, track.
   *
   * <p>The constructor sets invalid values when a given destination, train line, or train number
   * are not valid or null.
   * Currently, the train dispatch system only supports one departure station,
   * and that is Gjøvik.
   *
   * @param departureStation The departure station name.
   * @param destination      The destination station name.
   * @param departureTime    The departure time.
   * @param line             The train line name.
   * @param track            The track number.
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
   * Sets the name of the {@code departureStation} when a valid {@code String} is provided.
   * The {@code TrainDispatchSystem} is currently only supporting Gjøvik departure station.
   *
   * <p>The departure station is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDispatchSystem} object is valid to use.
   *
   * @param departureStation The name of the departure Station.
   * @since 0.0.2
   */
  public void setDepartureStation(String departureStation) {
    if (departureStation.equals("Gjøvik")) {
      this.departureStation = departureStation;
    } else {
      this.departureStation = "INVALID";
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
   * If the provided destination is not null,
   * it will be assigned to the object's destination property.
   * If the provided destination is null,
   * the destination property will be assigned the string "INVALID" instead.
   *
   * @param destination The destination to be set.
   * @since 0.0.2
   */

  public void setDestination(String destination) {
    this.destination = Objects.requireNonNullElse(destination, "INVALID");
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
   *
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
   * Sets a new departure time if there is any delay.
   *
   * @param delayMinutes The delay in minutes to set.
   * @since 0.0.1
   */
  public void setDelay(int delayMinutes) {
    // this.departureTime = departureTime.plusMinutes(delayMinutes);
    this.delay = delayMinutes;
  }

  /**
   * Provides the delay in minutes.
   *
   * @since 0.0.8
   */
  public int getDelay() {
    return delay;
  }

  /**
   * Sets the train line name.
   * If line is null, it sets a default value.
   *
   * @param line The train line name to set.
   * @since 0.0.1
   */
  public void setLine(String line) {
    this.line = (line != null) ? line : "Default";
  }

  /**
   * Gets the train line identifier.
   *
   * @return The train line identifier.
   * @since 0.0.1
   */
  public String getLine() {
    return line;
  }

  /**
   * Sets the track number within the range [1, 10].
   * Throws a IllegalStateException is the track number is less than 1 or more than 10.
   *
   * @param track                       The number to set
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
   * Gets the track number.
   *
   * @return The track number.
   * @since 0.0.1
   */
  public int getTrack() {
    return track;
  }

  /**
   * This method allows the assigment of a unique train number to a train departure.
   * First, the method checks if the train number has already been assigned
   * to this train departure. If the train number is already set for this departure, no action
   * is taken and the method returns.
   * If the train number is already assigned to another train departure in the system,
   * the method will throw an IllegalStateException
   * to indicate that the train number is already in use.
   *
   * @param trainNumber The train number to set.
   * @throws IllegalStateException If the train number is already in use.
   * @since 0.0.1
   */
  public void setTrainNumber(String trainNumber) {
    // Check if the train number is already assigned to another instance
    if (!trainManager.isTrainNumberAvailable(trainNumber)) {
      throw new IllegalStateException("Train number " + trainNumber + " is already allocated");
    } else {
      //trainManager.markTrainNumberAsAllocated(trainNumber, this);
      this.trainNumber = trainNumber;
    }
  }

  /**
   * Gets the allocated train number for the train departure.
   *
   * @return The allocated train number.
   * @since 0.0.1
   */
  public String getTrainNumber() {
    return trainNumber;
  }

  /**
   * Provides a string representation of the {@code TrainDispatchSystem} object.
   * The string representation consists of the departure station, destination, departure time,
   * line, track, and train number.
   *
   * @return The string representation of the {@code TrainDispatchSystem} object.
   * @since 0.0.7
   */
  public String getDetails() {
    return departureStation
        + " " + destination
        + " " + departureTime
        + " " + line
        + " " + track
        + " " + trainNumber;
  }
}