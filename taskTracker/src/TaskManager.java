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

    private List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();
        if (Files.notExists(filepath)) {
            try {
                Files.createFile(filepath);
            } catch (IOException e) {

            }
        } else {
            // json 불러오기
            try (BufferedReader br = Files.newBufferedReader(filepath)) {
                // 첫줄과 마지막 줄은 [ ]
                br.readLine();
                String line = null;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("]")) break;
                    if (line.endsWith(",")) {

                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while loading.");
                System.exit(-1);
            }
        }

        return list;
    }

    public void add(String description) {
        Task task = new Task(nextId++, description, Status.TODO, LocalDateTime.now());
        taskList.add(task);
    }

    public void update(int id, String description) {
        
    }

    public void delete(int id) {

    }

    public void mark(int id, Status status) {

    }

    public void listAll() {

    }

    public void list(Status status) {

    }

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
