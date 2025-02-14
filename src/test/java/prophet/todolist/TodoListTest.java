package prophet.todolist;

import prophet.task.ToDoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoListTest {
    @Test
    public void testAddToListNull() {
        assertEquals("Please.. don't make me work harder than I have to!\n",
                new TodoList().addToList(null));
    }
    @Test
    public void testAddToListTest() {
        assertEquals("Task added to list: T | [ ] test\n",
                new TodoList().addToList(new ToDoTask("test")));
    }

    @Test
    public void testEnumerateListEmpty() {
        assertEquals("Let's see what you have on your plate:\nSo gewd ah.. nothing to do!\n",
                new TodoList().enumerateList());
    }

}
