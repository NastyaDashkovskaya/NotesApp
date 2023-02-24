package ru.dashkovskajanastassia.notesapp;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesFragment extends Fragment {
    private static  final String  NOTE = "index";
    private int currentPosition = 0;
    static final String SELECTED_NOTE = "note";
    Note note;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_notes, container, false);
        }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putParcelable(SELECTED_NOTE, note);
            super.onSaveInstanceState(outState);
    }

    private boolean isLandscape(){
        return  getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle
                savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if(savedInstanceState != null){

               note = (Note)savedInstanceState.getParcelable(SELECTED_NOTE);
            }
        dataConteiner = view.findViewById(R.id.fragment_container);
            initListNotes(view);
            if(isLandscape()){
                ShowTextLand(note);
            }

        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        private void initListNotes(View view) {
            LinearLayout layoutView = (LinearLayout) view;


            for (int i = 0; i < Note.getNotes().size(); i++) {
                TextView tv = new TextView(getContext());
                tv.setText(Note.getNotes().get(i).getNoteTitle());
                tv.setTextSize(30);
                layoutView.addView(tv);
                final  int index = i;
                tv.setOnClickListener(v ->{
                    ShowText(Note.getNotes().get(index));
            });

        }
    }

    private void ShowText(Note note){
            this.note = note;
        if(isLandscape()){
            ShowTextLand(note);
        }
        else{
            ShowTextPort(note);
        }

    }
    private void ShowTextPort(Note note){
        NoteFragment noteFragment = NoteFragment.newInstance(note);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteFragment)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    private void ShowTextLand(Note note){
        NoteFragment noteFragment = NoteFragment.newInstance(note);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_text_container, noteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    View dataConteiner;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initListNotes(){
        initListNotes(dataConteiner);

    }
}

















