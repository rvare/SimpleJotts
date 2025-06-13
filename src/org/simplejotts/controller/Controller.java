package org.simplejotts.controller;

import java.util.*;
import java.util.LinkedList;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.*;

import org.simplejotts.model.*;
import org.simplejotts.view.*;

public class Controller {
	private final View view;
	private final Model model;
	private ListMenuItem selectedItem;
	private int selectedItemIndex;

	public Controller(final Model model, final View view) {
		this.model = model;
		this.view = view;

		this.view.addMainWindowCloseListener(new MainWindowCloseListener());

		// Create menu bar listeners
		this.view.addAboutDialogListener(new AboutDialogListener());
		this.view.addDocumentaitonDialogListener(new DocumentaitonDialogListener());
		this.view.addExportSelectedListener(new ExportSelectedListener());
		this.view.addExportAllListener(new ExportAllListener());

		// Create button listeners
		this.view.addNewButtonListener(new newButtonListener());
		this.view.addDeleteButtonListener(new deleteButtonListener());

		// Create list listeners
		this.view.addListSelectionListener(new ListListener());

		// Create mouse listeners
		this.view.addDoubleClickListener(new DoubleClickListener());

		// this.view.addToListModel((LinkedList<Object>)this.model.getList());
		LinkedList<Note> noteList = this.model.getList();
		LinkedList<ListMenuItem> ll = new LinkedList<ListMenuItem>();
		for (Note n : noteList) {
			ll.add(new ListMenuItem(n.getContent(), n.getDateCreated()));
		}

		this.view.addToListModel(ll);
	}

	// Operations
	public void refreshViewListModel() {
		LinkedList<Note> noteList = this.model.getList();
		LinkedList<ListMenuItem> linkedList = new LinkedList<ListMenuItem>();
		for (Note note : noteList) {
			linkedList.add(new ListMenuItem(note.getContent(), note.getDateCreated()));
		}
		this.view.refreshListModel(linkedList);
	}

	private void newOperation() {
		NoteWindow noteWindow = this.view.createNoteWindow();
		noteWindow.setVisible(true);

		if (noteWindow.getCancelFlag()) { return; }

		String content = noteWindow.getTextEditorContent();
		// LocalDateTime dateCreated = LocalDateTime.now();
		// LocalDateTime dateModified = LocalDateTime.now();
		this.model.newNote(content);
		this.refreshViewListModel();
	}

	private void editOperation(String newContent) {
		this.model.editNote(selectedItemIndex, newContent);
		this.refreshViewListModel();
	}

	private void deleteOperation() {
		this.model.deleteNote(selectedItemIndex);
		this.refreshViewListModel();
	}

	// Menu bar listeners
	private class ExportSelectedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser exportWindow = view.createExportSelectedWindow();

			try {
				model.exportSelectedNote(exportWindow, selectedItemIndex);
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
				view.createErrorWindow("Could not save file"); // Call view to display IO error message
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
				view.createErrorWindow("Could not save file, JSON issue"); // Call view to display JSON error message
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
				view.createErrorWindow("Something unexpected happened"); // Call view to display generic error message
			}
		}
	}

	private class ExportAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser exportWindow = view.createExportAllWindow();

			try {
				model.exportAllNotes(exportWindow);
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
				view.createErrorWindow("Could not save file"); // Call view to display IO error message
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
				view.createErrorWindow("Could not save file, JSON issue"); // Call view to display JSON error message
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
				view.createErrorWindow("Something unexpected happened"); // Call view to display generic error message
			}
		}
	}

	private class DocumentaitonDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			view.displayDocumentationDialogWindow();
		}
	}

	private class AboutDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			view.displayAboutDialogWindow();
		}
	}

	// Button listeners
	private class newButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			newOperation();
		}
	}

	private class deleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			deleteOperation();
		}
	}

	// JList listener
	private class ListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent listEvent) {
			if (!listEvent.getValueIsAdjusting()) {
				ListMenuItem item = view.getNoteList().getSelectedValue(); // Gets the selected list item
				if (item == null) { return; }
				selectedItem = item;
				selectedItemIndex = view.getNoteList().getLeadSelectionIndex(); // Gets the index of the selected item
			}
		}
	}

	// Mouse listener
	private class DoubleClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (evt.getClickCount() == 2) {
				LinkedList<Note> linkedList = model.getList();
				Note note = linkedList.get(selectedItemIndex);
				NoteWindow noteWindow = view.createNoteWindow(note.getContent());
				noteWindow.setVisible(true);
				if (noteWindow.getCancelFlag()) { return; }
				editOperation(noteWindow.getTextEditorContent());
			}
		}
	}

	private class MainWindowCloseListener extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			try {
				model.saveDataFile();
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
				view.createErrorWindow("Could not save file"); // Call view to display IO error message
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
				view.createErrorWindow("Could not save file, JSON issue"); // Call view to display JSON error message
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
				view.createErrorWindow("Something unexpected happened"); // Call view to display generic error message
			}
		}
	}
} // End Controller
