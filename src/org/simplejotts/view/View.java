package org.simplejotts.view;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.simplejotts.view.AboutDialog;
import org.simplejotts.view.DocumentationDialog;
import org.simplejotts.view.NoteWindow;
import org.simplejotts.view.ListMenuItem;

public class View extends JFrame {
	private String noteContentBuffer;

	// Menu bar
	private JMenuBar menuBar;
	private JMenu menu;

	// Meneu bar items
	private JMenuItem exportSelectedOption;
	private JMenuItem exportAllOption;
	private JMenuItem docOption;
	private JMenuItem aboutOption;

	// Buttons
	private JButton newButton;
	private JButton deleteButton;
	private JButton settingsButton;

	// List GUI
	private JList<ListMenuItem> noteList;
	private DefaultListModel<ListMenuItem> listModel;

	// Constants
	private final static short DEFAULT_WIDTH = 400;
	private final static short DEFAULT_HEIGHT = 600;
	private final static String WINDOW_TITLE = "Simple Jott";
	private final static String WINDOW_TITLE_DIRTY = "Simple Jott *";

	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create menu bar
		this.menuBar = new JMenuBar();

		// Create file menu and its items
		JMenu exportMenu = new JMenu("Export");
		this.exportSelectedOption = new JMenuItem("Export Selected");
		// this.exportSelectedOption.addActionListener(new ExportSelectedListener());
		exportMenu.add(this.exportSelectedOption);
		this.exportAllOption = new JMenuItem("Export All");
		// this.exportAllOption.addActionListener(new ExportAllListener());
		exportMenu.add(this.exportAllOption);

		// Create Help menu and its items
		JMenu helpMenu = new JMenu("Help");
		this.docOption = new JMenuItem("Documentation");
		// this.docOption.addActionListener(new DocumentaitonDialogListener());
		helpMenu.add(this.docOption);
		this.aboutOption = new JMenuItem("About");
		// this.aboutOption.addActionListener(new AboutDialogListener());
		helpMenu.add(this.aboutOption);

		// Add all menu items to menu bar
		this.menuBar.add(exportMenu);
		this.menuBar.add(helpMenu);
		this.setJMenuBar(this.menuBar);

		// Create Buttons
		this.newButton = new JButton("New");
		// this.newButton.addActionListener(new newButtonListener());
		this.deleteButton = new JButton("Delete");
		// this.settingsButton = new JButton("Settings");
		// this.settingsButton.setIcon(UIManager.getIcon("FileView.fileIcon"));

		// Create buttons panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.newButton);
		buttonPanel.add(this.deleteButton);
		// buttonPanel.add(this.settingsButton);
		this.getContentPane().add(BorderLayout.NORTH, buttonPanel);

		// Create JList
		this.listModel = new DefaultListModel<ListMenuItem>();
		this.noteList = new JList<ListMenuItem>();
		this.noteList.setModel(this.listModel);
		JPanel listPanel = new JPanel();
		// listPanel.add(new JScrollPane(this.noteList)); // Here until we know for certain we don't need it
		this.getContentPane().add(BorderLayout.CENTER, new JScrollPane(this.noteList));
		// this.getContentPane().add(BorderLayout.CENTER, listPanel);

		// Create the frame
		this.setTitle(this.WINDOW_TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		this.setResizable(false);
	} // End constructor

	// Getters
	public String getNoteContentBuffer() {
		return noteContentBuffer;
	}

	public JList<ListMenuItem> getNoteList() {
		return this.noteList;
	}

	// Setters
	public void setNoteContentBuffer(final String newContent) {
		this.noteContentBuffer = newContent;
	}

	// Methods
	public void showMainFrame() {
		this.setVisible(true);
	}
	
	public void displayAboutDialogWindow() {
		new AboutDialog().setVisible(true);
	}

	public void displayDocumentationDialogWindow() {
		new DocumentationDialog().setVisible(true);
	}

	public NoteWindow createNoteWindow() {
		return new NoteWindow();
	}

	public NoteWindow createNoteWindow(String content) {
		return new NoteWindow(content);
	}

	public JFileChooser createExportSelectedWindow() {
		JFileChooser fileExporter = new JFileChooser();
		fileExporter.setAcceptAllFileFilterUsed(false);

		FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter("HTML", "html");
		fileExporter.addChoosableFileFilter(htmlFilter);

		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text", "txt");
		fileExporter.addChoosableFileFilter(txtFilter);

		fileExporter.showSaveDialog(this);

		return fileExporter;
	}

	public JFileChooser createExportAllWindow() {
		JFileChooser fileExporter = new JFileChooser();
		fileExporter.setAcceptAllFileFilterUsed(false);

		FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter("HTML", "html");
		fileExporter.addChoosableFileFilter(htmlFilter);

		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text", "txt");
		fileExporter.addChoosableFileFilter(txtFilter);

		fileExporter.showSaveDialog(this);

		return fileExporter;
	}

	public void addToListModel(final LinkedList<ListMenuItem> ll) {
		System.out.println("Here");
		System.out.println(ll);
		listModel.clear();
		for (ListMenuItem i : ll) {
			this.listModel.addElement(i);
		}
	}

	public void refreshListModel(final LinkedList<ListMenuItem> ll) {
		this.listModel.removeAllElements();
		this.listModel.clear();

		for (ListMenuItem item : ll) { this.listModel.addElement(item); }
	}

	// Add Listeners
	public void addNewButtonListener(ActionListener newButtonListener) {
		this.newButton.addActionListener(newButtonListener);
		System.out.println("Does this still work?");
	}

	public void addDeleteButtonListener(ActionListener deleteButtonListener) {
		this.deleteButton.addActionListener(deleteButtonListener);
	}

	public void addAboutDialogListener(ActionListener aboutOptionListener) {
		this.aboutOption.addActionListener(aboutOptionListener);
	}

	public void addDocumentaitonDialogListener(ActionListener docOptionListener) {
		this.docOption.addActionListener(docOptionListener);
	}

	public void addExportSelectedListener(ActionListener exportSelectedListener) {
		this.exportSelectedOption.addActionListener(exportSelectedListener);
	}

	public void addExportAllListener(ActionListener exportAllListener) {
		this.exportAllOption.addActionListener(exportAllListener);
	}

	public void addListSelectionListener(ListSelectionListener ll) {
		this.noteList.addListSelectionListener(ll);
	}

	public void addDoubleClickListener(MouseAdapter ma) {
		noteList.addMouseListener(ma);
	}

	public void addMainWindowCloseListener(WindowAdapter closeListener) {
		this.addWindowListener(closeListener);
	}
} // End View class
