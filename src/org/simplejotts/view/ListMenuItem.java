package org.simplejotts.view;

import java.time.LocalDate;

public class ListMenuItem {
	private String preview;
	private LocalDate dateCreated;

	public ListMenuItem(final String noteContent, final LocalDate dateCreated) {
		this.preview = noteContent.substring(0, 10);
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("%s...", this.preview);
	}
}
