// java/com/omkar/studymate/ChapterActivity.java
package com.omkar.studymate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChapterActivity extends Activity {

    // Declare UI elements
    TextView tvChapterTitle, tvSubjectLabel;
    ListView lvChapters;

    // Store received class and subject
    String selectedClass, selectedSubject;

    // Array to hold chapter list shown in ListView
    String[] chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        // Get data passed from SubjectActivity
        selectedClass   = getIntent().getStringExtra("class");
        selectedSubject = getIntent().getStringExtra("subject");

        // Find UI elements
        tvChapterTitle = (TextView) findViewById(R.id.tv_chapter_title);
        tvSubjectLabel = (TextView) findViewById(R.id.tv_subject_label);
        lvChapters     = (ListView) findViewById(R.id.lv_chapters);

        // Display subject and class in labels
        tvChapterTitle.setText("Chapters - " + selectedSubject);
        tvSubjectLabel.setText(selectedClass + " | " + selectedSubject);

        // Load chapters based on the selected subject
        chapters = getChaptersForSubject(selectedSubject);

        // Set up ArrayAdapter to show chapters in ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            chapters
        );
        lvChapters.setAdapter(adapter);

        // Handle chapter click: open ContentActivity with chapter name
        lvChapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedChapter = chapters[position];
                Intent intent = new Intent(ChapterActivity.this, ContentActivity.class);
                intent.putExtra("class",   selectedClass);
                intent.putExtra("subject", selectedSubject);
                intent.putExtra("chapter", selectedChapter); // pass selected chapter
                startActivity(intent);
            }
        });
    }

    // Returns a list of chapters based on subject
    private String[] getChaptersForSubject(String subject) {

        if (subject.equals("Science")) {
            return new String[]{
                "Chapter 1: Crop Production and Management",
                "Chapter 2: Microorganisms: Friend and Foe",
                "Chapter 3: Synthetic Fibres and Plastics",
                "Chapter 4: Materials: Metals and Non-Metals",
                "Chapter 5: Coal and Petroleum"
            };
        } else if (subject.equals("Maths")) {
            return new String[]{
                "Chapter 1: Rational Numbers",
                "Chapter 2: Linear Equations in One Variable",
                "Chapter 3: Understanding Quadrilaterals",
                "Chapter 4: Data Handling",
                "Chapter 5: Squares and Square Roots"
            };
        } else if (subject.equals("History")) {
            return new String[]{
                "Chapter 1: How, When and Where",
                "Chapter 2: From Trade to Territory",
                "Chapter 3: Ruling the Countryside",
                "Chapter 4: Tribals, Dikus and the Vision of a Golden Age",
                "Chapter 5: When People Rebel"
            };
        } else if (subject.equals("Geography")) {
            return new String[]{
                "Chapter 1: Resources",
                "Chapter 2: Land, Soil, Water, Natural Vegetation and Wildlife Resources",
                "Chapter 3: Mineral and Power Resources",
                "Chapter 4: Agriculture",
                "Chapter 5: Industries"
            };
        } else if (subject.equals("English")) {
            return new String[]{
                "Chapter 1: The Best Christmas Present in the World",
                "Chapter 2: The Tsunami",
                "Chapter 3: Glimpses of the Past",
                "Chapter 4: Bepin Choudhury's Lapse of Memory",
                "Chapter 5: The Summit Within"
            };
        } else {
            // Default fallback chapters
            return new String[]{
                "Chapter 1: Introduction",
                "Chapter 2: Core Concepts",
                "Chapter 3: Advanced Topics"
            };
        }
    }
}
