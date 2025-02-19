package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The task storage of the chatbot.
 *
 * @author wz2k
 */
public class Storage {
    /** File for storage of tasks */
    private File file;

    /** Task storage file reader */
    private BufferedReader reader;

    /** Task storage file writer */
    private BufferedWriter writer;

    /**
     * Creates a storage for tasks at the specified file path.
     *
     * @param path Storage file path.
     * @throws IOException If an I/O error occurs.
     */
    public Storage(String path) throws IOException {
        file = new File(path);

        file.getParentFile().mkdirs();
        file.createNewFile();

        writer = new BufferedWriter(new FileWriter(path, true));
        reader = new BufferedReader(new FileReader(path));
    }

    /**
     * Returns the list of tasks in the storage.
     *
     * @return List of tasks from storage.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> getTasks() throws IOException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            String taskStorageString = reader.readLine();

            while (taskStorageString != null) {
                String[] data = taskStorageString.split("\\|");
                taskList.add(parseTaskData(data));
                taskStorageString = reader.readLine();
            }

            return taskList;
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Identifies the task type from its storage string.
     *
     * @param data Storage string from file.
     * @return Task instance.
     * @throws DukeException If parsing of data fails.
     */
    private Task parseTaskData(String[] data) throws DukeException {
        switch (data[0]) {
        case "T":
            assert data.length == 3 : "todo data should have 3 parts";
            return new Todo(data[2], Boolean.parseBoolean(data[1]));
        case "D":
            assert data.length == 4 : "deadline data should have 4 parts";
            return new Deadline(data[2], Boolean.parseBoolean(data[1]), data[3]);
        case "E":
            assert data.length == 5 : "event data should have 5 parts";
            return new Event(data[2], Boolean.parseBoolean(data[1]), data[3], data[4]);
        default:
            String exceptionMessage = "Error parsing storage data.";
            throw new DukeException(exceptionMessage);
        }
    }

    /**
     * Writes task details to file to store it.
     *
     * @param task Task to be stored.
     * @throws IOException If an I/O error occurs.
     */
    public void storeTask(Task task) throws IOException {
        writer.write(task.toTaskStorageString());
        writer.newLine();
        writer.flush();
    }

    /**
     * Updates task file storage based on current task list details.
     *
     * @param taskList List of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public void restructure(TaskList taskList) throws IOException {
        new FileWriter(file.getPath(), false).close();
        int size = taskList.getSize();

        for (int i = 1; i <= size; i++) {
            storeTask(taskList.getTask(i));
        }
    }
}
