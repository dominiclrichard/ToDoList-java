package csc335.ToDoList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToDoList implements Serializable {

	private static final long serialVersionUID = 1L;
	public ArrayList<String> toDoList;
	private String fileName;
	private FileInputStream rawBytes; 
	
	//TODO
	public ToDoList() {
		toDoList = new ArrayList<>();
		fileName = "myTODOs.ser";
	}
	
	//TODO
	public void addItem(String item) {
		toDoList.add(item);
		this.serialize();
	}
	
	public void removeItem(String item) {
		toDoList.remove(item);
		this.serialize();
	}
	
	private void serialize() {
		
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(toDoList);
			outFile.close();
		}
		
		catch (IOException ioe) {
			System.out.println("Writing Objects Failed");
		}
	}
	
	public ArrayList<String> getList() {
		try {
			rawBytes = new FileInputStream(fileName);
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			ArrayList<String> list = (ArrayList<String>) inFile.readObject();
			inFile.close();
			
			return list;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
