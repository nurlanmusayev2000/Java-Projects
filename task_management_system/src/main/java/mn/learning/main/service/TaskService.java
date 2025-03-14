package mn.learning.main.service;

import java.util.ArrayList;
import java.util.List;
import mn.learning.main.models.Task;


public class TaskService {
	private List<Task> tasks = new ArrayList<>();

	public void addTask(String title, String description, String priority) {
		tasks.add(new Task(title, description, priority));
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public boolean markTaskAsCompleted(int index)
	{
		if (index >= 0 && index < tasks.size()) {
			tasks.get(index).markAsCompleted();
			return true;
		}
		return false;
	}

	public boolean removeTask(int index) {
		if (index >= 0 && index < tasks.size()) {
			tasks.remove(index);
			return true;
		}
		return false;
	}

}
