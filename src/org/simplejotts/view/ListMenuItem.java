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
