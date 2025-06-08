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
	private File notesFile;

	// Constants
	private static String FILE_PATH = "../simple_jotts_notes.json";

	public Model() {
		this.noteList = new LinkedList<Note>();
		this.dirtyFlag = false;

		// Open file and get contents
		String noteFileContents = " ";
		try {
			this.notesFile = new File(FILE_PATH);
			noteFileContents = Files.readString(Paths.get(FILE_PATH));
			System.out.println(noteFileContents);
		}
		catch(IOException ioEx) {
			System.out.println(ioEx.getMessage());
		}
		catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
		// String noteFileContents = this.openDataFile();

		System.out.println(!this.notesFile.exists() && this.notesFile.isDirectory());
		if (!this.notesFile.exists() && !this.notesFile.isDirectory()) {
			System.out.println("DNE");
			return;
		}

		JSONObject jsonObject = new JSONObject(noteFileContents);
		System.out.println(jsonObject);
		JSONArray noteArray = new JSONArray(jsonObject.get("notes").toString());
		System.out.println(noteArray);

		Iterator iter = noteArray.iterator();
		while (iter.hasNext()) {
			JSONObject obj = new JSONObject(iter.next().toString());
			System.out.println(obj);
			String content = obj.getString("content");
			LocalDateTime dateCreated = LocalDateTime.parse(obj.getString("date_created"));
			LocalDateTime dateModified = LocalDateTime.parse(obj.getString("date_modified"));

			Note note = new Note(content, dateCreated, dateModified);
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

	public void saveDataFile() throws IOException, JSONException {
		System.out.println("saveDataFile");
		StringBuilder jsonContents = createJsonObject();

		FileWriter fileWriter = new FileWriter(this.FILE_PATH);
		fileWriter.write(jsonContents.toString());
		fileWriter.close();
	}

	public StringBuilder createJsonObject() {
		JSONArray jsonArr = new JSONArray();
		for (Note note : this.noteList) {
			jsonArr.put(new JSONObject().put("date_created", note.getDateCreated())
										.put("date_modified", note.getDateModified())
										.put("content", note.getContent()));
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", 1.0);
		jsonObject.put("notes", jsonArr);

		return new StringBuilder(jsonObject.toString());
	}

	public void newNote(String noteContent) {
		System.out.println("newNote");

		Note note = new Note(noteContent); // This constructor also creates date created and modified.
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
		System.out.println("exportAllToText");
		for (Note note : noteList) {
			fileWriter.write(String.format("%s\n", note.getDateCreated().toString()));
			fileWriter.write(String.format("%s\n\n", note.getContent()));
		}
	}

	public void exportSelectedToText(final FileWriter fileWriter, final Note selectedNote) throws IOException {
		System.out.println("exportSelectedToText");
		fileWriter.write(String.format("%s\n", selectedNote.getDateCreated().toString()));
		fileWriter.write(selectedNote.getContent());
	}
} // End Model
