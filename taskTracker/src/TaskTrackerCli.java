public class TaskTrackerCli {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        if (args.length < 1) {
            System.out.println("usage: TaskTrackerCli <command> [arg...]");
            return;
        }

        String command = args[0];
        String arg1 = null;
        String arg2 = null;

        if (args.length > 1) {
            arg1 = args[1];
        }
        if (args.length > 2) {
            arg2 = args[2];
        }
        
        switch(command) {
            case "add" -> tm.add(arg1);
            case "update" -> tm.update(Integer.parseInt(arg1), arg2);
            case "delete" -> tm.delete(Integer.parseInt(arg1));
            case "mark-in-progress" -> tm.mark(Integer.parseInt(arg1), Status.IN_PROGRESS);
            case "mark-done" -> tm.mark(Integer.parseInt(arg1), Status.DONE);
            case "list" -> {
                if (arg1 == null || arg1.isEmpty()) {
                    tm.listAll();
                } else {
                    switch (arg1) {
                        case "done" -> tm.list(Status.DONE);
                        case "todo" -> tm.list(Status.TODO);
                        case "in-progress" -> tm.list(Status.IN_PROGRESS);
                    }
                }
            }
            default -> System.out.println("usage: TaskTrackerCli <command> [arg...]");
        }
        tm.save();
    }
}
