import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import trainmanager.TrainManager;


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
 * @version 0.0.6
 * @since 0.0.1
 */
public class TrainDispatchUserInterface {
  private HashSet<TrainDispatchSystem> trainDispatchList;
  private TrainManager trainManager;
  private Scanner scanner;

  /**
   * Initializes the TrainDispatchUserInterface with a collection of train dispatch systems.
   *
   * @param trainDispatchList A HashSet of TrainDispatchSystem instances representing train trips.
   * @since 0.0.3
   */
  public TrainDispatchUserInterface(HashSet<TrainDispatchSystem> trainDispatchList) {
    this.trainDispatchList = trainDispatchList;
    this.trainManager = new TrainManager(); // Create an instance of TrainManager
    this.scanner = new Scanner(System.in); // Create an instance of Scanner
  }


  /**
   * Starts the user interface.
   *
   * @since 0.0.1
   */
  public void start() {
    displayCurrentTime();
    displayWelcomeText();

    int choice;
    do {
      choice = displayMenu();
      processUserChoice(choice);
    } while (choice != 9);
    scanner.close();
  }

  /**
   * Displays the current time.
   *
   * @since 0.0.4
   */
  public void displayCurrentTime() {
    LocalTime currentTime = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);
    LocalDate currentDate = LocalDate.now();
    System.out.println("| Gjøvik Station | " + formattedTime + " | " + currentDate + "|");
  }

  /**
   * Displays a welcome message to the user.
   *
   * @since 0.0.4
   */
  public void displayWelcomeText() {
    System.out.println("================================================");
    System.out.println("Welcome to the Train Dispatch System Application");
    System.out.println("================================================");
  }

  /**
   * Displays the main menu, ang get the user's choice.
   *
   * @return An integer representing the user's choice (1-9).
   * @since 0.0.4
   */
  public int displayMenu() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n\nMain Menu");
    System.out.println("Pleas select an option from the menu below: ");
    System.out.println("================================================");
    System.out.println("Please select an option from the menu below:");
    System.out.println("1. View train dispatch information");
    System.out.println("2. Add a new train departure");
    System.out.println("3. Assign a track to train departure");
    System.out.println("4. Add a delay to a train departure");
    System.out.println("5. Search for a train departure based on train number");
    System.out.println("6. Search for a train departure based on destination");
    System.out.println("7. Search for a train departure based on departure time");
    System.out.println("8. Update station time from user input");
    System.out.println("9. Exit the application");
    System.out.println("================================================");
    System.out.print("Input your choice (1-9) below: ");
    int option;
    try {
      option = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid choice. Please select a valid option.");
      return displayMenu();
    }
    return option;
  }

  /**
   * Displays train departure details for a specific train departure.
   *
   * @param trainDispatch The train departure to display details for.
   * @since 0.0.5
   */
  public void displayTrainDepartureDetails(TrainDispatchSystem trainDispatch) {
    System.out.println("Train departure details for train "
        + trainDispatch.getTrainNumber() +  " :");
    System.out.println("Departure station: " + trainDispatch.getDepartureStation());
    System.out.println("Departure Time: " + trainDispatch.getDepartureTime());
    System.out.println("Track: " + trainDispatch.getTrack());
    System.out.println("Line: " + trainDispatch.getLine());
  }

  /**
   * Processes the user's choice.
   *
   * @param choice The user's choice (1-9).
   * @since 0.0.4
   */
  public void processUserChoice(int choice) {
    while (choice != 9) {
      switch (choice) {
        case 1:
          // View train dispatch information
          displayTrainDepartureInformation();
          break;
        case 2:
          // Add a new train departure
          addNewTrainDeparture();
          break;
        case 3:
          // Assign a track to a train departure
          assignTrackToTrainDeparture();
          break;
        case 4:
          // Add a delay to a train departure
          addDelayToTrainDeparture();
          break;
        case 5:
          // Search for a train departure based on train number
          searchTrainDepartureByTrainNumber();
          break;
        case 6:
          // Search for a train departure based on destination
          searchTrainDepartureByDestination();
          break;
        case 7:
          // Search for a train departure based on departure time
          searchTrainDepartureByDepartureTime();
          break;
        case 8:
          // Update station time from user input
          updateStationTimeFromUserInput();
          break;
        case 9:
          // Exit the application
          System.out.println("Exiting the application. Goodbye!");
          return;
        default:
          System.out.println("Invalid choice. Please select a valid option.");
          break;
      }

      System.out.println("Do you want to go back to the main menu (press 0), or to exit the application (press 9)?");
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Please select a valid option.");
      }
    }
  }

  /**
   * Starts the user interface for displaying train dispatch information in a tabular format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * @since 0.0.4
   */
  public void displayTrainDepartureInformation() {
    // Display the table header
    System.out.println("----------------------------------------"
        + "------------------------------------------");
    System.out.println("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |");
    System.out.println("-----------------------------------"
        + "-----------------------------------------------");

    // Display information about train trips in a tabular format
    for (TrainDispatchSystem trainDispatch : trainDispatchList) {

      System.out.printf("| %-17s | %-11s | %-13s  | %-5d | %-4s | %-12s |%n",
          trainDispatch.getDepartureStation(),
          trainDispatch.getDestination(),
          trainDispatch.getDepartureTime(),
          trainDispatch.getTrack(),
          trainDispatch.getLine(),
          trainDispatch.getTrainNumber());
    }

    // Display the table footer
    System.out.println("------------------------------------------"
        + "----------------------------------------");
  }

  /**
   * Adds a new train departure to the system.
   * The user is prompted to enter the following information:
   * departure station, destination, departure time, train line, track number, and train number.
   *
   * @since 0.0.4
   */
  public void addNewTrainDeparture() {
    System.out.println("Please enter the departure station: ");
    String departureStation = scanner.nextLine();
    System.out.println("Please enter the destination: ");
    String destination = scanner.nextLine();
    System.out.println("Please enter the departure time (HH:mm): ");
    String departureTimeStr = scanner.nextLine();
    LocalTime departureTime;
    try {
      departureTime = LocalTime.parse(departureTimeStr);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid time format. Please use HH:mm format (e.g., 08:30).");
      return;
    }
    System.out.println("Please enter the train line: ");
    String line = scanner.nextLine();
    System.out.println("Please enter the track number: ");
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }
    scanner.nextLine(); // Consume the newline character
    System.out.println("Please enter the train number: ");
    String trainNumber = scanner.nextLine();

    TrainDispatchSystem trainDispatch = new TrainDispatchSystem(departureStation, destination,
        departureTime, line, track);
    trainDispatch.setTrainNumber(trainNumber);
    trainDispatchList.add(trainDispatch);
    trainManager.markTrainNumberAsAllocated(trainNumber, trainDispatch);
  }

  /**
   * Assigns a track to a train departure. The user is prompted to enter the train number.
   *
   * @since 0.0.4
   */
  public void assignTrackToTrainDeparture() {
    System.out.println("Please enter the train number: ");
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("TrainNumber", trainNumber);

    if (trainDispatch == null) {
      System.out.println("Train with the specified train number was not found.");
      return;
    }
    System.out.println("Please enter the track number: ");
    int track;
    try {
      track = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for track number. Please enter a valid number.");
      return;
    }

    trainDispatch.setTrack(track);
    System.out.println("Track assigned successfully.");
  }

  /**
   * Adds a delay to a train departure. The user is prompted to enter the train number.
   *
   * @since 0.0.4
   */
  public void addDelayToTrainDeparture() {
    System.out.println("Please enter the train number: ");
    String trainNumber = scanner.nextLine();

    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("TrainNumber", trainNumber);

    if (trainDispatch == null) {
      System.out.println("Train with the specified train number was not found.");
      return;
    }

    System.out.println("Please enter the delay in minutes: ");
    int delay;
    try {
      delay = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input for delay. Please enter a valid number.");
      return;
    }

    trainManager.allocateTrainNumbers(trainNumber, trainDispatch);
    trainDispatch.setDelay(delay);
    System.out.println("Delay added successfully.");
  }

  /**
   * Searches for a train departure based on train number. The user is prompted to enter the
   * train number.
   *
   * @since 0.0.4
   */
  public void searchTrainDepartureByTrainNumber() {
    System.out.println("Please enter the train number: ");
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("TrainNumber", trainNumber);

    if (trainDispatch != null) {
      displayTrainDepartureDetails(trainDispatch);
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
    System.out.println("Please enter the destination: ");
    String destination = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("Destination", destination);

    if (trainDispatch != null) {
      displayTrainDepartureDetails(trainDispatch);
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
    System.out.println("Please enter the departure time (HH:mm): ");
    String departureTimeStr = scanner.nextLine();
    LocalTime departureTime;

    try {
      departureTime = LocalTime.parse(departureTimeStr);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid time format. Please use HH:mm format (e.g., 08:30).");
      return;
    }

    String departureTimeString = departureTime.toString();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("DepartureTime", departureTimeString);

    if (trainDispatch != null) {
      displayTrainDepartureDetails(trainDispatch);
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
    System.out.println("Please enter the train number "
        + "for which you want to update the station time: ");
    String trainNumber = scanner.nextLine();
    TrainDispatchSystem trainDispatch =
        searchTrainDispatchByAttribute("TrainNumber", trainNumber);

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
      if (attribute.equalsIgnoreCase("TrainNumber")
          && trainDispatchSystem.getTrainNumber().equals(value)) {
        trainDispatch = trainDispatchSystem;
        break;
      } else if (attribute.equalsIgnoreCase("Destination") &&
          trainDispatchSystem.getDestination().equals(value)) {
        trainDispatch = trainDispatchSystem;
        break;
      } else if (attribute.equalsIgnoreCase("DepartureTime") &&
          trainDispatchSystem.getDepartureTime().equals(value)) {
        trainDispatch = trainDispatchSystem;
        break;
      }
    }
    return trainDispatch;
  }

}
