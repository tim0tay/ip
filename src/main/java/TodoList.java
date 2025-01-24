import java.util.ArrayList;
import java.util.List;
public class TodoList {
    private final List<String> list;

    public TodoList() {
        this.list = new ArrayList<>();
    }

    public String addToList(String todo) {
        if (todo == null || todo.isEmpty()) {
            return "Please.. don't make me work harder than I have to!\n";
        }
        this.list.add(todo);
        return "Task added to list: " + todo;
    }

    public String enumerateList() {
        StringBuilder result = new StringBuilder("Let's see what you have on your plate:\n");
        for (int i = 0; i < this.list.size(); i++) {
            result.append(i+1).append(". ").append(this.list.get(i)).append("\n");
        }
        if (this.list.isEmpty()) {
            result.append("So gewd ah.. nothing to do!\n");
        } else if (this.list.size() > 4) {
            result.append("Seems like you have quite a few things to do.. go easy on yourself!\n");
        }
        return result.toString();
    }
}
