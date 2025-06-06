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

public class NoteWindow extends JDialog {
	private String noteContentBuffer;
	private boolean cancelFlag;

	// Text format buttons
	// private JButton boldButton;
	// private JButton italicButton;
	// private JButton unorderListButton;
	// private JButton orderListButton;

	// Buttons
	private JButton saveButton;
	private JButton cancelButton;

	// Text Area
	private JTextArea editorArea;
	// private JTextPane editorArea;

	// Constants
	private static short DEFAULT_WIDTH = 500;
	private static short DEFAULT_HEIGHT = 500;

	public NoteWindow(String noteContent) {
		System.out.println("NoteWindow constructor");
		// Create format buttons
		// Leaving these here for now when I get a chance to implement them.
		// JPanel formatPanel = new JPanel();
		// this.boldButton = new JButton("Bold");
		// this.italicButton = new JButton("Italic");
		// this.unorderListButton = new JButton("Bullet");
		// this.orderListButton = new JButton("Number");
		// formatPanel.add(this.boldButton);
		// formatPanel.add(this.italicButton);
		// formatPanel.add(this.unorderListButton);
		// formatPanel.add(this.orderListButton);
		// this.getContentPane().add(BorderLayout.NORTH, formatPanel);

		// Setting variables
		this.cancelFlag = true;

		// Create editor
		JPanel editorPanel = new JPanel();
		this.getContentPane().add(BorderLayout.CENTER, editorPanel);
		this.editorArea = noteContent == null ? new JTextArea(20, 40) : new JTextArea(noteContent, 20, 40);
		// this.editorArea = new JTextPane();
		this.editorArea.setLineWrap(true);
		this.editorArea.setFont(editorArea.getFont().deriveFont(14f));
		JScrollPane scroller = new JScrollPane(this.editorArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorPanel.add(scroller);

		// Create buttons
		JPanel buttonPanel = new JPanel();

		// Save button
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
		this.saveButton = new JButton("Save");
		this.saveButton.addActionListener(new SaveButtonListener());
		buttonPanel.add(this.saveButton);

		// Cancel button
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(new CancelButtonListener());
		buttonPanel.add(this.cancelButton);

		// Set window attributes
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setResizable(false);
	} // End construction

	public NoteWindow() {
		this(null);
	}

	// Getters
	public String getTextEditorContent() {
		return this.editorArea.getText();
	}

	public boolean getCancelFlag() {
		return this.cancelFlag;
	}

	// Listeners
	private class CancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Cancel button hit");
			cancelFlag = true;
			dispose();
		}
	}

	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Save button hit");
			cancelFlag = false;
			dispose();
		}
	}
} // End NoteWindow
