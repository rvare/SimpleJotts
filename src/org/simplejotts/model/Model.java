
/*
Copyright 2025 Richard Varela
This file is part of SimpleJotts.

SimpleJotts is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

SimpleJotts is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with SimpleJotts.
If not, see <https://www.gnu.org/licenses/>.
*/

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
		}
		catch(IOException ioEx) {
			System.out.println(ioEx.getMessage());
		}
		catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
		// String noteFileContents = this.openDataFile();

		if (!this.notesFile.exists() && !this.notesFile.isDirectory()) {
			// Add error message?
			return;
		}

		JSONObject jsonObject = new JSONObject(noteFileContents);
		JSONArray noteArray = new JSONArray(jsonObject.get("notes").toString());

		Iterator iter = noteArray.iterator();
		while (iter.hasNext()) {
			JSONObject obj = new JSONObject(iter.next().toString());
			String content = obj.getString("content");
			LocalDateTime dateCreated = LocalDateTime.parse(obj.getString("date_created"));
			LocalDateTime dateModified = LocalDateTime.parse(obj.getString("date_modified"));

			Note note = new Note(content, dateCreated, dateModified);
			this.noteList.add(note);
		}

	} // End Model constructor

	// Getters
	public void getNote() {
	}

	public LinkedList<Note> getList() {
		return this.noteList;
	}

	// Operations
	public void openDataFile() throws IOException {
	}

	public void saveDataFile() throws IOException, JSONException {
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
		Note note = new Note(noteContent); // This constructor also creates date created and modified.
		this.noteList.add(note);
	}

	public void deleteNote(final int index) {
		try {
			this.noteList.remove(index);
		}
		catch(IndexOutOfBoundsException iobEx) {
			System.out.println(iobEx.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void editNote(final int index, final String newContent) {
		Note note = this.noteList.get(index);
		note.setContent(newContent);
	}

	public void saveNotes() {
	}

	// Exporting
	public void exportSelectedNote(final JFileChooser fileChooser, final int selectedIndex) throws IOException, JSONException {

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
	}

	public void exportAllToText(final FileWriter fileWriter) throws IOException {
		for (Note note : noteList) {
			fileWriter.write(String.format("%s\n", note.getDateCreated().toString()));
			fileWriter.write(String.format("%s\n\n", note.getContent()));
		}
	}

	public void exportSelectedToText(final FileWriter fileWriter, final Note selectedNote) throws IOException {
		fileWriter.write(String.format("%s\n", selectedNote.getDateCreated().toString()));
		fileWriter.write(selectedNote.getContent());
	}
} // End Model
