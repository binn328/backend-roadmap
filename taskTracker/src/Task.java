import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public Task() {}

    public Task(
        int id, 
        String description, 
        Status status,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = createAt;
    }

    /**
     * Convert Task class to JSON
     * @return JSON Object String
     */
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"" + id + "\",");
        sb.append("\"description\":\"" + description + "\",");
        sb.append("\"status\":\"" + status + "\",");
        sb.append("\"createAt\":\"" + createAt.format(FORMATTER) + "\",");
        sb.append("\"updateAt\":\"" + updateAt.format(FORMATTER) + "\"");
        sb.append("}");

        return sb.toString();
    }

    /**
     * Convert JSON to Task class
     * @param json JSON Object String to Convert
     */
    public void fromJson(String json) {
        json = json.replaceAll("\\{", "");
        json = json.replaceAll("\\}", "");
        json = json.replaceAll("\"", "");

        String[] splitJson = json.split(",");
        for (String element: splitJson) {
            String[] splitElement = element.split(":", 2);
            String key = splitElement[0];
            String value = splitElement[1];

            switch (key) {
                case "id" -> this.id = Integer.parseInt(value);
                case "description" -> this.description = value;
                case "status" -> this.status = Status.statusFromString(value);
                case "createAt" -> this.createAt = LocalDateTime.parse(value, FORMATTER);
                case "updateAt" -> this.updateAt = LocalDateTime.parse(value, FORMATTER);
            }
        }

    }

    /**
     * Convert Task class to String for list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%4d|%21s|%11s|%19s|%19s|\n", id, description, status, createAt, updateAt));
        sb.append("*" + "-".repeat(4) + "*" + "-".repeat(21)  + "*" + "-".repeat(11)  + "*" + "-".repeat(19)  + "*" + "-".repeat(19) + "*");
        return sb.toString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}


