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
