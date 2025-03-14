package mn.learning.main;

import java.util.List;
import java.util.Scanner;
import mn.learning.main.models.Task;
import mn.learning.main.service.TaskService;

public class Main {
    private static TaskService taskService = new TaskService();

		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("\n1. Add Task 2. List Task 3.Mark Completed 4.Remove Task 5. Exit");
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
					case 1:
						addTask(scanner);
						break;  // ✅ Added break

					case 2:
						listTasks();
						break;  // ✅ Added break
					case 3:
						markTaskAsCompleted(scanner);
						break;  // ✅ Added break
					case 4:
						removeTask(scanner);
						break;
					case 5:
						System.out.println("exiting ...");
						return;
					default:
						System.out.println("invalid Option ");
						return;
				}
			}
		}

		public static void addTask(Scanner scanner) {
			System.out.println("you are in te addtask method ");
			System.out.println("Enter title");
			String title = scanner.nextLine();
			System.out.println("Enter Description");
			String description = scanner.nextLine();
			System.out.println("Enter priority (High/Medium/Low):");
			String priority = scanner.nextLine();
			taskService.addTask(title, description, priority);
		}
	    private static void listTasks() {
    		List<Task> tasks = taskService.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Method to mark a task as completed
    private static void markTaskAsCompleted(Scanner scanner) {
        listTasks();
        System.out.println("Enter task number to mark as completed:");
        int index = scanner.nextInt() - 1;

        if (!taskService.markTaskAsCompleted(index)) {
            System.out.println("Invalid task number.");
        }
    }

    // Method to remove a task
		private static void removeTask(Scanner scanner) {
			listTasks();
			System.out.println("Enter task number to remove:");
			int index = scanner.nextInt() - 1;

			if (!taskService.removeTask(index)) {
				System.out.println("Invalid task number.");
			}
		}

}
