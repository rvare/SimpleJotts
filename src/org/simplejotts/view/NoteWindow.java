/*
Copyright 2025 Richard Varela
This file is part of SimpleJotts.

SimpleJotts is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

SimpleJotts is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with SimpleJotts.
If not, see <https://www.gnu.org/licenses/>.
*/

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

	private static short DEFAULT_EDITOR_ROWS = 24;
	private static short DEFAULT_EDITOR_COLS = 50;

	public NoteWindow(String noteContent) {
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
		this.editorArea = noteContent == null ? new JTextArea(DEFAULT_EDITOR_ROWS, DEFAULT_EDITOR_COLS)
											  : new JTextArea(noteContent, DEFAULT_EDITOR_ROWS, DEFAULT_EDITOR_COLS);
		this.editorArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		// this.editorArea = new JTextPane();
		this.editorArea.setLineWrap(true);
		this.editorArea.setFont(editorArea.getFont().deriveFont(14f));
		JScrollPane scroller = new JScrollPane(this.editorArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		editorPanel.add(scroller);

		// Create buttons
		// Create button panel
		JPanel buttonPanel = new JPanel();
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

		// Cancel button
		this.cancelButton = new JButton("Discard");
		this.cancelButton.addActionListener(new CancelButtonListener());
		buttonPanel.add(this.cancelButton);

		// Set window attributes
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setResizable(false);
		this.addWindowListener(new NoteWindowCloseHandler());
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
			cancelFlag = true;
			dispose();
		}
	}

	private class NoteWindowCloseHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			cancelFlag = false;
			dispose();
		}
	}
} // End NoteWindow
