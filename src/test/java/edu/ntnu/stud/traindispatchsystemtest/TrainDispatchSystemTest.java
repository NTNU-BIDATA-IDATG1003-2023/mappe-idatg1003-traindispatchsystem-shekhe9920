package edu.ntnu.stud.traindispatchsystemtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDispatchSystemTest {

    TrainDispatchSystem trainDispatchSystemTest;
    @BeforeEach
    void setUp() {

        trainDispatchSystemTest =
            new TrainDispatchSystem("Gjøvik", "Oslo",
                LocalTime.of(8, 55), "F1", 1, "706");

    }

    @AfterEach
    void tearDown() {
        trainDispatchSystemTest = null;
    }

    @Test
    void setPositiveTestForTrainNumber() {

        // Arrange
        setUp();

        // Act
        trainDispatchSystemTest.setTrainNumber("706");

        // Assert
        assertEquals("706", trainDispatchSystemTest.getTrainNumber(),
            "Train wasn't set");
        System.out.println("Train was set successfully");
    }

    @Test
    void negativeTestForTrainNumber() {

        // Arrange
        setUp();

        // Act
        trainDispatchSystemTest.setTrainNumber(null);

        // Assert
        assertNotEquals("706", trainDispatchSystemTest.getTrainNumber(),
            "Train was set");
        System.out.println("Train was not set successfully");
    }
}