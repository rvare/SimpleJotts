package org.simplejotts.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListMenuItem {
	private String preview;
	private LocalDateTime dateCreated;

	public ListMenuItem(final String noteContent, final LocalDateTime dateCreated) {
		System.out.println("ListMenuItem constructor");
		if (noteContent.length() > 50) {
			this.preview = noteContent.replace('\n', ' ').substring(0, 50).concat("...");
		}
		else {
			this.preview = noteContent;
		}
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("%10s    %-55s", this.dateCreated.toLocalDate(), this.preview);
	}
}
