package com.example.scoredatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText scoreEditText;
    TextView displayTextView;
    MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate references to these XML components
        nameEditText = (EditText) findViewById(R.id.name);
        scoreEditText = (EditText) findViewById(R.id.score);
        displayTextView = (TextView) findViewById(R.id.myScores);
        databaseHelper = new MyDatabaseHelper(this, null, null, 1);
        printDB();  // prints current database contents

    }


    public void addButtonClicked(View v) {
        // gets score name and value from edittext and uses it to create a new Scores object
        // it adds this element to the database and then reprints the database to show the change

        Scores score = new Scores(nameEditText.getText().toString(), Integer.parseInt(scoreEditText.getText().toString()));
        databaseHelper.addScore(score);

        printDB();

        nameEditText.setText("");
        scoreEditText.setText("");

    }

    public void removeButtonClicked(View v){
        // need to first get the name from the edittext field of the score that you want to delete
        // call the removeScore method to remove score from the database, then print it again

        String nameToRemove = nameEditText.getText().toString();
        databaseHelper.removeScore(nameToRemove);

        printDB();

        nameEditText.setText("");
        scoreEditText.setText("");
    }

    public void printDB() {
        // calls the method that creates a string of all the database elements
        // sets this string to the text in Textview component

        String dbString = databaseHelper.databasetoString();
        displayTextView.setText(dbString);
    }
}
