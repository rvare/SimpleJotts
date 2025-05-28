package org.simplejotts.controller;

import java.util.*;
import java.util.LinkedList;
import java.io.*;
import java.time.LocalDate;
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

	public void refreshViewListModel() {
		// LinkedList<Note> noteList = this.model.getNoteList();
		// this.view.refreshListModel();
	}

	// Operations
	private void newOperation() {
		System.out.println("newOperation");
		NoteWindow noteWindow = this.view.createNoteWindow();
		noteWindow.setVisible(true);
		System.out.println("Execution continues");

		if (noteWindow.getCancelFlag()) { return; }

		// int id = assign id
		String content = noteWindow.getTextEditorContent();
		System.out.println(String.format("Note contents: %s", content));
		// LocalDateTime dateCreated = new LocalDateTime();
		// LocalDateTime dateModified = new LocalDateTime();
		// this.model.newNote();
		// this.refreshViewListModel();
	}

	private void editOperation() {
		System.out.println("Edit operaiton");
	}

	private void deleteOperation() {
		System.out.println("Delete operaiton");
	}

	// Menu bar listeners
	private class ExportSelectedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Export listener");
			JFileChooser exportWindow = view.createExportSelectedWindow();
		}
	}

	private class ExportAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Export all listener");
			JFileChooser exportWindow = view.createExportAllWindow();
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
			}
		}
	}
} // End Controller
