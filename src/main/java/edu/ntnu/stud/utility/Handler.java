package edu.ntnu.stud.utility;

import java.util.Scanner;

/**
 *
 * @since 0.0.7 (Introduced in Version 0.0.7 of the Train Dispatch System application)
 */
public class Handler
{
  public String inputString(String typeOfInput){
    Scanner inputReader = new Scanner(System.in);
    String inputString = "";
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if(!inputReader.hasNextInt()) {
        inputString = inputReader.nextLine();
        flag = true;
      }
      else
      {
        System.out.println("Please enter a string!");
        inputReader.next();
      }
    }
    return inputString;
  }

  public int inputInteger(String typeOfInput){
    Scanner inputReader = new Scanner(System.in);
    int inputInteger = 0;
    boolean flag = false;
    while (!flag) {
      System.out.println("Please input the " + typeOfInput + " ::");
      if (inputReader.hasNextInt()){
        inputInteger = inputReader.nextInt();
        flag = true;
      }
      else {
        System.out.println("Please enter an integer!");
        inputReader.next();
      }
    }
    return inputInteger;
  }
}
