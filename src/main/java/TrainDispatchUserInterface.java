import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import trainmanager.TrainManager;
import guiutility.FeedBackMessages;


/**
 * The {@code TrainDispatchUserInterface} class manages the user interface of the
 * Train Dispatch System.
 * It is responsible for presenting train dispatch information.
 * This class collaborates with the {@code TrainManager} and {@code TrainDispatchSystem}
 * classes to provide a comprehensive user experience for handling and viewing train-related data.
 *
 * <p>The primary functionalities of this class include:
 *
 * <blockquote><pre>
 *  1. Displaying train dispatch information in a tabular format,
 *     including departure stations, destinations, departure times, tracks,
 *     train lines, and allocated train numbers.
 *  2. Adding new train departures to the system.
 *  3. Assigning tracks to train departures.
 *  4. Adding delays to train departures.
 *  5. Searching for train departures based on train numbers, destinations,
 *     or departure times.
 *  6. Updating station times based on user input.
 * </pre></blockquote>
 *
 * <p>To utilize this class, you can create an instance of {@code TrainDispatchUserInterface} and
 * call the start method to present train dispatch information.
 * Ensure that train trips are initialized using the {@code TrainDispatchInitializer} class before
 * using this user interface
 *
 * @author Karwan Shekhe
 * @version 0.0.7 (Version of this class.)
 * @since 0.0.3 (This class was introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class TrainDispatchUserInterface {
  final FeedBackMessages feedBackMessages;
  private HashSet<TrainDispatchSystem> trainDispatchList;
  private TrainManager trainManager;
  private final Scanner scanner;


  /**
   * Initializes the TrainDispatchUserInterface with a collection of train dispatch systems.
   *
   * @param trainDispatchList A HashSet of TrainDispatchSystem instances representing train trips.
   * @since 0.0.3
   */
  public TrainDispatchUserInterface(HashSet<TrainDispatchSystem> trainDispatchList) {
    this.trainDispatchList = trainDispatchList;
    this.trainManager = new TrainManager();
    this.scanner = new Scanner(System.in);
    feedBackMessages = new FeedBackMessages(this.trainDispatchList);
  }

  /**
   * Starts the user interface.
   *
   * @since 0.0.1
   */
  public void start() {
    feedBackMessages.displayCurrentTime();
    feedBackMessages.displayWelcomeText();
    int choice;
    do {
      choice = displayMenu();
      processUserChoice(choice);
    } while (choice != 9);
    scanner.close();
  }


  /**
   * Displays the main menu, ang get the user's choice.
   *
   * @return An integer representing the user's choice (1-9).
   * @since 0.0.4
   */
  public int displayMenu() {
    Scanner scanner = new Scanner(System.in);
    feedBackMessages.displayMenuContent();

    int option;
    try {
      option = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      feedBackMessages.INVALID_CHOICE();
      return displayMenu();
    }

    return option;
  }


  /**
   * Processes the user's choice.
   *
   * @param choice The user's choice (1-9).
   * @since 0.0.4
   */
  // kansje bytte til en fornuftig navn
  public void processUserChoice(int choice) {
    while (choice != 9) {
      switch (choice) {
        case 1:

          feedBackMessages.displayTrainDepartureInformation(); // View train dispatch information
          break;
        case 2:

          addNewTrainDeparture();                  // Add a new train departure
          break;
        case 3:

          assignTrackToTrainDeparture();           // Assign a track to a train departure
          break;
        case 4:

          addDelayToTrainDeparture();              // Add a delay to a train departure
          break;
        case 5:

          searchTrainDepartureByTrainNumber();     // Search for a train departure based on train number
          break;
        case 6:

          searchTrainDepartureByDestination();     // Search for a train departure based on destination
          break;
        case 7:

          searchTrainDepartureByDepartureTime();   // Search for a train departure based on departure time
          break;
        case 8:
          updateStationTimeFromUserInput();        // Update station time from user input
          break;
        case 9:
          // Exit the application
          feedBackMessages.EXITING_APPLICATION();  // Display a message before exiting
          return;
        default:
          feedBackMessages.INVALID_CHOICE();       // Display an error message for invalid choice
          break;
      }

      feedBackMessages.CONTINUE_OR_EXIT();         // Ask the user if they want to continue or exit.
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        feedBackMessages.INVALID_CHOICE();        // Display an error message for invalid choice
      }
    }
  }

  /**
   * Adds a new train departure to the system.
   * The user is prompted to enter the following information:
   * departure station, destination, departure time, train line, track number, and train number.
   *
   * @since 0.0.4
   */
  public void addNewTrainDeparture() {
    feedBackMessages.enterDepartureStation();
    String departureStation = scanner.nextLine();

    feedBackMessages.enterDestination();
    String destination = scanner.nextLine();

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
    String line = scanner.nextLine();

    feedBackMessages.ENTER_TRACK();
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }
    scanner.nextLine();

    feedBackMessages.ENTER_TRAIN_NUMBER();
    String trainNumber = scanner.nextLine();

    TrainDispatchSystem trainDispatch = new TrainDispatchSystem(departureStation, destination,
        departureTime, line, track);
    trainDispatchList.add(trainDispatch);
    trainDispatch.setTrainNumber(trainNumber);
    trainManager.markTrainNumberAsAllocated(trainNumber, trainDispatch);
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

    trainManager.allocateTrainNumbers(trainNumber, trainDispatch);
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
    // Prompt the user to enter the train number for which they want to update the station time
    feedBackMessages.ENTER_TRAIN_NUMBER_TO_UPDATE_STATION_TIME();
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute(FeedBackMessages.TRAIN_NUMBER, trainNumber);

    if (trainDispatch == null) {
      System.out.println("Train with the specified train number was not found.");
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
