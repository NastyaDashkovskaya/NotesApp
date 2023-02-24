package ru.dashkovskajanastassia.notesapp;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Note implements Parcelable {
    private static final Random random = new Random();
    private static ArrayList<Note> notes;
    private static int counter;
    private int id;

    private String noteTitle;
    private String noteContent;
    private LocalDateTime noteDateOfCreation;

    public Note(String noteTitle, String noteContent, LocalDateTime dateOfCreation) {
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

    public LocalDateTime getNoteDateOfCreation() {
        return noteDateOfCreation;
    }

    public void setNoteDateOfCreation(LocalDateTime noteDateOfCreation) {
        this.noteDateOfCreation = noteDateOfCreation;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Note.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    {
        id = ++counter;
    }

    static {
        notes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            notes.add(Note.getNote(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    public static Note getNote(int index) {
        String noteTitle = String.format("Заметка %d", index);
        String noteContent = String.format("Описание заметки %d", index);
        LocalDateTime noteDateOfCreation = LocalDateTime.now().plusDays(-random.nextInt(5));
        return new Note(noteTitle, noteContent, noteDateOfCreation);
    }

    public static ArrayList<Note> getNotes() {
        return notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getNoteTitle());
        dest.writeString(getNoteContent());

    }

    protected Note(Parcel parcel) {
        id = parcel.readInt();
        noteTitle = parcel.readString();
        noteContent = parcel.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
