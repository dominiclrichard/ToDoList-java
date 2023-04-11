package csc335.ToDoList;

import java.io.IOException;

public class ToDoListMain {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ToDoList list = new ToDoList();
		list.addItem("Eat");
		list.addItem("Do Homework");
		list.addItem("Study for Exam");
		list.addItem("Go to Gym");
	}

}
