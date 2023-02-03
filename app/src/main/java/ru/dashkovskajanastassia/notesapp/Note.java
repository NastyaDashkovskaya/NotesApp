package ru.dashkovskajanastassia.notesapp;


import java.util.Calendar;

public class Note {
    private String noteTitle;
    private String noteContent;
    private String noteDateOfCreation;

    public Note(String noteTitle, String noteContent, String dateOfCreation) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteDateOfCreation = dateOfCreation;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteDateOfCreation() {
        return noteDateOfCreation;
    }

    public void setNoteDateOfCreation(String noteDateOfCreation) {
        this.noteDateOfCreation = noteDateOfCreation;
    }
}
