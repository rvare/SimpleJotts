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

	public Controller(final Model model, final View view) {
		this.model = model;
		this.view = view;

		// Create menu bar listeners

		// Create button listeners

		// Create list listeners

	}

	public refreshViewListModel() {

	}

	// Operations
	private void newOperation() {

	}

	private void editOperation() {

	}

	private void deleteOperation() {

	}

	// Menu bar listeners
	private class ExportSelectedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser exportWindow = createExportSelectedWindow();
		}
	}

	private class ExportAllListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser exportWindow = createExportAllWindow();
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
			NoteWindow noteWindow = new NoteWindow();
			noteWindow.setVisible(true);
		}
	}

	private class deleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {

		}
	}

	// JList listener
	private class listListener implements ListSelectionListener {
		@Override
		public void valueCHanged(ListSelectionEvent listEvent) {
			if (!listEvent.getValueIsAdjusting()) {
			}
		}
	}
} // End Controller
