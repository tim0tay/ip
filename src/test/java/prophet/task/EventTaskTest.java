package prophet.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    @Test
    public void testToStringValid() {
        // Test case where the event task is from 2021-09-01 to 2021-09-02
        EventTask eventTask = new EventTask(
                "CS2103T Lecture", LocalDate.parse("2021-09-01"), LocalDate.parse("2021-09-02"));
        assertEquals("E | [ ] CS2103T Lecture from: Sep 01 2021 to: Sep 02 2021\n", eventTask.toString());
    }

    @Test
    public void testGetStatusIconValid() {
        // Test case where the event task is from 2021-09-01 to 2021-09-02
        EventTask eventTask = new EventTask(
                "CS2103T Lecture", LocalDate.parse("2021-09-01"), LocalDate.parse("2021-09-02"));
        eventTask.setDone();
        assertEquals("E | [X] ", eventTask.getStatusIcon());
    }

}
