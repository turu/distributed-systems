package agh.sr.rmi.noteboard.api;

public interface Noteboard {
	
	public String getText();
	
	public void appendText(String newNote);
	
	public void clear();
	
}

