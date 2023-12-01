package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * The {@code FeedBackMessages} class manages feedback messages and their display to the user.
 * It provides methods for showing informative and error messages within the Train Dispatch System
 * application.
 *
 * @author Karwan Shekhe
 * @version 0.0.2 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class InformationDisplay {

  private Iterator<TrainDispatchSystem> iterator;
  private StringBuilder stringBuilder;
  private static final String TRAIN_NUMBER = "TrainNumber";
  private static final String SEPARATOR_LINE = "================================================\n";
  private static final String HORIZONTAL_LINE = "----------------------------------------"
      + "------------------------------------------\n";

  /**
   * Constructs an instance of FeedBackMessages with a specified list of train departures.
   *
   * @param iterator The list of train departures.
   *
   * @since 0.0.1
   */
  public InformationDisplay(Iterator<TrainDispatchSystem> iterator) {
    this.iterator = iterator;
  }

  public void updateIterator(Iterator<TrainDispatchSystem> iterator) {this.iterator = iterator;}

  /**
   * Displays the current station, time (in the format HH:mm)
   * and date (in the format yyyy-MM-dd).
   *
   * @since 0.0.1
   */
  public void displayCurrentStationAndTime() {
    stringBuilder = new StringBuilder();

    LocalTime currentTime = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);
    LocalDate currentDate = LocalDate.now();

    stringBuilder.append("| Gjøvik Station | " + formattedTime + " | " + currentDate + "|");

    System.out.println(stringBuilder);
  }

  /**
   * Displays a welcome message to the user.
   *
   * @since 0.0.1
   */
  public void displayWelcomeText() {
    stringBuilder = new StringBuilder();

    stringBuilder.append(SEPARATOR_LINE);
    stringBuilder.append("Welcome to the Train Dispatch System Application\n");
    stringBuilder.append(SEPARATOR_LINE);

    System.out.println(stringBuilder);
  }

  /**
   * Displays the main menu to the user.
   *
   * @since 0.0.1
   */
  public void displayMenuContent() {
    stringBuilder = new StringBuilder();

    stringBuilder.append("Main Menu\n");
    stringBuilder.append("Please select an option from the menu below:\n");

    stringBuilder.append(SEPARATOR_LINE);

    stringBuilder.append("1. View train dispatch information\n");
    stringBuilder.append("2. Add a new train departure\n");
    stringBuilder.append("3. Add delay to a train departure\n");
    stringBuilder.append("4. Search for a train departure based on train number\n");
    stringBuilder.append("5. Search for a train departure based on destination\n");
    stringBuilder.append("6. Search for a train departure based on departure time\n");
    stringBuilder.append("7. Sort the departure list based on departure time\n");
    stringBuilder.append("8. Update the train dispatch list based on your current time\n");

    stringBuilder.append(SEPARATOR_LINE);

    stringBuilder.append("Please input you choice below (1-8): \n");

    System.out.println(stringBuilder);
  }

  /**
   * Displays train departure details for a specific train departure.
   *
   * @param iterator The list of train departures.
   * @since 0.0.1
   */
  public void displayTrainDepartureDetails(Iterator<TrainDispatchSystem> iterator) {
    boolean found = false;

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatch = iterator.next();
      stringBuilder = new StringBuilder();

      if(trainDispatch != null) {
        found = true;

        stringBuilder.append(SEPARATOR_LINE);
        stringBuilder.append("Train Departure Details for train ").
            append(trainDispatch.getTrainNumber()).append(" :\n");
        stringBuilder.append(HORIZONTAL_LINE);

        stringBuilder.append("Train Number: ").append(trainDispatch.getTrainNumber()).append("\n");
        stringBuilder.append("Departure Station: ").append(trainDispatch.getDepartureStation()).append("\n");
        stringBuilder.append("Destination: ").append(trainDispatch.getDestination()).append("\n");
        stringBuilder.append("Departure Time: ").append(trainDispatch.getDepartureTime()).append("\n");
        stringBuilder.append("Train Line: ").append(trainDispatch.getLine()).append("\n");
        stringBuilder.append("Track: ").append(trainDispatch.getTrack()).append("\n");

        stringBuilder.append(SEPARATOR_LINE);
        System.out.println(stringBuilder);
      }

    }
    if(!found) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(SEPARATOR_LINE);
      stringBuilder.append("Train with the specified train number was not found.").append("\n");
      stringBuilder.append(SEPARATOR_LINE);
      System.out.println(stringBuilder);
    }
  }

  /**
   * Displays a train departures in a table format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * @since 0.0.1
   */
  public void displayTrainDispatchListTable(Iterator<TrainDispatchSystem> iterator) {
    stringBuilder = new StringBuilder();

    stringBuilder.append(HORIZONTAL_LINE);
    stringBuilder.append("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |\n");
    stringBuilder.append(HORIZONTAL_LINE);

    if (iterator.hasNext()) {
      while (iterator.hasNext()) {
        TrainDispatchSystem trainDispatch = iterator.next();

        stringBuilder.append(String.format("| %-17s | %-11s | %-13s  | %-5d | %-4s | %-12s |%n",
            trainDispatch.getDepartureStation(),
            trainDispatch.getDestination(),
            formatDepartureTimeWithDelay(trainDispatch),
            trainDispatch.getTrack(),
            trainDispatch.getLine(),
            trainDispatch.getTrainNumber()));
      }
    } else {
      stringBuilder.append("| No train dispatches available |\n");
    }
    stringBuilder.append(HORIZONTAL_LINE);

    System.out.println(stringBuilder);
  }

  /**
   * Informs the user that the train is delayed.
   * It prints a message indicating that the train is delayed and the delay in minutes.
   *
   * @param trainDispatch The train departure.
   * @since 0.0.2
   */
  private String formatDepartureTimeWithDelay(TrainDispatchSystem trainDispatch) {
    if (trainDispatch.getDelay() > 0) {
      return String.format("%s (%d min delay)",
          trainDispatch.getDepartureTime(), trainDispatch.getDelay());
    } else {
      return trainDispatch.getDepartureTime().toString();
    }
  }


  // Messages for the user:

  /*
  Kansje lagre alle meldingene i en array og jeg lager en metode som bruker for løkke til å
  itterere gjennom arrayen og finne en melding med en spesifikk indeks. Der etter implementerer
  jeg dette i ConfigurationAppOptions classen.
  Isteden for å skrive masse metoder som gjør det samme og gjør koden vanskelig å lese.
  Jeg lager 2 arrays en for error meldinger og en for info til brukeren.

  Jeg kan prøve å følge samme prinsipp i oppgave 8.18 og side 279 i boka.
  (Sjekk også CommandWords classen i "zuul-bad" prosjektet i BlueJ.)

  eventuelt kan jeg lage en ny klasse for dette oppgaven.
   */

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
  // Commit test
}
