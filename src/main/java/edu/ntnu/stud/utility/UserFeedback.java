package edu.ntnu.stud.utility;

/**
 * The {@code UserFeedback} class provides feedback messages to the user during interactions with
 * the Train Dispatch System application.
 *
 * <p>This class includes predefined feedback messages for different scenarios, such as entering
 * train details, encountering errors, or completing tasks successfully. It utilizes ANSI color
 * codes for terminal output, making feedback messages visually distinctive.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code UserFeedback} and
 * call the desired feedback methods,
 * such as {@code feedbackMessage}, {@code errorFeedBack}, or {@code taskCompletedFeedBack},
 * providing the type of feedback expected. The class will return the corresponding feedback message
 * based on the specified type.
 *
 * Example:
 * {@code
 * UserFeedback feedback = new UserFeedback();
 * String enterTrainNumberMessage = feedback.feedbackMessage("enterTrainNumber");
 * System.out.println(enterTrainNumberMessage);
 * }
 * </pre></blockquote>
 *
 * <p>Feedback messages are printed to the console with different formatting, including color-coded
 * text and underlined error messages for better user visibility.
 *
 * @author Karwan Shekhe
 * @version 0.1.0 (Version of this class)
 * @since 0.0.7 (Introduced in Version 0.0.7 of the Train Dispatch System application)
 */
public class UserFeedback {

  // ANSI COLOR CODES FOR TERMINAL OUTPUT:
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_BOLD = "\u001B[1m";
  private static final String UNDER_DOUBLE_LINE = "\u001B[4m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";



  /**
   * Generates a user prompt message based on the specified type.
   *
   * @param type The type of prompt message to generate.
   * @return The user prompt message for the specified type.
   */
  private String feedbackMessage(String type) {
    return switch (type) {
      case "enterTrainNumber" -> "Please enter the train number: ";

      case "enterDepartureStation" -> "Please enter the departure station: ";

      case "enterDestination" -> "Please enter the destination: ";

      case "enterDepartureTime" -> "Please enter the departure time: ";

      case "enterTrainLine" -> "Please enter the train line: ";

      case "enterTrackNumber" -> "Please enter the track number: ";

      case "enterTrainNumberToSetDelay" -> "Please enter the number for the delayed train:";

      case "enterNewStationTime" -> "Enter the new station time (HH:mm)";

      case "removingExpiredDepartures" ->
          "Removing train departures whose departure time has passed...";

      case "removingAllDepartures" -> "Removing all departures...";

      default -> "";
    };
  }




  /**
   * Generates an error feedback message based on the specified type.
   *
   * @param type The type of error feedback message to generate.
   * @return The error feedback message for the specified type.
   */
  private String errorFeedBack(String type) {
    return switch (type) {

      case "notFound" -> "Train departure was not found. ";

      case "invalidTrainNumber" -> "Invalid train number. Please try again.";

      case "invalidDepartureStation" -> "Invalid departure station. Please try again.";

      case "invalidDestination" -> "Invalid destination. Please try again.";

      case "invalidDepartureTime" -> "Invalid departure time. Please try again.";

      case "invalidTrainLine" -> "Invalid train line. Please try again.";

      case "invalidTimeFormat" -> "Invalid time format. Please use HH:mm format (e.g., 08:30). "
          + "(Press 'enter' to try again)";

      case "failedToSetDelay" -> "Failed to set the delay.";

      case "trainNotAdded" -> "Error: Train was not added. Please try again. ";
      default -> "";
    };
  }



  /**
   * Generates a success feedback message based on the specified type.
   *
   * @param type The type of success feedback message to generate.
   * @return The success feedback message for the specified type.
   */
  public String taskCompletedFeedBack(String type) {
    return switch (type) {

      case "addedSuccessfully" -> "Train added successfully.";

      case "trackAssigned" -> "Track assigned successfully";

      case "updatedSuccessfully" -> "Departure list updated successfully.";

      case "sortedSuccessfully" -> "Train departures sorted successfully.";

      case "delayAdded" -> "Delay set successfully.";

      case "stationTimeUpdated" -> "Station time updated successfully.";

      default -> "";
    };
  }



  /**
   * Logs and displays feedback messages to the user based on the specified type.
   *
   * @param type The type of feedback message to log and display.
   */
  public void logFeedback(String type) {
    String message = feedbackMessage(type);                // the feedback message
    String warning = errorFeedBack(type);                  // the error message
    String taskCompleted = taskCompletedFeedBack(type);    // the success message

    // Display feedback messages to the user:
    if (!message.isEmpty()) {
      System.out.println(ANSI_BOLD + ANSI_BLACK + ANSI_YELLOW_BACKGROUND + message + ANSI_RESET);
    }

    // Display error messages to the user:
    if (!warning.isEmpty()) {
      String stopUnderDoubleLine = "\u001B[24m";

      System.err.println(ANSI_BOLD + ANSI_RED + UNDER_DOUBLE_LINE
          + warning + stopUnderDoubleLine + ANSI_RESET);
    }

    // Display task completed messages to the user:
    if (!taskCompleted.isEmpty()) {
      System.out.println(ANSI_BOLD + ANSI_GREEN +  taskCompleted + ANSI_RESET);
    }
  }
}
