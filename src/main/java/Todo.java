/**
 * Represents a todo task.
 *
 * @author wz2k
 */
public class Todo extends Task {
    /**
     * Constructor for class Todo.
     *
     * @param desc description of the todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * This method returns the task type, checkbox and description.
     *
     * @return todo task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
