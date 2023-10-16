package edu.ntnu.stud;

import java.time.LocalTime;


/**
 * The {@code TrainDispatchSystem} class represents a train dispatch system.
 * All the trains in the {@code TrainDispatchSystem} hava a departure station,
 * destination,departure time, line, track and the train number.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 * TrainDispatchSystem trainDispatch =
 * new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8, 55), "F1", 4, 123);
 * </pre></blockquote>
 *
 * <p>The class {@code TrainDispatchSystem} includes methods for adding delays,
 * if a train is not on time.
 *
 * @author Karwan Shekhe
 * @version 0.0.2
 * @since 0.0.1
 */
public class TrainDispatchSystem {
  private String departureStation;
  private String destination;
  private LocalTime departureTime;
  private String line;
  private int track;
  private int trainNumber; // Store the train number directly
  private boolean trainNumberSet = false; // Boolean flag to track if train number is set

  /**
  * Constructor to initialize a TrainDispatchSystem object.
  * Constructs a new {@code TrainDispatchSystem} by taking departure station,
  * destination,departure time, line, track and the train number.
  *
  * <p>The constructor sets invalid values when a given destination, train line or train number
  * are not valid or null. Currently, the train dispatch system only supports one departure station,
  * and that is Gjøvik.
  *
  *
  * @param departureStation The departure station name.
  * @param destination      The destination station name.
  * @param departureTime    The departure time.
  * @param line             The train line name.
  * @param track            The track number.
  * @param trainNumber      The train number.
  * @since 0.0.1
  */

  public TrainDispatchSystem(String departureStation, String destination,
                               LocalTime departureTime, String line, int track, int trainNumber) {
    setDepartureStation(departureStation);
    setDepartureTime(departureTime);
    setDestination(destination);
    setLine(line);
    setTrack(track);
    setTrainNumber(trainNumber);
  }


  public int getTrainNumber() {
    return trainNumber;
  }

  public String getLine() {
    return line;
  }

  public int getTrack() {
    return track;
  }

  // Setters methods

  /**
   * Sets the name of the {@code departureStation} when a valid {@code String} is provided.
   * The {@code TrainDispatchSystem} is currently only supporting Gjøvik departure station.
   *
   * <p>The departure station is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDispatchSystem} object is valid to use.
   *
   * @param departureStation The name of the departure Station.
   * @since 0.0.2
   *
   */

  public void setDepartureStation(String departureStation) {
    if (departureStation.equals("Gjøvik")) {
      this.departureStation = departureStation;
    } else {
      this.departureStation = "INVALID";
    }
  }

  /**
   * Provides the name of the departure station.
   *
   * @return The name of the departure station
   * @since 0.0.1
   */

  public String getDepartureStation() {
    return departureStation;
  }

  /**
   * Sets the name of the {@code destination} when a valid {@code String} is provided.
   *
   * <p>The destination is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDispatchSystem} object is valid to use.
   *
   * @param destination The name of the destination.
   * @since 0.0.2
   *
   */

  public void setDestination(String destination) {
    if (destination != null) {
      this.destination = destination;
    } else {
      this.destination = "INVALID";
    }
  }

  /**
   * Provides the name of the destination.
   *
   * @return The name of the destination
   * @since 0.0.1
   */
  public String getDestination() {
    return destination;
  }

  /**
  * Sets the departure time.
  *
  * @param departureTime The departure time to set
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
  * @param delayMinutes The delay in minutes to set
  * @since 0.0.1
  */

  public void setDelay(int delayMinutes) {
    this.departureTime = departureTime.plusMinutes(delayMinutes);
  }

  /**
  * Sets the train number if it hasn't been set already.
  * Throws a IllegalStateException if the train number is already been sett.
  *
  * @param trainNumber                 The train number to set.
  * @throws IllegalArgumentException   If the train number has already been set.
  * @since 0.0.1
  */

  public void setTrainNumber(int trainNumber) {
    if (!trainNumberSet) {
      this.trainNumber = trainNumber;
      trainNumberSet = true;
    } else {
      throw new IllegalStateException("Train number has already been set.");
    }
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
  * Sets the train line name.
  * If line is null, it sets a default value.
  *
  * @param line The train line name to set.
  * @since 0.0.1
  */
  public void setLine(String line) {
    this.line = (line != null) ? line : "Default"; // Sets default value if line is null
  }

  /**
  * Generates a string representation of the TrainDispatchSystem object.
  *
  * @return A string with details about the train dispatch.
  */

  @Override
  public String toString() {
    return "Departure from " + departureStation + " to "
              + destination + " at " + departureTime + "|| Line: "
              + line + " ||" + "|| Track: " + track + " ||" + "Train number: " + trainNumber;
  }
}