package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalTime;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDispatchSystemTest {

    @BeforeEach
    void setUp() {
        HashSet<TrainDispatchSystem> trainDispatchListTest = new HashSet<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setPositiveTestForTrainNumber() {
        TrainDispatchSystem trainDispatchListTest1 =
            new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "706");

        assertEquals("706",
            trainDispatchListTest1.getTrainNumber(), "Train number is 706");
    }

    @Test
    void negativeTestForTrainNumber() {
        TrainDispatchSystem trainDispatchListTest2 =
            new TrainDispatchSystem("Gjøvik", "Oslo",
                LocalTime.of(8, 55), "F1", 1, null);

        assertNotEquals( "Train number is null",
            trainDispatchListTest2.getTrainNumber(), "Train number is null");
    }
}