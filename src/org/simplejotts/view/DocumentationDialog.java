package org.simplejotts.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class DocumentationDialog extends JDialog {
	public DocumentationDialog() {
		System.out.println("Doc Dialog");
		this.setTitle("Documentation");

		String docContent = """
							<html><p>Will add more later.</p></html>
							""";
		
		JLabel docContentContainer = new JLabel(docContent);
		JPanel panel = new JPanel();
		panel.add(docContentContainer);
		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(500, 400);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
}
