package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * The {@code InformationDisplay} class manages the presentation of information and feedback
 * within the Train Dispatch System application. It is responsible for displaying current station
 * details, welcome messages, menus, and train departure information in a user-friendly format.
 * Additionally, it provides methods for formatting and highlighting information for better user
 * interaction.
 *
 * @author Karwan Shekhe
 * @version 0.0.4 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class InformationDisplay {
  private final TrainRegister trainRegister;
  private final PrintStream printStream;
  private StringBuilder stringBuilder;
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String SEPARATOR_LINE = "========================"
      + "===========================";
  private static final String HORIZONTAL_LINE = "----------------------------------------"
      + "-----------------------------------------------------\n";

  /**
   * Constructs an instance of InformationDisplay with a specified train register and print stream.
   *
   * @param trainRegister The train register containing departure information.
   * @param printStream   The print stream for displaying information.
   * @since 0.0.1
   */
  public InformationDisplay(TrainRegister trainRegister, PrintStream printStream) {
    this.trainRegister = trainRegister;
    this.printStream = printStream;
  }

  /**
  * Applies a strikethrough effect to the given input string by adding the Unicode combining
  * character "COMBINING LONG STROKE OVERLAY" (U+0336) to each character in the string.
  * This creates a visual strikethrough effect when displayed.
  *
  * @param input The input string to be strikethrough.
  * @return A new string with the strikethrough effect applied.
  * @since 0.0.4
  */
  private String strikeThrough(String input) {
    return input.chars()
        .mapToObj(c -> (char) c + "̶")
        .collect(Collectors.joining());
  }

  /**
   * Generates a string consisting of spaces to achieve the needed width.
   * This method creates a string containing a specified number of space characters,
   * allowing for better formatting in text display.
   *
   * @param width The number of space characters to generate.
   * @return A string consisting of spaces with the specified width.
   * @since 0.0.4
   */
  private String generateSpaces(int width) {
    return " ".repeat(Math.max(0, width));
  }

  /**
   * Displays the current station, time (in the format HH:mm) and date (in the format yyyy-MM-dd).
   *
   * @since 0.0.1
   */
  public void displayCurrentStationAndTime() {
    stringBuilder = new StringBuilder();

    LocalTime currentTime = trainRegister.getStationTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = currentTime.format(formatter);
    LocalDate currentDate = LocalDate.now();

    stringBuilder.append(SEPARATOR_LINE + "\n");
    stringBuilder.append("|   Gjøvik Station    |  ").append(formattedTime).append("   |   ")
        .append(currentDate).append("   |   \n");
    stringBuilder.append(SEPARATOR_LINE + "\n");

    printStream.println(stringBuilder);
  }

  /**
   * Displays a welcome message to the user.
   *
   * @since 0.0.1
   */
  public void displayWelcomeText() {
    stringBuilder = new StringBuilder();

    stringBuilder.append(SEPARATOR_LINE + "\n");
    stringBuilder.append("| Welcome to the Train Dispatch System Application |\n");
    stringBuilder.append(SEPARATOR_LINE + "\n");

    printStream.println(stringBuilder);
  }

  /**
   * Displays the main menu content to the user.
   *
   * @since 0.0.1
   */
  public void displayMenuContent() {
    stringBuilder = new StringBuilder();
    String menuLine = "====================================================================";

    stringBuilder.append("Main Menu\n");
    stringBuilder.append("Please select an option from the menu below:\n");

    stringBuilder.append(menuLine).append("\n");

    stringBuilder.append("| 1.  View train dispatch information (Table)                      |\n");
    stringBuilder.append("| 2.  Add a new train departure                                    |\n");
    stringBuilder.append("| 3.  Add delay to a train departure                               |\n");
    stringBuilder.append("| 4.  Assign a new track for a train departure                     |\n");
    stringBuilder.append("| 5.  Search for a train departure based on train number           |\n");
    stringBuilder.append("| 6.  Search for a train departure based on destination            |\n");
    stringBuilder.append("| 7.  Search for a train departure based on departure time         |\n");
    stringBuilder.append("| 8.  Sort the departure list based on departure time              |\n");
    stringBuilder.append("| 9.  Update the train dispatch list based on your current time    |\n");
    stringBuilder.append("| 10. Update the station time                                      |\n");
    stringBuilder.append("| 11. Enter '11' for Help                                          |\n");

    stringBuilder.append(menuLine).append("\n");

    stringBuilder.append("Please input you choice below (1-11): \n");

    printStream.println(stringBuilder);
  }

  /**
   * Displays train departure details for a specific train departure/departures.
   *
   * @param iterator The list of train departures.
   * @since 0.0.1
   */
  public void displayTrainDepartureDetails(Iterator<TrainDispatchSystem> iterator) {
    boolean found = false;
    stringBuilder = new StringBuilder();

    stringBuilder.append(SEPARATOR_LINE + "\n");
    stringBuilder.append("|  Departure Details from the search results:  |\n");
    stringBuilder.append(SEPARATOR_LINE + "\n");
    stringBuilder.append(HORIZONTAL_LINE);
    stringBuilder.append("| Departure Station | Destination | "
        + "Departure Time          | Track | Line | Train Number |\n");
    stringBuilder.append(HORIZONTAL_LINE);

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatch = iterator.next();

      if (trainDispatch != null) {
        found = true;

        stringBuilder.append(String.format("| %-17s | %-11s | %-23s | %-5d | %-4s | %-12s |%n",
            trainDispatch.getDepartureStation(),
            trainDispatch.getDestination(),
            formatDepartureTimeWithDelay(trainDispatch),
            trainDispatch.getTrack(),
            trainDispatch.getLine(),
            trainDispatch.getTrainNumber()));
      }
    }
    stringBuilder.append(HORIZONTAL_LINE);

    if (!found) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(SEPARATOR_LINE + "\n");
      stringBuilder.append("Train was not found.").append("\n");
      stringBuilder.append(SEPARATOR_LINE + "\n");
      printStream.println(stringBuilder);
    }
    printStream.println(stringBuilder);
  }

  /**
   * Displays the train departures in a table format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * @since 0.0.1
   */
  public void displayTrainDispatchListTable(Iterator<TrainDispatchSystem> iterator) {
    stringBuilder = new StringBuilder();

    stringBuilder.append(HORIZONTAL_LINE);
    stringBuilder.append("| Departure Station | Destination | "
        + "Departure Time            | Track | Line | Train Number |\n");
    stringBuilder.append(HORIZONTAL_LINE);

    if (iterator.hasNext()) {
      while (iterator.hasNext()) {
        TrainDispatchSystem trainDispatch = iterator.next();

        stringBuilder.append(String.format("| %-17s | %-11s | %-25s | %-5d | %-4s | %-12s |%n",
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

    printStream.println(stringBuilder);
  }

  /**
   * <p>Formats the departure time of a train departure,
   * considering any delay (delay between 0 and 60 minutes).</p>
   *
   * <p>If a train is delayed, the departure time is displayed with a strikethrough effect,
   * indicating the original scheduled time and the delay in minutes.
   * Then the {@code displayTrainDispatchListTable} is updated with the help of this method,
   * indicating that the train is delayed.</p>
   *
   * @param trainDispatch The TrainDispatchSystem object representing the train departure.
   * @return A formatted string representing the departure time with or without delay.
   * @since 0.0.2
   */
  private String formatDepartureTimeWithDelay(TrainDispatchSystem trainDispatch) {

    // This is to avoid misplacement of columns in the table
    if (trainDispatch.getDelay() > 0 && trainDispatch.getDelay() < 10) {
      return String.format(ANSI_YELLOW + "%s (%d min delay)      " + ANSI_RESET, // with 6 spaces
          strikeThrough(trainDispatch.getDepartureTime().toString()), trainDispatch.getDelay());
    } else if (trainDispatch.getDelay() >= 10) {
      return String.format(ANSI_YELLOW + "%s (%d min delay)     " + ANSI_RESET,   // with 5 spaces
          strikeThrough(trainDispatch.getDepartureTime().toString()), trainDispatch.getDelay());
    } else {
      return trainDispatch.getDepartureTime().toString();
    }
  }

  /**
   * <p>Displays train departures that have departure times earlier than the specified time.
   * The method iterates through the given iterator of TrainDispatchSystem objects and
   * prints details of train departures that have departure times earlier than the provided time.
   * </p>
   *
   * <p>The displayed information includes train number, departure and destination stations,
   * departure time, train line, and track number.</p>
   *
   * @param iterator The iterator of TrainDispatchSystem objects to be displayed.
   * @param time      The time used as a reference for filtering train departures.
   * @since 0.0.4
   */
  public void displayTrainDispatchesBeforeTime(Iterator<TrainDispatchSystem> iterator,
      LocalTime time) {

    stringBuilder = new StringBuilder();
    String doubleLine = SEPARATOR_LINE + SEPARATOR_LINE + "\n";

    stringBuilder.append(doubleLine);
    stringBuilder.append(String.format("|%sRemoved Train Departures:%s|%n",
        generateSpaces(37), generateSpaces(38)));
    stringBuilder.append(doubleLine);
    stringBuilder.append("|    Train nr.    |     Departure --> Destination      "
        + "|   Departure Time   |    Line    |   Track   |\n");
    stringBuilder.append(doubleLine);

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatch = iterator.next();

      if (trainDispatch.getDepartureTime().isBefore(time)) {
        stringBuilder.append(String.format("| %-20s | %-34s | %-23s | %-10s | %-9d |%n",
            ANSI_YELLOW + trainDispatch.getTrainNumber(),
            trainDispatch.getDepartureStation() + " --> " + trainDispatch.getDestination(),
            strikeThrough(trainDispatch.getDepartureTime().toString()),
            trainDispatch.getLine(),
            trainDispatch.getTrack())).append(ANSI_RESET);
      }
    }

    stringBuilder.append(doubleLine);
    printStream.println(stringBuilder);
  }

  /**
   * Displays a guide message to the user, providing helpful tips and instructions.
   * This method prints a guide message containing useful information and tips for
   * users to navigate and understand the functionality of the Train Dispatch System application.
   *
   * @since 0.0.4
   */
  public void displayGuideMessage() {
    stringBuilder = new StringBuilder();
    String line = "###############################################################################";

    stringBuilder.append(line).append("\n");
    stringBuilder.append("  Thank you for using the application. "
        + "Here are some tips to get you started:  \n");
    stringBuilder.append("\n");

    stringBuilder.append(" - Entering '1' in the menu will take you to the departure table.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - Enter '2' to add a new train departure.\n");
    stringBuilder.append("   Note: you'll need to provide details such as:\n");
    stringBuilder.append("   Train Number, Destination, Departure Time,"
        + " Train Line and the Track.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - If you need to add a delay to a specific train, \n");
    stringBuilder.append("   enter the train number and then enter the delay (in minutes).\n");
    stringBuilder.append("   Display the train dispatch table again to view the delay.\n");
    stringBuilder.append("   NOTE: If you want to remove a delay for a delayed departure,\n");
    stringBuilder.append("   enter the train number for the given train that is set to be delayed,\n");
    stringBuilder.append("   and then enter '0' as the delay, the delayed will then be removed.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - If you need to update the track number for a specific train,\n");
    stringBuilder.append("   enter the train number, and then enter the new track number.\n");
    stringBuilder.append("   (NOTE: Gjøvik Station currently has only 10 tracks!)\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - To search for a specific departure, \n");
    stringBuilder.append("   you can use one of the three options in the menu: 5, 6, and 7.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - To sort the departures based on the departure time "
        + "press option '8'.\n");
    stringBuilder.append("   The departures will be sorted.\n");
    stringBuilder.append("   Display the train dispatch table again to confirm this.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - The Station time is "
        + "automatically set to the time from your device.\n");
    stringBuilder.append("   If it doesnt match your station time, press '10' from the menu.\n");
    stringBuilder.append("\n");

    stringBuilder.append(" - You always have the option to continue or exit the application.\n");
    stringBuilder.append("   After each menu interaction, "
        + "enter either 12 to exit, or 0 to continue.\n");
    stringBuilder.append("\n");

    stringBuilder.append("  Hope this helps, and enjoy using the application\n");
    stringBuilder.append(line).append("\n");

    printStream.println(ANSI_GREEN + stringBuilder + ANSI_RESET);
  }
}
