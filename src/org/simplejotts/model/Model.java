package org.simplejotts.model;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.*;

import org.simplejotts.model.Note;

public class Model {
	private LinkedList<Note> noteList;
	private boolean dirtyFlag;

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

		JSONObject jsonObject = new JSONObject(noteFileContents);
		System.out.println(jsonObject);
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

	}

	// Operations
	public void openDataFile() {

	}

	public void saveDataFile() {

	}

	public void newNote() {

	}

	public void deleteNote() {

	}

	public void editNote() {

	}

	public void saveNotes() {

	}

	// Exporting
	public void exportSelectedNote() {

	}

	public void exportAllNotes() {

	}
} // End Model
