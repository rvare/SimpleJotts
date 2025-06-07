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
		LinkedList<ListMenuItem> ll = new LinkedList<ListMenuItem>();
		for (Note n : noteList) {
			ll.add(new ListMenuItem(n.getContent(), n.getDateCreated()));
		}
		System.out.println(ll);
		this.view.refreshListModel(ll);
	}

	private void newOperation() {
		System.out.println("newOperation");
		NoteWindow noteWindow = this.view.createNoteWindow();
		noteWindow.setVisible(true);
		System.out.println("Execution continues");

		if (noteWindow.getCancelFlag()) { return; }

		// int id = assign id
		String content = noteWindow.getTextEditorContent();
		System.out.println(String.format("Note contents: %s", content));
		// LocalDateTime dateCreated = LocalDateTime.now();
		// LocalDateTime dateModified = LocalDateTime.now();
		// System.out.println(dateCreated);
		// System.out.println(dateModified);
		this.model.newNote(content);
		this.refreshViewListModel();
	}

	private void editOperation(String newContent) {
		System.out.println("Edit operaiton");
		this.model.editNote(selectedItemIndex, newContent);
		this.refreshViewListModel();
	}

	private void deleteOperation() {
		System.out.println("Delete operaiton");
		this.model.deleteNote(selectedItemIndex);
		this.refreshViewListModel();
	}

	// Menu bar listeners
	private class ExportSelectedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Export listener");
			JFileChooser exportWindow = view.createExportSelectedWindow();

			try {
				model.exportSelectedNote(exportWindow, selectedItemIndex);
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
				// Call view to display IO error message
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
				// Call view to display JSON error message
			}
			catch(Exception ex) {
				System.out.println("Error");
				System.out.println(ex.getMessage());
				// Call view to display generic error message
			}
		}
	}

	private class ExportAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Export all listener");
			JFileChooser exportWindow = view.createExportAllWindow();

			try {
				model.exportAllNotes(exportWindow);
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
				// Call view to display IO error message
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
				// Call view to display JSON error message
			}
			catch(Exception ex) {
				System.out.println("Error");
				System.out.println(ex.getMessage());
				// Call view to display generic error message
			}
		}
	}

	private class DocumentaitonDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Doc listener");
			view.displayDocumentationDialogWindow();
		}
	}

	private class AboutDialogListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("About dialog listener");
			view.displayAboutDialogWindow();
		}
	}

	// Button listeners
	private class newButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("New button hit");
			newOperation();
		}
	}

	private class deleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Delete button hit");
			deleteOperation();
		}
	}

	// JList listener
	private class ListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent listEvent) {
			if (!listEvent.getValueIsAdjusting()) {
				ListMenuItem item = view.getNoteList().getSelectedValue();
				if (item == null) { return; }
				selectedItem = item;
				selectedItemIndex = view.getNoteList().getLeadSelectionIndex();
				System.out.println(item);
				System.out.println(selectedItemIndex);
			}
		}
	}

	// Mouse listener
	private class DoubleClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent evt) {
			System.out.println("Single clicked");
			if (evt.getClickCount() == 2) {
				System.out.println("Double click");
				LinkedList<Note> ll = model.getList();
				Note n = ll.get(selectedItemIndex);
				System.out.println(n);
				NoteWindow noteWindow = view.createNoteWindow(n.getContent());
				noteWindow.setVisible(true);
				if (noteWindow.getCancelFlag()) { return; }
				System.out.println("Continues in DoubleClickListener");
				editOperation(noteWindow.getTextEditorContent());
			}
		}
	}

	private class MainWindowCloseListener extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.out.println("Wow, it's closing");
			try {
				model.saveDataFile();
			}
			catch(IOException ioEx) {
				System.out.println(ioEx.getMessage());
			}
			catch(JSONException jsonEx) {
				System.out.println(jsonEx.getMessage());
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
} // End Controller
