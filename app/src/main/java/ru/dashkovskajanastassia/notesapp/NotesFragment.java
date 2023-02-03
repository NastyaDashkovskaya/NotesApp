package ru.dashkovskajanastassia.notesapp;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_notes, container, false);
        }

        private static  final String  NOTE = "Note";
        private int currentPosition = 0;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(NOTE, currentPosition);
            super.onSaveInstanceState(outState);
    }

    private boolean isLandscape(){
        return  getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    }
    @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle
                savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if(savedInstanceState != null){
                currentPosition = savedInstanceState.getInt(NOTE, 0);
            }
            initListNotes(view);
            if(isLandscape()){
                ShowTextLand(currentPosition);
            }
        }
        // создаём список городов на экране из массива в ресурсах
        private void initListNotes(View view) {
            LinearLayout layoutView = (LinearLayout) view;
            String[] notes = getResources().getStringArray(R.array.notes);

            for (int i = 0; i < notes.length; i++) {
                TextView tv = new TextView(getContext());
                tv.setText(notes[i]);
                tv.setTextSize(30);
                layoutView.addView(tv);
                final  int index = i;
                tv.setOnClickListener(v ->{
                    ShowText(index);
            });

        }
    }
    private void ShowText(int index){
        if(isLandscape()){
            ShowTextLand(index);
        }
        else{
            ShowTextPort(index);
        }

    }
    private void ShowTextPort(int index){
        NoteFragment noteFragment = NoteFragment.newInstance(index);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteFragment)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    private void ShowTextLand(int index){
        NoteFragment noteFragment = NoteFragment.newInstance(index);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_text_container, noteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}