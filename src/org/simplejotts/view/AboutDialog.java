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

public class AboutDialog extends JDialog {
	private static short DEFAULT_WIDTH = 250;
	private static short DEFAULT_HEIGHT = 150;

	public AboutDialog() {
		this.setTitle("About");

		JLabel applicationTitle = new JLabel("Simple Jotts");
		applicationTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel authorAndCopyrightLabel = new JLabel("(c) 2025 Richard Varela", SwingConstants.CENTER);
		authorAndCopyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel versionNumber = new JLabel("v1.0", SwingConstants.CENTER);
		versionNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(applicationTitle);
		panel.add(authorAndCopyrightLabel);
		panel.add(versionNumber);
		
		this.getContentPane().add(BorderLayout.CENTER, panel);

		this.pack();
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
	}

}
