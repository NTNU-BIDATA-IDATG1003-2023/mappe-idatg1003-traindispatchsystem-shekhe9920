package edu.ntnu.stud.utility;

import java.util.Scanner;

/**
 * @since 0.0.7 (Introduced in Version 0.0.7 of the Train Dispatch System application)
 */
public class Handler {
  private final Scanner inputReader;

  public Handler() {
    this.inputReader = new Scanner(System.in);
  }

  public String inputString(String typeOfInput) {
    String inputString = "";
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if (inputReader.hasNextLine()) {
        inputString = inputReader.nextLine();
        flag = true;
      } else {
        System.out.println("Please enter a valid input!");
        inputReader.nextLine(); // Consume the entire line to handle the newline character
      }
    }
    return inputString;
  }

  public int inputInteger(String typeOfInput) {
    int inputInteger = 0;
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if (inputReader.hasNextInt()) {
        inputInteger = inputReader.nextInt();
        flag = true;
      } else {
        System.out.println("Please enter an integer!");
        inputReader.nextLine(); // Consume the newline character
      }
    }
    return inputInteger;
  }
}