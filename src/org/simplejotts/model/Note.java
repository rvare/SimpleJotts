package org.simplejotts.model;

import java.time.LocalDateTime;

public class Note {
	private int id;
	private String content;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;

	public Note() {
		this.dateCreated = LocalDateTime.now();
		this.dateModified = LocalDateTime.now();
	}

	public Note(final int id, final String content) {
		this.id = id;
		this.content = content;
		this.dateCreated = LocalDateTime.now();
		this.dateCreated = LocalDateTime.now();
	}

	public Note(final int id, final String content, final LocalDateTime dateCreated, final LocalDateTime dateModified) {
		System.out.println("Called");
		this.id = id;
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	// Getters
	public int getId() {
		return this.id;
	}

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
	public void setId(final int id) {
		this.id = id;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setDateCreated(final LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("id: %d\ndateCreated: %s\ndateModified: %s\ncontent:\n%s\n", id, dateCreated, dateModified, content);
	}
}
