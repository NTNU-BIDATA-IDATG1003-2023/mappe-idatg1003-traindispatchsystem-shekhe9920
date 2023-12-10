package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import edu.ntnu.stud.utility.InputHandler;
import edu.ntnu.stud.utility.UserFeedback;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;


/**
 * The {@code UserOptionsManager} class manages user interactions and configurations
 * for a Train Dispatch System.
 * <p>
 * It provides methods for adding new train departures, assigning tracks, adding delays,
 * searching for train departures, sorting the departure list, and updating station times.</p>
 *
 * @author Karwan Shekhe
 * @version 0.0.8 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class UserOptionsManager {

  UserFeedback userFeedback = new UserFeedback();
  private final InputHandler inputHandler;
  private final InformationDisplay display;
  private final TrainRegister trainRegister;

  // Variables:
  private static final String TRAIN_NUMBER = "Train number";
  private static final String DESTINATION = "Destination";
  private static final String ENTER_TRAIN_NUMBER = "enterTrainNumber";
  private static final String ATTRIBUTE_NAME_TRAIN_NUMBER = "trainNumber";
  private static final String ERROR = "Error: ";
  private static final String UPDATED_SUCCESSFULLY = "updatedSuccessfully";



  /**
   * Constructs a new {@code UserOptionsManager} instance with the specified {@code TrainRegister}.
   *
   * @param trainRegister The train register to manage user options.
   * @since 0.0.1
   */
  public UserOptionsManager(TrainRegister trainRegister) {

    this.trainRegister = trainRegister;
    this.inputHandler = new InputHandler();
    trainRegister.initializeDepartureRegister();
    this.display = new InformationDisplay(trainRegister, System.out);
  }




  /**
   * Adds a new train departure to the system by prompting the user to input relevant information.
   *
   * <p><strong>The user is prompted to enter the following information:</strong></p>
   *
   * <blockquote><pre>
   *      - destination
   *      - departure time
   *      - train line
   *      - track number
   *      - train number
   * </pre></blockquote>
   *
   * @since 0.0.4
   */
  public void addNewTrainDeparture() {

    final String trainNumber;
    final String destination;
    final String line;

    userFeedback.logFeedback(ENTER_TRAIN_NUMBER);         // Prompt the user to enter a train number
    trainNumber = inputHandler.inputValidString(TRAIN_NUMBER);

    userFeedback.logFeedback("enterDestination");   // Prompt the user to enter a destination
    destination = inputHandler.inputValidString(DESTINATION);

    userFeedback.logFeedback("enterDepartureTime"); // Prompt the user to enter a departure time
    LocalTime departureTime
        = inputHandler.inputValidTime("departure time");

    userFeedback.logFeedback("enterTrainLine");
    line = inputHandler.inputValidString("train line"); // Prompt the user to enter a line

    int track;
    userFeedback.logFeedback("enterTrackNumber"); // Prompt the user to enter a track number
    track = inputHandler.inputValidInteger("track number");


    // Adding a new train departure to the system, if all the user inputs are valid:
    try {
      if (!trainRegister.addTrainDeparture(new TrainDispatchSystem("Gjøvik", destination,
          departureTime, line, track, trainNumber))) {
        userFeedback.logFeedback("trainNotAdded");
        return;
      }

      trainRegister.addTrainDeparture(new TrainDispatchSystem("Gjøvik", destination,
          departureTime, line, track, trainNumber));
      userFeedback.logFeedback("trainAdded");

    } catch (IllegalArgumentException | IllegalStateException e) {
      System.err.println(ERROR + e.getMessage());
      userFeedback.logFeedback("trainNotAdded");
    }
  }



  /**
   * Sets a delay for a specific train departure based on the user's input.
   *
   * @since 0.0.5
  */
  public void setDelayForTrainDeparture() {
    userFeedback.logFeedback(ENTER_TRAIN_NUMBER);
    String trainNumber = inputHandler.inputValidString(TRAIN_NUMBER);

    // Search for the train number in the train register:
    Iterator<TrainDispatchSystem> iterator =
        trainRegister.searchByAttributeAndValue(ATTRIBUTE_NAME_TRAIN_NUMBER, trainNumber);

    // Sets the delay for the train departure if the train number is found:
    if (iterator.hasNext()) {
      TrainDispatchSystem train = iterator.next();
      try {
        int delay = inputHandler.inputValidInteger("Delay");
        train.setDelay(delay);
        userFeedback.logFeedback("delayAdded");
      } catch (IllegalArgumentException e) {
        System.err.println(ERROR + e.getMessage());
        userFeedback.logFeedback("failedToSetDelay");
      }
    }
  }

  /**
   * Assigns a new track to a specific train departure based on the user's input.
   *
   * @since 0.0.7
   */
  public void assignNewTrackToTrainDeparture() {
    final String trainNumber;

    userFeedback.logFeedback(ENTER_TRAIN_NUMBER);
    trainNumber = inputHandler.inputValidString(TRAIN_NUMBER);

    // Search:
    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue(ATTRIBUTE_NAME_TRAIN_NUMBER, trainNumber);

    // Assigns a new track to the train departure if valid data is provided:
    try {
      resultsObtainedIterator.next().setTrack(inputHandler.inputValidInteger("Track"));
      userFeedback.logFeedback("trackAssigned");
    } catch (IllegalArgumentException e) {
      System.err.println(ERROR + e.getMessage());
    }

  }

  /**
   * Searches for a specific train departure based on the train number provided by the user.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnTrainNumber() {
    final String trainNumber;

    userFeedback.logFeedback(ENTER_TRAIN_NUMBER);
    trainNumber = inputHandler.inputValidString(TRAIN_NUMBER);

    // Search:
    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue(ATTRIBUTE_NAME_TRAIN_NUMBER, trainNumber);

    // Displays the search results:
    display.displayTrainDepartureDetailsForSearchResults(resultsObtainedIterator);
  }


  /**
   * Searches for a train departure based on the destination provided by the user.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnDestination() {
    final String destination;

    userFeedback.logFeedback("enterDestination");  // Prompt the user to enter a destination
    destination = inputHandler.inputValidString(DESTINATION);

    // Search:
    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue("destination", destination);

    // Displays the search results:
    display.displayTrainDepartureDetailsForSearchResults(resultsObtainedIterator);
  }

  /**
   * Searches for a train departure based on the departure time provided by the user.
   *
   * @since 0.0.4
   */
  public void searchDepartureBasedOnDepartureTime() {

    userFeedback.logFeedback("enterDepartureTime");
    // Validating the user's input:
    LocalTime departureTime = inputHandler.inputValidTime("departure time");

    Iterator<TrainDispatchSystem> resultsObtainedIterator =
        trainRegister.searchByAttributeAndValue("departureTime", departureTime.toString());

    // Displays the search results:
    display.displayTrainDepartureDetailsForSearchResults(resultsObtainedIterator);
  }

  /**
   * Sorts the departure table's/list based on their departure times in ascending order.
   *
   * @since 0.0.5
   */
  public void sortDepartureListBasedOnDepartureTime() {
    trainRegister.sortListByDepartureTime();             // Sorts the list
    userFeedback.logFeedback("sortedSuccessfully"); // Inform the user that the list is sorted
  }

  /**
   * Prompts the user to enter a train number and
   * removes the corresponding train departure from the system.
   * The method logs feedback messages for user interaction.
   *
   * @since 0.0.8
   */
  public void removeSpecificTrainDeparture() {
    final String trainNumber;

    userFeedback.logFeedback(ENTER_TRAIN_NUMBER);

    trainNumber = inputHandler.inputValidString(TRAIN_NUMBER);

    trainRegister.removeTrainByTrainNumber(trainNumber); // Removes the train departure

    userFeedback.logFeedback(UPDATED_SUCCESSFULLY);
  }



  /**
   * Removes all train departures from the train dispatch system.
   * This method logs a message, clears all train departures, and
   * displays an updated train dispatch list table.
   *
   * @since 0.0.8
   */
  public void removeAllTrainDepartures() {

    userFeedback.logFeedback("removingAllDepartures");
    trainRegister.removeAllTrains();  // Removes all train departures

    // Displays an updated train dispatch list table for confirmation:
    display.displayTrainDispatchListTable(trainRegister.getTrainDispatchListIterator());
    userFeedback.logFeedback(UPDATED_SUCCESSFULLY);

  }



  /**
   * Updates the train departures based on the station time,
   * and removing any departures whose departure time is
   * earlier than the station time.
   *
   * @since 0.0.5
   */
  public void removeExpiredTrainsAndUpdateList() {
    userFeedback.logFeedback("removingExpiredDepartures");

    // Displays the train departures before the station time:
    display.displayExpiredDeparturesBasedOnTime(
        trainRegister.getTrainDispatchListIterator(), trainRegister.getStationTime());

    // Removes the train departures before the station time:
    trainRegister.removeTrainsIfDepartureTimePassed();
    userFeedback.logFeedback(UPDATED_SUCCESSFULLY);
  }

  /**
   * Updates the station time based on the user's input.
   *
   * @since 0.0.7
   */
  public void updateStationTime() {
    while (true) {
      // Prompt the user to enter a new station time:
      userFeedback.logFeedback("enterNewStationTime");

      // Validating the user's input:
      try {
        String userInput = inputHandler.inputValidString("Station time");

        if (userInput.trim().isEmpty()) {
          System.out.println("No input detected. Please enter a valid time in HH:mm format.");
          continue;
        }

        // Update the station time if the user's input is valid:
        LocalTime newStationTime = LocalTime.parse(userInput);
        trainRegister.updateStationTime(newStationTime);

        userFeedback.logFeedback("stationTimeUpdated");
        display.displayCurrentStationAndTime();
        return;

        // Exception handling:
      } catch (DateTimeParseException e) {
        userFeedback.logFeedback("invalidTimeFormat");

      } catch (IllegalArgumentException e) {
        System.err.println(ERROR + e.getMessage());
        userFeedback.logFeedback("invalidTimeFormat");
      }
    }
  }
}
