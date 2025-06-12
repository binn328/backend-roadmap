public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public static Status statusFromString(String str) {
        return switch (str) {
            case "TODO" -> TODO;
            case "IN_PROGRESS" -> IN_PROGRESS;
            case "DONE" -> DONE;
            default -> TODO;
        };
    }

}
