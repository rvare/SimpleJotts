package org.simplejotts.model;

import java.time.LocalDate;

public class Note {
	private int id;
	private String content;
	private LocalDate dateCreated;
	private LocalDate dateModified;

	public Note() {
		this.dateCreated = LocalDate.now();
		this.dateModified = LocalDate.now();
	}

	public Note(final int id, final String content) {
		this.id = id;
		this.content = content;
		this.dateCreated = LocalDate.now();
		this.dateCreated = LocalDate.now();
	}

	public Note(final int id, final String content, final LocalDate dateCreated, final LocalDate dateModified) {
		System.out.println("Called");
		this.id = id;
		this.content = content;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	public int getId() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	public LocalDate getDateCreated() {
		return this.dateCreated;
	}

	public LocalDate getDateModified() {
		return this.dateModified;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public void setDateCreated(final LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return String.format("id: %d\ndateCreated: %s\ndateModified: %s\ncontent:\n%s\n", id, dateCreated, dateModified, content);
	}
}
