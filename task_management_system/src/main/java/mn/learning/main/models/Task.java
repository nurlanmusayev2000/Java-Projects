package mn.learning.main.models;

public class Task {
	private String title;
	private String description;
	private String priority;
	private boolean isCompleted;

	public Task(String title , String description , String priority)
	{
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.isCompleted = false;
	}

	public void markAsCompleted() {
		this.isCompleted = true;
	}

	@Override
	public String toString() {
    return (isCompleted ? "[✔] " : "[ ] ") + title + " - " + priority + "\n" + description;
	}
}
