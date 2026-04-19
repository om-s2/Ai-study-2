// java/com/omkar/studymate/QuizActivity.java
package com.omkar.studymate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {

    // Declare UI elements
    TextView tvQuestionNumber, tvScore, tvQuestion, tvResultMsg;
    Button btnOptionA, btnOptionB, btnOptionC, btnOptionD, btnNext;

    // Score and current question index
    int score = 0;
    int currentQuestion = 0;

    // -------------------------------------------------------------------------
    // QUIZ DATA: 10 MCQs covering Science, Maths, History, Geography, English
    // -------------------------------------------------------------------------

    // Questions array
    String[] questions = {
        // Science Questions
        "Which of the following is a Kharif crop?",
        "Which microorganism is used to make curd?",
        "What is the chemical formula of water?",

        // Maths Questions
        "Which of the following is NOT a rational number?",
        "Solve: 2x + 5 = 15. Find x.",

        // History Questions
        "When did the British Crown take control of India?",
        "Who wrote 'A History of British India'?",

        // Geography Questions
        "Which of the following is a renewable resource?",
        "Coal is an example of which type of resource?",

        // English Questions
        "In 'The Best Christmas Present in the World', what sport did soldiers play on Christmas Day?"
    };

    // Options for each question (4 options per question)
    String[][] options = {
        // Q1 options
        {"Wheat", "Mustard", "Paddy", "Gram"},
        // Q2 options
        {"Yeast", "Lactobacillus", "Amoeba", "Virus"},
        // Q3 options
        {"H2O2", "CO2", "H2O", "O2"},
        // Q4 options
        {"3/5", "√2", "0", "-7/3"},
        // Q5 options
        {"x = 3", "x = 5", "x = 7", "x = 10"},
        // Q6 options
        {"1857", "1858", "1947", "1900"},
        // Q7 options
        {"Karl Marx", "James Mill", "Jawaharlal Nehru", "Charles Darwin"},
        // Q8 options
        {"Coal", "Solar Energy", "Natural Gas", "Petroleum"},
        // Q9 options
        {"Renewable", "Human-Made", "Non-Renewable", "Biotic"},
        // Q10 options
        {"Cricket", "Hockey", "Tennis", "Football"}
    };

    // Correct answer index for each question (0 = A, 1 = B, 2 = C, 3 = D)
    int[] answers = {
        2,  // Q1: Paddy (C)
        1,  // Q2: Lactobacillus (B)
        2,  // Q3: H2O (C)
        1,  // Q4: √2 (B) — irrational
        1,  // Q5: x = 5 (B)
        1,  // Q6: 1858 (B)
        1,  // Q7: James Mill (B)
        1,  // Q8: Solar Energy (B)
        2,  // Q9: Non-Renewable (C)
        3   // Q10: Football (D)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Find all UI elements by ID
        tvQuestionNumber = (TextView) findViewById(R.id.tv_question_number);
        tvScore          = (TextView) findViewById(R.id.tv_score);
        tvQuestion       = (TextView) findViewById(R.id.tv_question);
        tvResultMsg      = (TextView) findViewById(R.id.tv_result_msg);
        btnOptionA       = (Button) findViewById(R.id.btn_option_a);
        btnOptionB       = (Button) findViewById(R.id.btn_option_b);
        btnOptionC       = (Button) findViewById(R.id.btn_option_c);
        btnOptionD       = (Button) findViewById(R.id.btn_option_d);
        btnNext          = (Button) findViewById(R.id.btn_next);

        // Load the first question
        loadQuestion(currentQuestion);

        // Option A clicked
        btnOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0); // index 0 = Option A
            }
        });

        // Option B clicked
        btnOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1); // index 1 = Option B
            }
        });

        // Option C clicked
        btnOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2); // index 2 = Option C
            }
        });

        // Option D clicked
        btnOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3); // index 3 = Option D
            }
        });

        // Next button clicked: go to next question or show final score
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion++; // move to next question

                if (currentQuestion < questions.length) {
                    // More questions remain: load next question
                    loadQuestion(currentQuestion);
                } else {
                    // All questions done: show final score dialog
                    showFinalScore();
                }
            }
        });
    }

    // Loads a question and its 4 options into the UI
    private void loadQuestion(int index) {
        // Update question number label
        tvQuestionNumber.setText("Question " + (index + 1) + " of " + questions.length);

        // Set question text
        tvQuestion.setText(questions[index]);

        // Set option button labels (A, B, C, D)
        btnOptionA.setText("A. " + options[index][0]);
        btnOptionB.setText("B. " + options[index][1]);
        btnOptionC.setText("C. " + options[index][2]);
        btnOptionD.setText("D. " + options[index][3]);

        // Reset option button colors to default blue
        resetButtonColors();

        // Clear any previous result message
        tvResultMsg.setText("");

        // Hide Next button until user selects an answer
        btnNext.setVisibility(View.GONE);

        // Re-enable all option buttons
        enableOptions(true);
    }

    // Checks if the selected answer (selectedIndex) is correct
    private void checkAnswer(int selectedIndex) {
        // Disable all option buttons so user cannot change answer
        enableOptions(false);

        int correctIndex = answers[currentQuestion];

        if (selectedIndex == correctIndex) {
            // Correct answer
            score++;
            tvResultMsg.setText("✅ Correct!");
            tvResultMsg.setTextColor(0xFF388E3C); // green
            highlightButton(selectedIndex, 0xFF388E3C); // green highlight
        } else {
            // Wrong answer: show correct answer in green, wrong in red
            tvResultMsg.setText("❌ Wrong! Correct answer: " + getOptionLetter(correctIndex));
            tvResultMsg.setTextColor(0xFFC62828); // red
            highlightButton(selectedIndex, 0xFFC62828);  // red for wrong
            highlightButton(correctIndex, 0xFF388E3C);   // green for correct
        }

        // Update score display
        tvScore.setText("Score: " + score);

        // Show Next button
        btnNext.setVisibility(View.VISIBLE);
    }

    // Highlights a specific option button with the given color
    private void highlightButton(int index, int color) {
        switch (index) {
            case 0: btnOptionA.setBackgroundColor(color); break;
            case 1: btnOptionB.setBackgroundColor(color); break;
            case 2: btnOptionC.setBackgroundColor(color); break;
            case 3: btnOptionD.setBackgroundColor(color); break;
        }
    }

    // Resets all option buttons to default blue color
    private void resetButtonColors() {
        int defaultBlue = 0xFF1976D2;
        btnOptionA.setBackgroundColor(defaultBlue);
        btnOptionB.setBackgroundColor(defaultBlue);
        btnOptionC.setBackgroundColor(defaultBlue);
        btnOptionD.setBackgroundColor(defaultBlue);
    }

    // Enables or disables all four option buttons
    private void enableOptions(boolean enable) {
        btnOptionA.setEnabled(enable);
        btnOptionB.setEnabled(enable);
        btnOptionC.setEnabled(enable);
        btnOptionD.setEnabled(enable);
    }

    // Returns the letter label for an option index
    private String getOptionLetter(int index) {
        switch (index) {
            case 0: return "A. " + options[currentQuestion][0];
            case 1: return "B. " + options[currentQuestion][1];
            case 2: return "C. " + options[currentQuestion][2];
            case 3: return "D. " + options[currentQuestion][3];
            default: return "";
        }
    }

    // Shows a dialog with the final score and a button to go back to main menu
    private void showFinalScore() {
        String message = "You scored " + score + " out of " + questions.length + "!\n\n";

        // Give feedback based on score
        if (score == questions.length) {
            message += "🏆 Excellent! Perfect Score!";
        } else if (score >= questions.length * 0.7) {
            message += "👍 Great job! Keep it up!";
        } else if (score >= questions.length * 0.5) {
            message += "😊 Good effort! Practice more.";
        } else {
            message += "📚 Keep studying! You will do better!";
        }

        // Show AlertDialog with final score
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Completed!");
        builder.setMessage(message);
        builder.setCancelable(false);

        // Button to go back to Main Menu
        builder.setPositiveButton("Back to Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Go back to MainActivity and clear the activity stack
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // Button to retry the quiz
        builder.setNegativeButton("Retry Quiz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reset score and restart from question 1
                score = 0;
                currentQuestion = 0;
                loadQuestion(currentQuestion);
                tvScore.setText("Score: 0");
            }
        });

        builder.show();
    }
}
