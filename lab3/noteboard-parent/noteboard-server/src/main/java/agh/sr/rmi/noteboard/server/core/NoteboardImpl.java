package agh.sr.rmi.noteboard.server.core;

import agh.sr.rmi.noteboard.api.Noteboard;

public class NoteboardImpl implements Noteboard {
	
	private StringBuffer buf;

	public NoteboardImpl() {
		buf = new StringBuffer();
	}

	public void appendText(String newNote) {
		// TODO: I was too lazy to implement it...
	}

	public void clear() {
		buf = new StringBuffer();
	}

	@Override
	public String getText() {
		// TODO: I was too lazy to implement it...
		return null;
	}
}
