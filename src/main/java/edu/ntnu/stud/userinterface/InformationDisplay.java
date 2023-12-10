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
 * The {@code InformationDisplay} class manages the presentation of information/data and feedback
 * within the Train Dispatch System application.
 *
 * <p>It is responsible for displaying current station
 * details, welcome messages, menus, and train departure information in a user-friendly format.</p>
 *
 * <p>Additionally, it provides methods for formatting and highlighting information for better user
 * interaction.</p>
 *
 * @author Karwan Shekhe
 * @version 0.0.5 (Version of this class)
 * @since 0.0.6 (Introduced in Version 0.0.6 of the Train Dispatch System application)
 */
public class InformationDisplay {
  private final TrainRegister trainRegister;  // The train register containing information.
  private final PrintStream printStream;     // The print stream for displaying information.
  private StringBuilder stringBuilder;       // The string builder for building strings.


  // ANSI escape codes for colors and text formatting:
  private static final String ANSI_YELLOW = "\u001B[33m";   // Yellow color
  private static final String ANSI_GREEN = "\u001B[32m";    // Green color
  private static final String ANSI_RESET = "\u001B[0m";     // Reset color
  private static final String ANSI_BOLD = "\u001B[1m";      // Bold text


  // Separator lines for the:
  private static final String SEPARATOR_LINE = "========================"
      + "===========================";           // separator line mainly for the tables
  private static final String HORIZONTAL_LINE = "----------------------------------------"
      + "-----------------------------------------------------\n";   // line for separating



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
  * Applies a strikethrough effect to the given input string.
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
   * <p>This method is used when a string requires many spaces to achieve the needed visual,
   * making the code easier to read. </p>
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // Format: HH:mm
    String formattedTime = currentTime.format(formatter);
    LocalDate currentDate = LocalDate.now();

    // This part displays the actual station, time and date:
    stringBuilder.append(ANSI_GREEN).append(SEPARATOR_LINE + "\n")
        .append("|   Gjøvik Station    |  ").append(formattedTime).append("   |   ")
        .append(currentDate).append("   |   \n")
        .append(SEPARATOR_LINE + "\n")
        .append(ANSI_RESET);

    printStream.println(ANSI_BOLD + stringBuilder);
  }



  /**
   * Displays a welcome message to the user.
   *
   * @since 0.0.1
   */
  public void displayWelcomeText() {
    stringBuilder = new StringBuilder();

    stringBuilder.append(ANSI_GREEN).append("\n")
        .append(SEPARATOR_LINE + "\n")
        .append("| Welcome to the Train Dispatch System Application |\n")
        .append(SEPARATOR_LINE)
        .append(ANSI_RESET);

    printStream.println(ANSI_BOLD + stringBuilder);
  }



  /**
   * Displays the main menu content to the user.
   *
   * @since 0.0.1
   */
  public void displayMenuContent() {
    stringBuilder = new StringBuilder();
    String menuLine = "=====================================================================";

    stringBuilder.append("Main Menu\n");
    stringBuilder.append("Please select an option from the menu below:\n");

    stringBuilder
        .append(menuLine).append("\n")
        .append("| 1.  View train dispatch information (Table)                       |\n")
        .append("| 2.  Add a new train departure                                     |\n")
        .append("| 3.  Add delay to a train departure                                |\n")
        .append("| 4.  Assign a new track for a train departure                      |\n")
        .append("| 5.  Search for a train departure based on train number            |\n")
        .append("| 6.  Search for a train departure based on destination             |\n")
        .append("| 7.  Search for a train departure based on departure time          |\n")
        .append("| 8.  Sort the departure list based on departure time               |\n")
        .append("| 9.  Remove a specific train departure (search based on train nr.) |\n")
        .append("| 10. Remove all the train departures form the list                 |\n")
        .append("| 11. Remove departures that have passed based on the station time  |\n")
        .append("| 12. Update the station time                                       |\n")
        .append("| 13. Enter '13' for Help                                           |\n")
        .append(menuLine).append("\n")
        .append("Please input you choice below (1-13): \n");

    printStream.println(ANSI_BOLD + stringBuilder);
  }



  /**
   * Displays an empty table when no train departures are available.
   * This method is used when no train departures are found to display.
   *
   * @since 0.0.5
   */
  private void displayEmptyTable() {
    stringBuilder.append("|")
        .append(generateSpaces(31))
        .append(ANSI_YELLOW + "No train dispatches available" + ANSI_RESET)
        .append(generateSpaces(31)).append("|\n");
  }



  /**
   * Displays train departure details for a specific train departure/departures.
   * It iterates through the given iterator of TrainDispatchSystem objects and prints details of
   * train departures that match the provided train number.
   *
   * <p>If no train departures are found, the method will display an empty table.</p>
   *
   * @param iterator The list of train departures.
   * @since 0.0.1
   */
  public void displayTrainDepartureDetailsForSearchResults(Iterator<TrainDispatchSystem> iterator) {
    boolean found = false;
    stringBuilder = new StringBuilder();

    // Columns for the table:
    stringBuilder
        .append(SEPARATOR_LINE + "\n")
        .append("|  Departure Details from the search results:  |\n")
        .append(SEPARATOR_LINE + "\n")
        .append(HORIZONTAL_LINE)
        .append("| Departure Station | Destination | "
             + "Departure Time          | Track | Line | Train Number |\n")
        .append(HORIZONTAL_LINE);

    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatch = iterator.next();

      if (trainDispatch != null) {
        found = true;

        stringBuilder
            .append(String.format("| %-17s | %-11s | %-23s | %-5d | %-4s | %-12s |%n",
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
      // If no train departures are found, the method will display an empty table.
      displayEmptyTable();
      stringBuilder.append(HORIZONTAL_LINE);
    }
    printStream.println(ANSI_BOLD + stringBuilder);
  }



  /**
   * Displays all the train departures in a table format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * <p>If no train departures are registered, the method will display an empty table.</p>
   *
   * @param iterator The iterator of TrainDispatchSystem objects to be displayed.
   * @since 0.0.1
   */
  public void displayTrainDispatchListTable(Iterator<TrainDispatchSystem> iterator) {
    stringBuilder = new StringBuilder();

    // Columns for the table:
    stringBuilder.append(HORIZONTAL_LINE)
        .append("| Departure Station | Destination | "
             + "Departure Time            | Track | Line | Train Number |\n")
        .append(HORIZONTAL_LINE);

    // Iterates through the list of train departures and prints the details of each train departure:
    if (iterator.hasNext()) {

      while (iterator.hasNext()) {
        TrainDispatchSystem trainDispatch = iterator.next();

        stringBuilder
            .append(String.format("| %-17s | %-11s | %-25s | %-5d | %-4s | %-12s |%n",
            trainDispatch.getDepartureStation(),
            trainDispatch.getDestination(),
            formatDepartureTimeWithDelay(trainDispatch),
            trainDispatch.getTrack(),
            trainDispatch.getLine(),
            trainDispatch.getTrainNumber()));
      }
    } else {

      // If no train departures are found, the method will display an empty table.
      displayEmptyTable();

    }
    stringBuilder.append(HORIZONTAL_LINE);

    printStream.println(ANSI_BOLD + stringBuilder);
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
   * @param time     The time used as a reference for filtering train departures.
   * @since 0.0.4
   */
  public void displayExpiredDeparturesBasedOnTime(Iterator<TrainDispatchSystem> iterator,
      LocalTime time) {

    stringBuilder = new StringBuilder();
    String doubleLine = SEPARATOR_LINE + SEPARATOR_LINE + "\n";

    // This part displays the table header:
    stringBuilder.append(doubleLine)
        .append(String.format("|%sRemoved Train Departures:%s|%n",
                generateSpaces(37), generateSpaces(38)));

    // This part displays the table columns:
    stringBuilder.append(doubleLine)
        .append("|    Train nr.    |     Departure --> Destination      "
                + "|   Departure Time   |    Line    |   Track   |\n")
        .append(doubleLine);

    // A loop to iterate through the list of train departures and print the details of each train:
    while (iterator.hasNext()) {
      TrainDispatchSystem trainDispatch = iterator.next();

      if (trainDispatch.getDepartureTime().isBefore(time)) {
        stringBuilder
            .append(String.format("| %-20s | %-34s | %-23s | %-10s | %-9d |%n",
            ANSI_YELLOW + trainDispatch.getTrainNumber(),
            trainDispatch.getDepartureStation() + " --> " + trainDispatch.getDestination(),
            strikeThrough(trainDispatch.getDepartureTime().toString()),
            trainDispatch.getLine(),
            trainDispatch.getTrack())).append(ANSI_RESET);
      }
    }

    stringBuilder.append(doubleLine);
    printStream.println(ANSI_BOLD + stringBuilder);
  }




  /**
   * Displays a goodbye message to the user.
   *
   * @since 0.0.5
   */
  public void displayGoodbyeMessage() {
    stringBuilder = new StringBuilder();
    String finalMessage1 = "Thank you for using the Train Dispatch System Application";

    stringBuilder.append("\n");
    stringBuilder.append(HORIZONTAL_LINE);

    stringBuilder.append("|").append(generateSpaces(17))
        .append(finalMessage1)
        .append(generateSpaces(17)).append("|\n");

    String finalMessage2 = "Have a nice day!";
    stringBuilder.append("|").append(generateSpaces(37))
        .append(finalMessage2)
        .append(generateSpaces(38)).append("|\n");


    stringBuilder.append(HORIZONTAL_LINE);

    printStream.println(ANSI_BOLD + ANSI_GREEN + stringBuilder + ANSI_RESET);
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

    stringBuilder.append(" - Entering '1' in the menu will take you to the departure table.\n")
                 .append("\n");

    stringBuilder.append(" - Enter '2' to add a new train departure.\n")
                 .append("   Note: you'll need to provide details such as:\n")
                 .append("   Train Number, Destination, Departure Time,"
                       + " Train Line and the Track Number.\n").append("\n");

    stringBuilder.append(" - If you need to add a delay to a specific train, \n")
                 .append("   enter the train number and then enter the delay (in minutes).\n")
                 .append("   Display the train dispatch table again to view the delay.\n")
                 .append("   NOTE: If you want to remove a delay for a delayed departure,\n")
                 .append("   enter the train number for the given train that is set to be delayed,")
                 .append("\n")
                 .append("   and then enter '0' as the delay, the delayed will then be removed.\n")
                 .append("\n");

    stringBuilder.append(" - If you need to update the track number for a specific train,\n")
                 .append("   enter the train number, and then enter the new track number.\n")
                 .append("   (NOTE: Gjøvik Station currently has only 10 tracks!)\n")
                 .append("\n");

    stringBuilder.append(" - To search for a specific departure, \n")
                 .append("   you can use one of the three options in the menu: 5, 6, and 7.\n")
                 .append("\n");

    stringBuilder.append(" - To sort the departures based on the departure time "
                       + "select option '8' from the menu.\n")
                 .append("   The departures will be sorted.\n")
                 .append("   Display the train dispatch table again to confirm this.\n")
                 .append("   (NOTE: If there is any delays, the delay will not be counted for.)\n")
                 .append("\n");

    stringBuilder.append(" - To remove a specific departure, select option '9' from the menu.\n")
                 .append("   You'll be prompted to enter the train number for the departure.\n")
                 .append("   Selecting option 10, will remove all the departures from the list.\n");

    stringBuilder.append(" - The Station time is "
                       + "automatically set to the time from your device.\n")
                 .append("   If it doesnt match your station time, select option"
                       + " '12' from the menu.\n")
                 .append("   Enter the new station time in the format HH:mm.\n")
                 .append("\n");

    stringBuilder.append(" - You always have the option to continue or exit the application.\n")
                 .append("   After each menu interaction, "
                       + "enter either 14 to exit, or 0 to continue.\n")
                 .append("\n");

    stringBuilder.append("  Hope this helps, and enjoy using the application\n")
                 .append(line).append("\n");

    printStream.println(ANSI_BOLD + ANSI_GREEN + stringBuilder + ANSI_RESET);
  }
}
