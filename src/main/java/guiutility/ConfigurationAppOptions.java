package guiutility;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * The {@code ConfigurationAppOptions} class manages the configuration options and user interactions
 * for a Train Dispatch System.
 * This class provides methods for adding new train departures, assigning tracks, adding delays,
 * searching for train departures, and updating station times. It uses a set of TrainDispatchSystem
 * objects to store and manage train-related information.
 *
 * @author Karwan Shekhe
 * @version 0.0.2 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class ConfigurationAppOptions {
  private FeedBackMessages feedBackMessages;
  private Set<TrainDispatchSystem> trainDispatchList;
  private Scanner scanner;
  private Iterator<TrainDispatchSystem> iterator;

  /**
   * Constructs a new {@code ConfigurationAppOptions} instance with the specified feedback messages,
   * train dispatch list, and scanner.
   *
   * @param feedBackMessages The feedback messages utility.
   * @param trainDispatchList The set of TrainDispatchSystem instances representing train trips.
   * @param scanner The Scanner object for user input.
   * @since 0.0.1
   */
  public ConfigurationAppOptions(FeedBackMessages feedBackMessages,
      Set<TrainDispatchSystem> trainDispatchList, Scanner scanner) {
    this.feedBackMessages = feedBackMessages;
    this.trainDispatchList = trainDispatchList;
    this.scanner = scanner;
  }

  /**
   * Adds a new train departure to the system.
   * The user is prompted to enter the following information:
   * departure station, destination, departure time, train line, track number, and train number.
   *
   * @since 0.0.4
   */
  public void addNewTrainDeparture() {
    final String trainNumber;
    final String departureStation;
    final String destination;
    final String line;

    feedBackMessages.ENTER_TRAIN_NUMBER();
    trainNumber = scanner.nextLine();

    feedBackMessages.enterDepartureStation();
    departureStation = scanner.nextLine();

    feedBackMessages.enterDestination();
    destination = scanner.nextLine();

    feedBackMessages.ENTER_DEPARTURE_TIME();
    String departureTimeStr = scanner.nextLine();
    LocalTime departureTime;
    try {
      departureTime = LocalTime.parse(departureTimeStr);
    } catch (DateTimeParseException e) {
      feedBackMessages.INVALID_TIME_FORMAT();
      return;
    }

    feedBackMessages.ENTER_LINE();
    line = scanner.nextLine();

    feedBackMessages.ENTER_TRACK();
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }
    scanner.nextLine();

    TrainDispatchSystem trainDispatch = new TrainDispatchSystem(departureStation, destination,
        departureTime, line, track, trainNumber);
    trainDispatchList.add(trainDispatch);
  }

  /**
   * Assigns a track to a train departure. The user is prompted to enter the train number.
   *
   * @since 0.0.4
   */
  public void assignTrackToTrainDeparture() {
    feedBackMessages.ENTER_TRAIN_NUMBER();
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch == null) {
      feedBackMessages.TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND();
      return;
    }

    feedBackMessages.ENTER_TRACK();
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }

    trainDispatch.setTrack(track);
    feedBackMessages.TRACK_ASSIGNED_SUCCESSFULLY();
  }

  /**
   * Adds a delay to a train departure. The user is prompted to enter the train number.
   *
   * @since 0.0.4
   */
  public void addDelayToTrainDeparture() {
    feedBackMessages.ENTER_TRAIN_NUMBER();
    String trainNumber = scanner.nextLine();

    // Uses "searchTrainDispatchByAttribute" method to find the train departure.
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch == null) {
      feedBackMessages.TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND();
      return;
    }

    feedBackMessages.ENTER_DELAY();
    int delay;
    try {
      delay = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for delay. Please enter a valid number.");
      return;
    }

    trainDispatch.setDelay(delay);
    feedBackMessages.DELAY_ADDED_SUCCESSFULLY();
  }

  /**
   * Searches for a train departure based on train number. The user is prompted to enter the
   * train number.
   *
   * @since 0.0.4
   */
  public void searchTrainDepartureByTrainNumber() {
    feedBackMessages.ENTER_TRAIN_NUMBER();
    String trainNumber = scanner.nextLine();

    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch != null) {
      feedBackMessages.displayTrainDepartureDetails(trainDispatch);
    } else {
      System.out.println("Train departure not found.");
    }
  }

  /**
   * Searches for a train departure based on destination. The user is prompted to enter the
   * destination.
   *
   * @since 0.0.4
   */
  public void searchTrainDepartureByDestination() {
    feedBackMessages.enterDestination();
    String destination = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("Destination", destination);

    if (trainDispatch != null) {
      feedBackMessages.displayTrainDepartureDetails(trainDispatch);
    } else {
      System.out.println("Train departure not found");
    }
  }

  /**
   * Searches for a train departure based on departure time. The user is prompted to enter the
   * departure time.
   *
   * @since 0.0.4
   */
  public void searchTrainDepartureByDepartureTime() {
    feedBackMessages.ENTER_DEPARTURE_TIME();
    String departureTimeStr = scanner.nextLine();
    LocalTime departureTime;

    try {
      departureTime = LocalTime.parse(departureTimeStr);
    } catch (DateTimeParseException e) {
      feedBackMessages.INVALID_TIME_FORMAT();
      return;
    }

    String departureTimeString = departureTime.toString();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("DepartureTime", departureTimeString);

    if (trainDispatch != null) {
      feedBackMessages.displayTrainDepartureDetails(trainDispatch);
    } else {
      System.out.println("Train departure not found");
    }
  }

  /**
   * Updates the station time from user input. The user is prompted to enter the station time.
   *
   * @since 0.0.4
   */
  public void updateStationTimeFromUserInput() {
    // Prompts the user to enter the train number for which they want to update the station time
    feedBackMessages.ENTER_TRAIN_NUMBER_TO_UPDATE_STATION_TIME();
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch == null) {
      feedBackMessages.TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND();
      return;
    }

    System.out.println("Please enter the station time (HH:mm): ");
    String newStationTimeStr = scanner.nextLine();
    LocalTime newStationTime;
    try {
      newStationTime = LocalTime.parse(newStationTimeStr);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid time format. Please use HH:mm format (e.g., 08:30).");
      return;
    }

    trainDispatch.setDepartureTime(newStationTime);
    System.out.println("Station time updated successfully.");
  }

  /**
   * Removes a train departure from the system. The user is prompted to enter the train number.
   * @since 0.0.2 (Version of the FeedBackMessages class where this method was introduced)
   */
  public void removeTrainDispatchByTrainNumber() {
    feedBackMessages.ENTER_TRAIN_NUMBER();
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch == null) {
      feedBackMessages.TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND();
      return;
    }
    trainDispatchList.remove(trainDispatch);
    System.out.println("Train departure removed successfully.");
  }

  /**
   * Updates the station time for all train departures that have a departure time before
   * the current time.
   * @since 0.0.2 (Version of the FeedBackMessages class where this method was introduced)
   */
  public void removeTrainDepartureByCurrentTime() {
    LocalTime currentTime = LocalTime.now();

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatchSystem = iterator.next();

      if (currentTime.isAfter(trainDispatchSystem.getDepartureTime())) {
        iterator.remove();
      }
    }

    System.out.println("Departure list updated successfully.");
  }


  /**
   * Searches for a specific train dispatch record within a list of train dispatch system based on
   * a given attribute, and it's corresponding value.
   * This method iterates through a list of train dispatch systems and compares the provided
   * attribute and value with each train dispatch record.
   * It returns the first matching record found.
   *
   * @param attribute The attribute to search for. It can be one of the following: "TrainNumber",
   *                  "Destination", or "DepartureTime".
   * @param value The value to match against the specified attribute.
   * @return The matching train dispatch system or null if not found.
   * @since 0.0.6
   */
  public TrainDispatchSystem searchTrainDispatchByAttribute(String attribute, String value) {
    TrainDispatchSystem trainDispatch = null;

    for (TrainDispatchSystem trainDispatchSystem : trainDispatchList) {
      boolean isMatch = false;

      if (attribute.equalsIgnoreCase("TrainNumber")) {
        isMatch = trainDispatchSystem.getTrainNumber().equals(value);
      } else if (attribute.equalsIgnoreCase("Destination")) {
        isMatch = trainDispatchSystem.getDestination().equals(value);
      } else if (attribute.equalsIgnoreCase("DepartureTime")) {
        LocalTime departureTime = LocalTime.parse(value);
        isMatch = trainDispatchSystem.getDepartureTime().equals(departureTime);
      }

      if (isMatch) {
        trainDispatch = trainDispatchSystem;
        break;
      }
    }

    return trainDispatch;
  }

}
