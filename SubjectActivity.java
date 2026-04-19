// java/com/omkar/studymate/SubjectActivity.java
package com.omkar.studymate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubjectActivity extends Activity {

    // Declare UI elements
    TextView tvClassTitle;
    Button btnScience, btnMaths, btnHistory, btnGeography, btnEnglish;

    // Store the class name received from MainActivity
    String selectedClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        // Get the class name passed from MainActivity using key "class"
        selectedClass = getIntent().getStringExtra("class");

        // Find UI elements
        tvClassTitle  = (TextView) findViewById(R.id.tv_class_title);
        btnScience    = (Button) findViewById(R.id.btn_science);
        btnMaths      = (Button) findViewById(R.id.btn_maths);
        btnHistory    = (Button) findViewById(R.id.btn_history);
        btnGeography  = (Button) findViewById(R.id.btn_geography);
        btnEnglish    = (Button) findViewById(R.id.btn_english);

        // Show the selected class in the title
        tvClassTitle.setText(selectedClass + " - Select Subject");

        // Science button click
        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChapterActivity("Science");
            }
        });

        // Maths button click
        btnMaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChapterActivity("Maths");
            }
        });

        // History button click
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChapterActivity("History");
            }
        });

        // Geography button click
        btnGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChapterActivity("Geography");
            }
        });

        // English button click
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChapterActivity("English");
            }
        });
    }

    // Helper method: open ChapterActivity with class and subject
    private void openChapterActivity(String subject) {
        Intent intent = new Intent(SubjectActivity.this, ChapterActivity.class);
        intent.putExtra("class", selectedClass);   // pass class name
        intent.putExtra("subject", subject);        // pass subject name
        startActivity(intent);
    }
}
