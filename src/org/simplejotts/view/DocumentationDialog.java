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
import java.io.*;
import java.util.*;

public class DocumentationDialog extends JDialog {
	public DocumentationDialog() {
		this.setTitle("Documentation");

		String docContent = """
							<html>
							<p>The following is some basic things about SimpleJotts.</p>
							<p>An important thing to note is that your notes are saved </p>
							<p>in the following file called simple_jotts_notes.json </p>
							<p>and should be in the same directory as this program.</p>
							</p>
							<p>To export a single note, select the note, go to file and</p
							<p>click 'export selected'. To export all, select 'export all'.</p>
							</html>
							""";
		
		JLabel docContentContainer = new JLabel(docContent);
		JPanel panel = new JPanel();
		panel.add(docContentContainer);
		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(500, 400);
		// this.setResizable(false);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}
}
