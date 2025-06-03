package org.simplejotts.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListMenuItem {
	private String preview;
	private LocalDateTime dateCreated;

	public ListMenuItem(final String noteContent, final LocalDateTime dateCreated) {
		if (noteContent.length() > 70) {
			this.preview = noteContent.substring(0, 70).concat("...");
		}
		else {
			this.preview = noteContent;
		}
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("%10s    %-75s", this.dateCreated.toLocalDate(), this.preview);
	}
}
