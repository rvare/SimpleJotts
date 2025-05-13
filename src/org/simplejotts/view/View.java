package org.simplejotts.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;

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
	private JList<Object> noteList;
	private DefaultListModel<Object> listModel;

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
		exportMenu.add(this.exportSelectedOption);
		this.exportAllOption = new JMenuItem("Export All");
		exportMenu.add(this.exportAllOption);

		// Create Help menu and its items
		JMenu helpMenu = new JMenu("Help");
		this.docOption = new JMenuItem("Documentation");
		helpMenu.add(this.docOption);
		this.aboutOption = new JMenuItem("About");
		helpMenu.add(this.aboutOption);

		// Add all menu items to menu bar
		this.menuBar.add(exportMenu);
		this.menuBar.add(helpMenu);
		this.setJMenuBar(this.menuBar);

		// Create Buttons
		this.newButton = new JButton("New");
		this.newButton.addActionListener(new newButtonListener());
		this.deleteButton = new JButton("Delete");
		this.settingsButton = new JButton("Settings");
		// this.settingsButton.setIcon(UIManager.getIcon("FileView.fileIcon"));

		// Create buttons panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.newButton);
		buttonPanel.add(this.deleteButton);
		buttonPanel.add(this.settingsButton);
		this.getContentPane().add(BorderLayout.NORTH, buttonPanel);

		// Create JList
		this.listModel = new DefaultListModel<Object>();
		this.noteList = new JList<Object>();
		JPanel listPanel = new JPanel();
		// listPanel.add(new JScrollPane(this.noteList)); // Here until we know for certain we don't need it
		this.getContentPane().add(BorderLayout.CENTER, new JScrollPane(this.noteList));

		// Create the frame
		this.setTitle(this.WINDOW_TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
		this.setResizable(false);
	}

	// Getters
	public String getNoteContentBuffer() {
		return " ";
	}

	// Setters
	public void setNoteContentBuffer(final String newContent) {
		this.noteContentBuffer = newContent;
	}

	// Methods
	public void showMainFrame() {
		this.setVisible(true);
	}
	
	// Listeners
	private class newButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			NoteWindow noteWindow = new NoteWindow();
			noteWindow.setVisible(true);
		}
	}
} // End View class
