package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainManager;
import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import edu.ntnu.stud.utility.InputHandler;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The {@code ConfigurationAppOptions} class manages the configuration options and user interactions
 * for a Train Dispatch System.
 * This class provides methods for adding new train departures, assigning tracks, adding delays,
 * searching for train departures, and updating station times. It uses a set of TrainDispatchSystem
 * objects to store and manage train-related information.
 *
 * @author Karwan Shekhe
 * @version 0.0.6 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class ConfigureUserOptions {
  private final Scanner scanner;
  private final TrainManager trainManager;
  private final InputHandler inputHandler;
  private final InformationDisplay display;
  private final TrainRegister trainRegister;


  /**
   * Constructs a new {@code ConfigurationUserOptions}
   * instance with the specified feedback messages,
   * train dispatch list, and scanner.
   *
   * @since 0.0.1
   */
  public ConfigureUserOptions(TrainRegister trainRegister, TrainManager trainManager) {
    this.trainManager = trainManager;

    this.trainRegister = trainRegister;
    scanner = new Scanner(System.in);
    this.inputHandler = new InputHandler();
    trainRegister.initializeDepartureRegister();
    this.display = new InformationDisplay(trainRegister.getTrainDispatchListIterator());
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

    System.out.println("Please enter the train number: ");
    trainNumber = scanner.nextLine();

    System.out.println("Please enter the departure station: ");
    departureStation = scanner.nextLine();

    System.out.println("Please enter the destination: ");
    destination = scanner.nextLine();

    System.out.println("Please enter the departure time: ");
    String departureTimeStr = scanner.nextLine();
    LocalTime departureTime;

    try {
      departureTime = LocalTime.parse(departureTimeStr);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid time format. Please use HH:mm format (e.g., 08:30).");
      return;
    }

    System.out.println("Please enter the train line: ");
    line = scanner.nextLine();

    System.out.println("Please enter the track number: ");
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }
    scanner.nextLine();

    if (!trainRegister.addTrainDeparture(new TrainDispatchSystem(departureStation, destination,
        departureTime, line, track, trainNumber))) {
      System.out.println("Error: Train number is not available or already allocated.");
      return;
    }

    System.out.println("Train added successfully!");
  }

  /**
   *
   *
   * @since 0.0.5
  */
  public void setDelayForTrainDeparture() {
    String trainNumber = inputHandler.inputString("train number");
    int delay = inputHandler.inputInteger("delay");

    TrainDispatchSystem train =
        trainRegister.searchByAttributeAndValue("trainNumber", trainNumber).next();

    if (train != null) {
      train.setDelay(delay);
      System.out.println("Delay set successfully.");
    } else {
      System.out.println("Train not found.");
    }
  }

  /**
   * Searches for a train departure based on train number. The user is prompted to enter the
   * train number.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnTrainNumber() {
    String trainNumber = inputHandler.inputString("train number");

    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue("trainNumber", trainNumber);
    display.displayTrainDepartureDetails(resultsObtainedIterator);

  }


  /**
   * Searches for a train departure based on destination. The user is prompted to enter the
   * destination.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnDestination() {
    String destination = inputHandler.inputString("destination");
     Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue("destination", destination);
     display.displayTrainDepartureDetails(resultsObtainedIterator);
  }

  /**
   * Searches for a train departure based on departure time. The user is prompted to enter the
   * departure time.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnDepartureTime() {
    String inputTimeStr =
        inputHandler.inputString(
            "departure time || Please use HH:mm format (e.g., 08:30) ||");
    LocalTime departureTime;

    try {
      departureTime = LocalTime.parse(inputTimeStr);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid time format. Please use HH:mm format (e.g., 08:30).");
      return;
    }

    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue("departureTime", departureTime.toString());
    display.displayTrainDepartureDetails(resultsObtainedIterator);

  }

  /**
   * Sort the train departures based on departure time.
   *
   * @since 0.0.5
   */
  public void sortDepartureListBasedOnDepartureTime() {
    trainRegister.sortListByDepartureTime();
    System.out.println("Train departures sorted successfully.");
  }
  /**
   * Updates the train departure list based on current time.
   *
   * @since 0.0.5
   */
  public void updateTrainDepartureList() {
    trainRegister.removeTrainsIfDepartureTimePassed();
    System.out.println("Train departures updated successfully.");
  }
  // Commit test
}
