package org.simplejotts.model;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.*;
import java.time.LocalDate;
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
			LocalDate dateCreated = LocalDate.parse(obj.getString("date_created"));
			LocalDate dateModified = LocalDate.parse(obj.getString("date_modified"));

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
		LocalDate dateCreated = LocalDate.now();
		LocalDate dateModified = LocalDate.now();

		Note note = new Note(id, noteContent, dateCreated, dateModified);
		System.out.println(note);
		this.noteList.add(note);
		System.out.println(this.noteList);
	}

	public void deleteNote() {
		System.out.println("deleteNote");
	}

	public void editNote() {
		System.out.println("editNote");
	}

	public void saveNotes() {
		System.out.println("saveNote");
	}

	// Exporting
	public void exportSelectedNote() throws IOException, JSONException {
		System.out.println("exportSelectedNote");
	}

	public void exportAllNotes() throws IOException, JSONException {
		System.out.println("exportAllNotes");
	}

	public void exportToHTML() throws IOException {
		System.out.println("exportToHTML");
	}

	public void exportToMarkdown() throws IOException {
		System.out.println("exportToMarkdown");
	}

	public void exportToText() throws IOException {
		System.out.println("exportToText");
	}
} // End Model
