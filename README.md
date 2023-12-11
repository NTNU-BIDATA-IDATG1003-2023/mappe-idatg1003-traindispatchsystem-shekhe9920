[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/sT7H9ZJB)
# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = Karwan Shekhe  
STUDENT ID = 106990


## Project description

The "Train Dispatch System" application is a system used to manage and monitor train departures, including scheduling, track allocation, and information on delays. 
The system is utilized by train operators and station staff to ensure smooth and precise train operations. 
The application is developed to meet the needs of a regional railway station and features a text-based user interface in the form of a menu.

Some specific problems and challenges addressed by the project include (but not limited to):
- Overview of train departures: Sorted by departure time.
- Adding a new train departure: Restriction on adding a train with a number matching an existing train in the list.
- Assigning tracks to a train departure and entering delays: Train number is used to look up the train departure, and changes are applied.
- Searching for train departures based on train number or destination.
- Updating the time of day: By prompting the user for a new time.



## Project structure


The "Train Dispatch System" project is organized into several key classes, each serving a specific purpose in managing and monitoring train departures. 

Here's an overview of the project structure:

### 1. TrainDispatchApp (Main Class)

- **Responsibility:** Main entry point for the application.
- **Functionality:**
    - Calls methods to populate initial train departure data.
    - Manages the interaction loop between the user and the system.

### 2. TrainDispatchSystem

- **Responsibility:** Represents individual train departures, storing essential information about departure station, destination, departure time, train line, track, and assigned train number.
- **Functionality:**
    - Provides methods for setting and retrieving information about train departures.


### 3. TrainManager

- **Responsibility:** Manages the allocation of unique train numbers to train departures.
- **Functionality:**
    - Prevents duplicate train numbers by using a HashMap to track allocated numbers.
    - Provides methods to check the availability of a train number and marks it as allocated.

### 4. TrainRegister

- **Responsibility:** Administers and creates instances of train departures, supporting functions such as sorting, removal, and searching.
- **Functionality:**
    - Utilizes a HashMap (`trainDispatchRegister`) for efficient access to train information by train number.
    - Implements an ArrayList (`dispatchSearchResults`) for temporary storage during search operations.
    - Includes methods for sorting train departures by departure time, searching based on attribute values, and more.

### 5. UserOptionsManager

- **Responsibility:** Handles configuration options and user interactions.
- **Functionality:**
    - Focuses on the user interface and execution of user choices.
    - Separates configuration and interaction logic for code readability and extensibility.

### 6. InformationDisplay

- **Responsibility:** Presents information to the user in a readable format.
- **Functionality:**
    - Includes methods to display tabulated information about train departures.

### 7. UserInterfaceManager

- **Responsibility:** Manages user choices through a switch-case structure.
- **Functionality:**
    - Processes user selections, executing corresponding actions using methods from `UserOptionsManager`.
    - Controls the flow of the application based on user input.

### 8. InputHandler

- **Responsibility:** Ensures valid user input by validating against specified criteria.
- **Functionality:**
    - Reads user input and performs validation based on specified types.

### 9. UserFeedback

- **Responsibility:** Provides feedback to the user, including informative and error messages.
- **Functionality:**
    - Separates positive feedback and error messages using switch-case blocks.


### File Organization:

- Source files are organized into the `edu.ntnu.stud` package.
- 
# Project Structure



## Source Code

- `src`
    - `main`
        - `java`
            - `edu.ntnu.stud`
                - `register`
                    - [TrainManager.java](src/main/java/edu/ntnu/stud/register/TrainManager.java)
                    - [TrainRegister.java](src/main/java/edu/ntnu/stud/register/TrainRegister.java)
                - `traindispatchsystem`
                    - [TrainDispatchSystem.java](src/main/java/edu/ntnu/stud/traindispatchsystem/TrainDispatchSystem.java)
                - `userinterface`
                    - [InformationDisplay.java](src/main/java/edu/ntnu/stud/userinterface/InformationDisplay.java)
                    - [UserInterfaceManager.java](src/main/java/edu/ntnu/stud/userinterface/UserInterfaceManager.java)
                    - [UserOptionsManager.java](src/main/java/edu/ntnu/stud/userinterface/UserOptionsManager.java)
                - `utility`
                    - [InputHandler.java](src/main/java/edu/ntnu/stud/utility/InputHandler.java)
                    - [UserFeedback.java](src/main/java/edu/ntnu/stud/utility/UserFeedback.java)
                - [TrainDispatchSystemApp.java](src/main/java/edu/ntnu/stud/TrainDispatchSystemApp.java)

## Test Code
- JUnit test classes, are stored in a separate `test` package.

- `test`
    - `java`
        - `edu.ntnu.stud`
              - `registertest`
                    - [TrainManagerTest.java](src/test/java/edu/ntnu/stud/registertest/TrainManagerTest.java)
                    - [TrainRegisterTest.java](src/test/java/edu/ntnu/stud/registertest/TrainRegisterTest.java)
              - `traindispatchsystemtest`
                    - [TrainDispatchSystemTest.java](src/test/java/edu/ntnu/stud/traindispatchsystemtest/TrainDispatchSystemTest.java)



          
## Link to repository

https://github.com/NTNU-BIDATA-IDATG1003-2023/mappe-idatg1003-traindispatchsystem-shekhe9920



## How to run the project

To run the project, follow these steps: 

1. **Clone the repository to your computer.**
   - Open a terminal or command prompt.
   - Navigate to the root directory of the project where your Java files are located.
   - Import the project into your IDE.
   
2. **Open the project in your IDE.**
    - Open the project in your IDE of choice.
    - Make sure that the project is configured to use Java 11 or higher.
   
3. **Run the main class `TrainDispatchApp`.**
    - Locate the `TrainDispatchApp` class in the project.
    - Run the `main` method within the `TrainDispatchApp` class.
    - This will launch the application and initialize essential components.

4. **Follow the instructions in the terminal.**
    - Once the application is running, follow the instructions prompted in the terminal.
    - Interact with the text-based menu to explore and manage train departures.
    - Input your choices as guided by the application's user interface.

**Note:** For the best experience, use a terminal that supports ANSI color codes.



## How to run the tests

1. **Locate the test by opening the `test` folder in the project.**
   - Locate the `test` folder in the project.
2. **Inside the `test` folder, find the `java` directory.**
    - The test classes, `TraindispatchSystemTest`, `TrainRegisterTest` and `TrainManagerTest`, are organized under specific packages.

3. **To run the tests, you have two options:**
    - Run the entire test class: 
       * Click on the green arrow next to the test class name.
       * This will execute all the tests in the selected class.
    - Run individual tests:
       * Open the test class.
       * Locate the green arrow next to a specific test method.
       * Click on the arrow to run that particular test.




## References

Below is a list of references that were really helpful during the project. 
They provided solutions, and inspiration at different points in the development process.

- Sorting HashMap: 
    - [Sort a HashMap in Java](https://www.baeldung.com/java-hashmap-sort)
    - [How to Sort a HashMap by Value in Java?](https://www.digitalocean.com/community/tutorials/sort-hashmap-by-value-java)                
- How to efficiently search in a collection: [Java Stream Filter with Lambda Expression](https://www.baeldung.com/java-stream-filter-lambda)
- How to use switch case: [Java switch statement](https://www.w3schools.com/java/java_switch.asp)
- How to use ANSI color codes in Java: [Print color in console using System.out.println](https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println)
- Reference for creating a method to generate spaces: [Create a string with N characters](https://stackoverflow.com/questions/2804827/create-a-string-with-n-characters)
- How to handle exceptions: [Run Java program after exception encountered](https://stackoverflow.com/questions/25263072/run-java-program-after-exception-encountered)
- How to use JavaDoc:
    - [What is Javadoc tool and how to use it?](https://www.geeksforgeeks.org/what-is-javadoc-tool-and-how-to-use-it/)
    - [JavaDoc standards](https://developer.atlassian.com/server/confluence/javadoc-standards/)
    - [Should JUnit tests have Javadoc?](https://stackoverflow.com/questions/1804540/should-junit-tests-be-javadocced/)