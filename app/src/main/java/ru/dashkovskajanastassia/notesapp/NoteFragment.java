package ru.dashkovskajanastassia.notesapp;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class NoteFragment extends Fragment {

    static final String SELECTED_NOTE = "note";
    Note note;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(savedInstanceState != null){
         requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null){

            note = (Note) arguments.getParcelable(SELECTED_NOTE);
            TextView text = view.findViewById(R.id.name);
            // ОШИБКА
            text.setText(note.getNoteTitle());

            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
              note.setNoteTitle(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            TextView tv = view.findViewById(R.id.Description);
        tv.setText(note.getNoteContent());

        Button buttonBack = view.findViewById(R.id.buttonback);
        if(buttonBack != null){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });}

        }
    }
    static final String ARG_INDEX = "index";

    public static NoteFragment newInstance(int index){
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX,index);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance(Note note){
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.note_menu,menu);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_delete){

            Note.getNotes().remove(note);
            note = null;
            updateData();
            if(!isLandscape()){
                requireActivity().getSupportFragmentManager().popBackStack();
            }
            Toast.makeText(getContext(), "Удаление произошло успешно", Toast.LENGTH_LONG).show();
            return true;

        }
        return super.onOptionsItemSelected(item);

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateData(){
        for(Fragment fragment: requireActivity().getSupportFragmentManager().getFragments()){
            if(fragment instanceof  NotesFragment){
                ((NotesFragment)fragment).initListNotes();
                break;
            }
        };

    }
    private boolean isLandscape(){
        return  getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    }}














