import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

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

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"" + id + "\",");
        sb.append("\"description\":\"" + description + "\",");
        sb.append("\"status\":\"" + status + "\",");
        sb.append("\"createAt\":\"" + createAt + "\",");
        sb.append("\"updateAt\":\"" + updateAt + "\"");
        sb.append("}");

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


