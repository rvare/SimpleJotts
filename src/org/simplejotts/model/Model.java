package org.simplejotts.model;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.*;

import org.simplejotts.model.Note;

public class Model {
	private LinkedList<Note> noteList;
	private boolean dirtyFlag;
	private int curr_id_number;

	// Constants
	private static String FILE_PATH = "../../test.json";

	public Model() {
		this.noteList = new LinkedList<Note>();
		this.dirtyFlag = false;

		// Open file and get contents
		String noteFileContents;
		try {
			noteFileContents = Files.readString(Paths.get(FILE_PATH));
			System.out.println(noteFileContents);
		}
		catch(Exception exp) {
			System.out.println(exp.getMessage());
			noteFileContents = " ";
		}
		// String noteFileContents = this.openDataFile();

		JSONObject jsonObject = new JSONObject(noteFileContents);
		System.out.println(jsonObject);
		this.curr_id_number = jsonObject.getInt("id_accumulator");
		JSONArray noteArray = new JSONArray(jsonObject.get("notes").toString());
		System.out.println(noteArray);

		Iterator iter = noteArray.iterator();
		while (iter.hasNext()) {
			JSONObject obj = new JSONObject(iter.next().toString());
			System.out.println(obj);
			int id = obj.getInt("id");
			String content = obj.getString("content");
			LocalDateTime dateCreated = LocalDateTime.parse(obj.getString("date_created"));
			LocalDateTime dateModified = LocalDateTime.parse(obj.getString("date_modified"));

			Note note = new Note(id, content, dateCreated, dateModified);
			System.out.println(note);
			this.noteList.add(note);
		}

		System.out.println(this.noteList);
	} // End Model constructor

	// Getters
	public void getNote() {
		System.out.println("getNote");
	}

	public LinkedList<Note> getList() {
		return this.noteList;
	}

	// Operations
	public void openDataFile() throws IOException {
		System.out.println("openDateFile");
	}

	public void saveDataFile() {
		System.out.println("saveDataFile");
	}

	public void newNote(String noteContent) {
		System.out.println("newNote");
		int id = this.curr_id_number++;
		LocalDateTime dateCreated = LocalDateTime.now();
		LocalDateTime dateModified = LocalDateTime.now();

		Note note = new Note(id, noteContent, dateCreated, dateModified);
		System.out.println(note);
		this.noteList.add(note);
		System.out.println(this.noteList);
	}

	public void deleteNote(final int index) {
		System.out.println("deleteNote");
		this.noteList.remove(index);
	}

	public void editNote(final int index, final String newContent) {
		System.out.println("editNote");
		Note note = this.noteList.get(index);
		note.setContent(newContent);
	}

	public void saveNotes() {
		System.out.println("saveNote");
	}

	// Exporting
	public void exportSelectedNote(final JFileChooser fileChooser, final int selectedIndex) throws IOException, JSONException {
		System.out.println("exportSelectedNote");

		File exportFilePath = fileChooser.getSelectedFile();
		FileWriter fileWriter = new FileWriter(exportFilePath);
		FileNameExtensionFilter fileFilter = (FileNameExtensionFilter)fileChooser.getFileFilter();
		Note selectedNote = this.noteList.get(selectedIndex);

		if (fileFilter.getDescription().equals("HTML")) {
			this.exportSelectedToHTML(fileWriter, selectedNote);
		}
		else if (fileFilter.getDescription().equals("Text")) {
			this.exportSelectedToText(fileWriter, selectedNote);
		}

		fileWriter.close();
	}

	public void exportAllNotes(final JFileChooser fileChooser) throws IOException, JSONException {
		System.out.println("exportAllNotes");

		File exportedFilePath = fileChooser.getSelectedFile();
		FileWriter fileWriter = new FileWriter(exportedFilePath);

		FileNameExtensionFilter fileFilter = (FileNameExtensionFilter)fileChooser.getFileFilter();
		if (fileFilter.getDescription().equals("HTML")) {
			this.exportAllToHTML(fileWriter);
		}
		else if (fileFilter.getDescription().equals("Text")) {
			this.exportAllToText(fileWriter);
		}

		fileWriter.close();
	}

	public void exportAllToHTML(final FileWriter fileWriter) throws IOException {
		System.out.println("exportAllToHTML");
		fileWriter.write("<!DOCTYPE HTML>");
		fileWriter.write("\n<html>");
		fileWriter.write("\n\t<body>");

		for (Note note : noteList) {
			fileWriter.write(String.format("\n\t\t<h1>%s %s</h1>",
											note.getDateCreated().toLocalDate(),
											note.getDateCreated().toLocalTime()));
			String[] noteLines = note.getContent().split("\n");
			for (String line : noteLines) {
				fileWriter.write("\n\t\t<p>");
				fileWriter.write(String.format("\n\t\t\t%s", line));
				fileWriter.write("\n\t\t</p>");
			}
		}

		fileWriter.write("\n\t</body>");
		fileWriter.write("\n</html>");
	}

	public void exportSelectedToHTML(final FileWriter fileWriter, final Note selectedNote) throws IOException {
		String[] noteLines = selectedNote.getContent().split("\n");

		fileWriter.write("<!DOCTYPE HTML>");
		fileWriter.write("\n<html>");
		fileWriter.write("\n\t<body>");

		for (String line : noteLines) {
			fileWriter.write(String.format("\n\t\t<h1>%s %s</h1>",
											selectedNote.getDateCreated().toLocalDate(),
											selectedNote.getDateCreated().toLocalTime()));
			fileWriter.write("\n\t\t<p>");
			fileWriter.write(String.format("\n\t\t\t%s", line));
			fileWriter.write("\n\t\t</p>");
		}

		fileWriter.write("\n\t</body>");
		fileWriter.write("\n</html>");
	}

	public void exportToMarkdown() throws IOException {
		System.out.println("exportToMarkdown");
	}

	public void exportAllToText(final FileWriter fileWriter) throws IOException {
		System.out.println("exportToText");
	}

	public void exportSelectedToText(final FileWriter fileWriter, final Note selectedNote) throws IOException {

	}
} // End Model
