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

public class NoteWindow extends JFrame {
	private String noteContentBuffer;

	// Text format buttons
	private JButton boldButton;
	private JButton italicButton;
	private JButton unorderListButton;
	private JButton orderListButton;

	// Buttons
	private JButton saveButton;
	private JButton cancelButton;

	// Text Area
	private JTextArea editorArea;

	// Constants
	private static short DEFAULT_WIDTH = 500;
	private static short DEFAULT_HEIGHT = 450;

	public NoteWindow() {
		System.out.println("NoteWindow constructor");
		// Create format buttons
		JPanel formatPanel = new JPanel();
		this.boldButton = new JButton("Bold");
		this.italicButton = new JButton("Italic");
		this.unorderListButton = new JButton("Bullet");
		this.orderListButton = new JButton("Number");
		formatPanel.add(this.boldButton);
		formatPanel.add(this.italicButton);
		formatPanel.add(this.unorderListButton);
		formatPanel.add(this.orderListButton);
		this.getContentPane().add(BorderLayout.NORTH, formatPanel);

		// Create editor
		JPanel editorPanel = new JPanel();
		this.getContentPane().add(BorderLayout.CENTER, editorPanel);
		this.editorArea = new JTextArea(20, 40);
		this.editorArea.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(this.editorArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorPanel.add(scroller);

		// Create buttons
		JPanel buttonPanel = new JPanel();
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
		this.saveButton = new JButton("Save");
		// this.saveButton.addActionListener();
		buttonPanel.add(this.saveButton);
		this.cancelButton = new JButton("Cancel");
		// this.cancelButton.addActionListener();
		buttonPanel.add(this.cancelButton);

		// Set window attributes
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setResizable(false);
	}

	// Getters
	public String getTextEditorContent() {
		return this.editorArea.getText();
	}

	// Setters

	// Listeners
}
