package io.github.habibu.dailytakefive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*Global variables */
    private TextView noteList;
    private String noted;
    private String notes;
    private SharedPreferences saveNotes;
    public static final String MY_PREF_FILE = "saveFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
        /*
        * Setting icon with the actionBar
        * */


            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.dailytake_5);

            /*main contents*/
            setContentView(R.layout.activity_main);

            noteList = (TextView) findViewById(R.id.notes_text);
            saveNotes = getSharedPreferences(MY_PREF_FILE, Context.MODE_APPEND);
            noteList.setText(saveNotes.getString("saveNote", notes));
        /*
        saveNotes = getSharedPreferences("notes", MODE_APPEND);
        //noteList.setText(saveNotes.getString("saveNote", "List of Daily Notes" + "\n"));
        noteList.setText(saveNotes.getString("saveNote", notes));
        saveNotes = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        */
        } catch (Exception e) {
            Log.e("ERROR", "ERROR FOUND AT:" + e.toString());
            e.printStackTrace();
        }
    }

    /*
        * Method to call while saving the notes
        *
    public void showNotes(String saveNote) {
        String note = saveNotes.getString(saveNote, null);
        SharedPreferences.Editor preferenceEditor = saveNotes.edit();
        preferenceEditor.putString("saveNote", saveNote);
        preferenceEditor.commit();
    }
    */

    /*
    * Method to response for the add note button after click
    * */
    public void takeNote(View view) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.add_notes_dialog, null);
        final EditText editTextNote = (EditText) alertLayout.findViewById(R.id.notes_list_text);
        /*
        * building alerdialog
        * */
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Write your five goals");
        alert.setView(alertLayout);
        /*
        * Disallowed closing the popup ufon clicking outside area*/
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getBaseContext(), "Note canceled", Toast.LENGTH_SHORT).show();

            }
        });
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //noteList = (TextView) findViewById(R.id.notes_text);
                notes = editTextNote.getText().toString();
                SharedPreferences.Editor editor = saveNotes.edit();
                editor.putString(noted, notes);
                editor.apply();
                noteList.append(notes);
                Toast.makeText(getBaseContext(), "Take five saved successfully", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

}
