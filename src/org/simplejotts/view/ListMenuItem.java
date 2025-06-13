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

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListMenuItem {
	private String preview;
	private LocalDateTime dateCreated;
	private static short MAX_LENGTH = 35;

	public ListMenuItem(final String noteContent, final LocalDateTime dateCreated) {
		if (noteContent.length() > MAX_LENGTH) {
			this.preview = noteContent.replace('\n', ' ').substring(0, MAX_LENGTH).concat("...");
		}
		else {
			this.preview = noteContent;
		}
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("%10s  %-35s", this.dateCreated.toLocalDate(), this.preview);
	}
}
