package guiutility;

import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * The {@code FeedBackMessages} class manages feedback messages and their display to the user.
 * It provides methods for showing informative and error messages within the Train Dispatch System
 * application.
 *
 * @author Karwan Shekhe
 * @version 0.0.1 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class FeedBackMessages {

  private Set<TrainDispatchSystem> trainDispatchList;
  public static final String TRAIN_NUMBER = "TrainNumber";
  public static final String SEPARATOR_LINE = "================================================";
  public static final String HORIZONTAL_LINE = "----------------------------------------"
      + "------------------------------------------";

  /**
   * Constructs an instance of FeedBackMessages with a specified list of train departures.
   *
   * @param trainDispatchList The list of train departures used to
   *                          display train departure information.
   * @since 0.0.1
   */
  public FeedBackMessages(Set<TrainDispatchSystem> trainDispatchList) {
    this.trainDispatchList = trainDispatchList;
  }

  /**
   * Displays the current time in the format HH:mm.
   * Also, displays the current date in the format yyyy-MM-dd.
   *
   * @since 0.0.1
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
   * @since 0.0.1
   */
  public void displayWelcomeText() {
    System.out.println(FeedBackMessages.SEPARATOR_LINE);
    System.out.println("Welcome to the Train Dispatch System Application");
    System.out.println(FeedBackMessages.SEPARATOR_LINE);
  }

  /**
   * Displays the main menu to the user.
   *
   * @since 0.0.1
   */
  public void displayMenuContent() {
    System.out.println("\n\nMain Menu");
    System.out.println("Pleas select an option from the menu below: ");
    System.out.println(SEPARATOR_LINE);
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
    System.out.println(SEPARATOR_LINE);
    System.out.print("Input your choice (1-9) below: ");
  }

  /**
   * Displays train departure details for a specific train departure.
   *
   * @param trainDispatch The train departure to display details for.
   * @since 0.0.1
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
   * Starts the user interface for displaying train dispatch information in a tabular format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * @since 0.0.1
   */
  public void displayTrainDepartureInformation() {

    System.out.println(HORIZONTAL_LINE);  // Displays the table header
    System.out.println("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |");
    System.out.println(HORIZONTAL_LINE);  // Displays the table header


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
    System.out.println(HORIZONTAL_LINE);  // Displays a horizontal line
  }


  // Messages for the user:

  /**
   * Displays a message to prompt the user to enter the departure station.
   *
   * @since 0.0.1
   *
   */
  public void enterDepartureStation() {
    String enterDepartureStation = "Pleas enter the departure station";
    System.out.println(enterDepartureStation);
  }

  /**
   * Displays a message to prompt the user to enter the train number for updating station time.
   *
   * @since 0.0.1
   */
  public void ENTER_TRAIN_NUMBER_TO_UPDATE_STATION_TIME() {
    String ENTER_TRAIN_NUMBER_TO_UPDATE_STATION_TIME = "Please enter the train number "
        + "for which you want to update the station time: ";
    System.out.println(ENTER_TRAIN_NUMBER_TO_UPDATE_STATION_TIME);
  }

  /**
   * Displays a message to the user to enter the destination.
   *
   * @since 0.0.1
   */
  public void enterDestination() {
    String enterDestination = "Please enter the destination: ";
    System.out.println(enterDestination);
  }

  /**
   * Displays a message to the user to enter the departure time.
   *
   * @since 0.0.1
   */
  public void ENTER_DEPARTURE_TIME() {
    String ENTER_DEPARTURE_TIME = "Please enter the departure time (HH:mm): ";
    System.out.println(ENTER_DEPARTURE_TIME);
  }

  /**
   * Displays a message to the user to enter the train line.
   *
   * @since 0.0.1
   */
  public void ENTER_LINE() {
    String ENTER_LINE = "Please enter the train line: ";
    System.out.println(ENTER_LINE);
  }

  /**
   * Displays a message to the user to enter the train line.
   *
   * @since 0.0.1
   */
  public void ENTER_TRACK() {
    String ENTER_TRACK = "Please enter the track number: ";
    System.out.println(ENTER_TRACK);
  }

  /**
   * Displays a message to the user to enter the train number.
   *
   * @since 0.0.1
   */
  public void ENTER_TRAIN_NUMBER() {
    String ENTER_TRAIN_NUMBER = "Please enter the train number: ";
    System.out.println(ENTER_TRAIN_NUMBER);
  }

  /**
   * Displays a message to the user to enter the delay in minutes.
   *
   * @since 0.0.1
   */
  public void ENTER_DELAY() {
    String ENTER_DELAY = "Please enter the delay in minutes: ";
    System.out.println(ENTER_DELAY);
  }

  /**
   * Displays a message indicating the successful assignment of a track.
   *
   * @since 0.0.1
   */
  public void TRACK_ASSIGNED_SUCCESSFULLY() {
    String TRACK_ASSIGNED_SUCCESSFULLY = "Track assigned successfully.";
    System.out.println(TRACK_ASSIGNED_SUCCESSFULLY);
  }

  /**
   * Displays a message indicating the successful addition of a delay.
   *
   * @since 0.0.1
   */
  public void DELAY_ADDED_SUCCESSFULLY() {
    String DELAY_ADDED_SUCCESSFULLY = "Delay added successfully.";
    System.out.println(DELAY_ADDED_SUCCESSFULLY);
  }

/**
   * Displays a message indicating the application will be exited.
   *
   * @since 0.0.1
   */
  public void EXITING_APPLICATION() {
    String EXITING_APPLICATION =  "Exiting the application. Goodbye!";
    System.out.println(EXITING_APPLICATION);
  }

  /**
   * Displays a message asking the user
   * whether they want to go back to the main menu or exit the application.
   *
   * @since 0.0.1
   */
  public void CONTINUE_OR_EXIT() {
    String CONTINUE_OR_EXIT = "Do you want to go back to the main menu (press 0), "
        + "or to exit the application (press 9)?";
    System.out.println(CONTINUE_OR_EXIT);
  }



  // Error messages for the user:

  /**
   * Displays an error message indicating an invalid user choice.
   *
   * @since 0.0.1
   */
  public void INVALID_CHOICE() {
    String INVALID_CHOICE = "Invalid choice. Please try again.";
    System.out.println(INVALID_CHOICE);
  }

  /**
   * Displays an error message indicating an invalid time format.
   *
   * @since 0.0.1
   */
  public void INVALID_TIME_FORMAT() {
    String INVALID_TIME_FORMAT =
        "Invalid time format. Please use HH:mm format (e.g., 08:30).";
    System.out.println(INVALID_TIME_FORMAT);
  }

  /**
   * Displays an error message indicating that a
   * train with the specified train number was not found.
   *
   * @since 0.0.1
   */
  public void TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND() {
    String TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND =
        "Train with the specified train number was not found.";
    System.out.println(TRAIN_WITH_SPECIFIED_TRAIN_NUMBER_NOT_FOUND);
  }
}
