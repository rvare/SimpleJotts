package org.simplejotts.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListMenuItem {
	private String preview;
	private LocalDateTime dateCreated;

	public ListMenuItem(final String noteContent, final LocalDateTime dateCreated) {
		this.preview = noteContent.substring(0, 10);
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("%s...", this.preview);
	}
}
