// java/com/omkar/studymate/MainActivity.java
package com.omkar.studymate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    // Declare buttons for each class
    Button btnClass8, btnClass9, btnClass10, btnClass11, btnClass12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all buttons by their IDs from activity_main.xml
        btnClass8  = (Button) findViewById(R.id.btn_class8);
        btnClass9  = (Button) findViewById(R.id.btn_class9);
        btnClass10 = (Button) findViewById(R.id.btn_class10);
        btnClass11 = (Button) findViewById(R.id.btn_class11);
        btnClass12 = (Button) findViewById(R.id.btn_class12);

        // When Class 8 is clicked, pass "Class 8" to SubjectActivity
        btnClass8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubjectActivity("Class 8");
            }
        });

        // When Class 9 is clicked
        btnClass9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubjectActivity("Class 9");
            }
        });

        // When Class 10 is clicked
        btnClass10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubjectActivity("Class 10");
            }
        });

        // When Class 11 is clicked
        btnClass11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubjectActivity("Class 11");
            }
        });

        // When Class 12 is clicked
        btnClass12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubjectActivity("Class 12");
            }
        });
    }

    // Helper method to start SubjectActivity and pass the class name
    private void openSubjectActivity(String className) {
        Intent intent = new Intent(MainActivity.this, SubjectActivity.class);
        intent.putExtra("class", className); // pass selected class using key "class"
        startActivity(intent);
    }
}
