package edu.ntnu.stud.utility;

import java.util.Scanner;

/**
 * The {@code InputHandler} class provides methods for handling user input.
 * It includes methods for reading strings and integers with input validation.
 *
 * <p>This class ensures that user inputs are validated based on specified criteria, such as time
 * format, integer format, and non-empty values.
 * It utilizes regular expressions for input validation
 * and includes methods to prompt the user until valid input is provided.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code InputHandler} and call the desired input methods,
 * such as {@code inputString} or {@code inputInteger}, providing the type of input expected.
 * The class will prompt the user until valid input is provided based on the specified criteria.
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.2 (Version of this class)
 * @since 0.0.8 (Introduced in Version 0.0.8 of the Train Dispatch System application)
 */
public class InputHandler {
  private final Scanner inputReader;


  /**
   * Constructs an instance of {@code InputHandler}.
   * Initializes the input reader.
   *
   * @since 0.0.1
   */
  public InputHandler() {
    this.inputReader = new Scanner(System.in);
  }

  /**
   * Reads a string input from the user with the specified type of input and performs validation.
   *
   * <p>The method prompts the user to input a string and validates it based on the specified type.
   * It ensures that the input is in the correct time format (HH:mm) or non-empty based on the
   * specified criteria. The user is prompted until valid input is provided.
   *
   * @param typeOfInput The type of input expected (e.g., "time" or "non-empty").
   * @return The validated string input from the user.
   * @since 0.0.1
   */
  public String inputString(String typeOfInput) {
    String inputString = "";
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " :");
      if (inputReader.hasNextLine()) {
        inputString = inputReader.nextLine().trim();

        // Validation of the input
        if(isValidInput(inputString, "time") || isValidInput(inputString, "non-empty")) {
          flag = true;
        } else {
        System.out.println("Invalid format. Please try again.");
      }

      } else {
        System.out.println("Please enter a valid input!");
        inputReader.nextLine();
      }
    }
    return inputString;
  }

  /**
   * Reads an integer input from the user with the specified type of input and performs validation.
   *
   * <p>The method prompts the user to input an integer and validates it based on the specified type.
   * It ensures that the input is a valid integer or non-empty based on the specified criteria.
   * The user is prompted until valid input is provided.
   *
   * @param typeOfInput The type of input expected (e.g., "integer" or "non-empty").
   * @return The validated integer input from the user.
   * @since 0.0.1
   */
  public int inputInteger(String typeOfInput) {
    int inputInteger = 0;
    boolean flag = false;

    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " :");
      if (inputReader.hasNextInt()) {
        inputInteger = inputReader.nextInt();

        // Validation of the input
        if (isValidInput(Integer.toString(inputInteger), "integer") ||
            isValidInput(Integer.toString(inputInteger), "non-empty")) {
          flag = true;

        } else {
          System.out.println("Invalid format. Please try again.");
          inputReader.nextLine();
        }

      } else {
        System.out.println("Please enter an integer!");
        inputReader.nextLine();
      }
    }
    return inputInteger;
  }

  /**
   * Validates the input based on the specified type.
   *
   * <p>The method performs validation on the provided input based on the specified type.
   * It checks if the input adheres to the defined criteria, such as being a valid time format (HH:mm),
   * a valid integer, or non-empty based on the specified criteria. The method returns a boolean
   * indicating whether the input is valid or not.
   *
   * @param input The input string to be validated.
   * @param type The type of validation to be applied (e.g., "time," "integer," or "non-empty").
   * @return True if the input is valid according to the specified type; otherwise, false.
   *
   * @since 0.0.2
   */
  private boolean isValidInput(String input, String type) {
    return switch (type) {
      case "time" ->
        // To check if time format is valid (HH:mm)
          input.matches("\\d{2}:\\d{2}");
      case "integer" ->
        // To check if the input integer is valid
          input.matches("\\d+");
      case "non-empty" ->
        // To check for non-empty input (trimmed)
          !input.trim().isEmpty();
      default -> true;
    };
  }
}