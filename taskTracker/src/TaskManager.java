import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> taskList;
    private int nextId;
    private final Path filepath = Paths.get("tasks.json");

    public TaskManager() {
        taskList = loadTasks();
        if (taskList.size() > 0) {
            nextId = taskList.getLast().getId() + 1;
        } else {
            nextId = 0;
        }
    }

    /**
     * Load tasks from JSON file
     * @return Task List
     */
    private List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();
        if (Files.notExists(filepath)) {
            try {
                Files.createFile(filepath);
            } catch (IOException e) {
                System.out.println("An error occurred while creating file.");
                System.exit(-3);
            }
        } else {
            // Load JSON
            try (BufferedReader br = Files.newBufferedReader(filepath)) {
                // Skip [ symbol
                br.readLine();
                String line = null;
                // Load JSON object and convert to Task
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("]")) break;
                    Task task = new Task();
                    task.fromJson(line);
                    list.add(task);
                }

            } catch (IOException e) {
                System.out.println("An error occurred while loading.");
                System.exit(-1);
            }
        }

        return list;
    }

    /**
     * add Task
     * @param description task description
     */
    public void add(String description) {
        Task task = new Task(nextId++, description, Status.TODO, LocalDateTime.now());
        taskList.add(task);
        System.out.println("Task added succesfully (ID: " + task.getId() + ")");
    }

    /**
     * update task
     * @param id task id for update
     * @param description task description for update
     */
    public void update(int id, String description) {
        for (Task task: taskList) {
            if (task.getId() == id) {
                task.setDescription(description);
                System.out.println("Task updated succesfully (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Can't find task by ID: " + id);
    }

    /**
     * delete task
     * @param id task id for delete
     */
    public void delete(int id) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == id) {
                taskList.remove(i);
                System.out.println("Task deleted succesfully (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Can't find task by ID: " + id);
    }

    /**
     * mark task
     * @param id task id to mark
     * @param status task status to mark
     */
    public void mark(int id, Status status) {
        for (Task task: taskList) {
            if (task.getId() == id) {
                task.setStatus(status);
                System.out.println("Task marked to " + status + " succesfully (ID: " + id + ")");
                return;
            }
        }
        System.out.println("Can't find task by ID: " + id);
    }

    /**
     * list all tasks
     */
    public void listAll() {
        String line = "*" + "-".repeat(4) + "*" + "-".repeat(21)  + "*" + "-".repeat(11)  + "*" + "-".repeat(19)  + "*" + "-".repeat(19) + "*"; 
        System.out.println(line);
        System.out.println("| ID |     Description     |   Status  |      CreateAt     |      UpdateAt     |");
        System.out.println(line);

        for (Task task: taskList) {
            System.out.println(task.toString());
        }
    }

    /**
     * list tasks in status
     * @param status task status for listing
     */
    public void list(Status status) {
        String line = "*" + "-".repeat(4) + "*" + "-".repeat(21)  + "*" + "-".repeat(11)  + "*" + "-".repeat(19)  + "*" + "-".repeat(19) + "*"; 
        System.out.println(line);
        System.out.println("| ID |     Description     |   Status  |      CreateAt     |      UpdateAt     |");
        System.out.println(line);

        for (Task task: taskList) {
            if (task.getStatus() == status)
                System.out.println(task.toString());
        }
    }

    /**
     * save all tasks
     */
    public void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(taskList.get(i).toJson());
            if (i != taskList.size() - 1) 
                sb.append(",");
            sb.append("\n");
        }
        sb.append("]");

        try (BufferedWriter bw = Files.newBufferedWriter(filepath)) {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while saving.");
            System.exit(-2);
        }
    }
}
