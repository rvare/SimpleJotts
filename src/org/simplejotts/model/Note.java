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

package org.simplejotts.model;

import java.time.LocalDateTime;

public class Note {
	private String content;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;

	public Note() {
		this.dateCreated = LocalDateTime.now();
		this.dateModified = LocalDateTime.now();
	}

	public Note(final String content) {
		this.content = content;
		this.dateCreated = LocalDateTime.now();
		this.dateModified = LocalDateTime.now();
	}

	public Note(final String content, final LocalDateTime dateCreated, final LocalDateTime dateModified) {
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	// Getters
	public String getContent() {
		return this.content;
	}

	public LocalDateTime getDateCreated() {
		return this.dateCreated;
	}

	public LocalDateTime getDateModified() {
		return this.dateModified;
	}

	// Setters
	public void setContent(final String content) {
		this.dateModified = LocalDateTime.now();
		this.content = content;
	}

	public void setDateCreated(final LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateModified(final LocalDateTime dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public String toString() {
		return String.format("dateCreated: %s\ndateModified: %s\ncontent:\n%s\n", dateCreated, dateModified, content);
	}
} // End Note
