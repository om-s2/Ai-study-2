// java/com/omkar/studymate/ContentActivity.java
package com.omkar.studymate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentActivity extends Activity {

    // Declare UI elements
    TextView tvContentTitle, tvNotes, tvQa;
    Button btnDownloadPdf, btnTakeQuiz;

    // Store received intent data
    String selectedClass, selectedSubject, selectedChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        // Get data from ChapterActivity
        selectedClass   = getIntent().getStringExtra("class");
        selectedSubject = getIntent().getStringExtra("subject");
        selectedChapter = getIntent().getStringExtra("chapter");

        // Find UI elements by ID
        tvContentTitle  = (TextView) findViewById(R.id.tv_content_title);
        tvNotes         = (TextView) findViewById(R.id.tv_notes);
        tvQa            = (TextView) findViewById(R.id.tv_qa);
        btnDownloadPdf  = (Button) findViewById(R.id.btn_download_pdf);
        btnTakeQuiz     = (Button) findViewById(R.id.btn_take_quiz);

        // Display chapter title
        tvContentTitle.setText(selectedChapter);

        // Load notes and Q&A based on chapter
        tvNotes.setText(getNotesForChapter(selectedChapter));
        tvQa.setText(getQAForChapter(selectedChapter));

        // Download PDF button: open sample PDF URL in browser
        btnDownloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open sample PDF URL using ACTION_VIEW intent
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setData(Uri.parse("https://ncert.nic.in/textbook.php"));
                startActivity(pdfIntent);
            }
        });

        // Take Quiz button: open QuizActivity with subject and chapter info
        btnTakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this, QuizActivity.class);
                intent.putExtra("class",   selectedClass);
                intent.putExtra("subject", selectedSubject);
                intent.putExtra("chapter", selectedChapter);
                startActivity(intent);
            }
        });
    }

    // Returns notes text for the given chapter
    private String getNotesForChapter(String chapter) {

        if (chapter.contains("Crop Production")) {
            return "Agriculture is the primary occupation in India. Crops are plants grown by farmers.\n\n"
                 + "• Kharif Crops: Grown in rainy season (June-September). Examples: Paddy, Maize, Soybean.\n"
                 + "• Rabi Crops: Grown in winter season (November-March). Examples: Wheat, Gram, Mustard.\n\n"
                 + "Soil preparation involves ploughing, levelling, and manuring.\n"
                 + "Irrigation provides water to crops using canals, wells, and sprinklers.\n"
                 + "Harvesting is the cutting of mature crops. Threshing separates grain from stalks.\n"
                 + "Storage of grains is done in silos and granaries to protect from pests.";

        } else if (chapter.contains("Microorganisms")) {
            return "Microorganisms are tiny living beings that cannot be seen with naked eyes.\n\n"
                 + "Types of Microorganisms:\n"
                 + "• Bacteria – Single-celled organisms. Example: Lactobacillus (in curd).\n"
                 + "• Fungi – Multi-celled. Example: Yeast, Mushroom.\n"
                 + "• Protozoa – Example: Amoeba, Paramecium.\n"
                 + "• Algae – Example: Spirogyra, Chlamydomonas.\n"
                 + "• Viruses – Not cells; reproduce inside host cells.\n\n"
                 + "Useful Microorganisms: Making curd, bread, wine; fixing nitrogen in soil.\n"
                 + "Harmful Microorganisms: Cause diseases like TB, Cholera, Malaria, COVID-19.\n"
                 + "Antibiotics like Penicillin are obtained from fungi to fight bacterial infections.";

        } else if (chapter.contains("Rational Numbers")) {
            return "Rational numbers are numbers that can be expressed in the form p/q, where p and q are integers and q ≠ 0.\n\n"
                 + "Properties of Rational Numbers:\n"
                 + "• Closure: Rational numbers are closed under addition, subtraction, multiplication.\n"
                 + "• Commutativity: a + b = b + a and a × b = b × a.\n"
                 + "• Associativity: (a+b)+c = a+(b+c).\n"
                 + "• Distributivity: a(b+c) = ab + ac.\n\n"
                 + "Between any two rational numbers there exist infinitely many rational numbers.\n"
                 + "The number line can represent all rational numbers.\n"
                 + "0 is neither positive nor negative.";

        } else if (chapter.contains("Linear Equations")) {
            return "A linear equation in one variable has only one variable with degree 1.\n\n"
                 + "Standard form: ax + b = c, where a ≠ 0.\n\n"
                 + "Steps to Solve:\n"
                 + "1. Transpose (move) terms with variable to one side.\n"
                 + "2. Move constants to the other side.\n"
                 + "3. Divide both sides by the coefficient of the variable.\n\n"
                 + "Example: 2x + 5 = 15 → 2x = 10 → x = 5\n\n"
                 + "Applications: Age problems, number problems, geometry problems.";

        } else if (chapter.contains("How, When and Where")) {
            return "History is the study of past events. Historians use dates, documents, and accounts to write history.\n\n"
                 + "Sources of History:\n"
                 + "• Official records, diaries, newspapers, and photographs.\n\n"
                 + "Colonial rule in India: British East India Company came as traders in 1600 AD.\n"
                 + "After 1857 revolt, British Crown took direct control of India.\n\n"
                 + "Periods of Indian History:\n"
                 + "• Ancient Period: Before 700 AD.\n"
                 + "• Medieval Period: 700-1750 AD.\n"
                 + "• Modern Period: After 1750 AD.\n\n"
                 + "James Mill (Scottish historian) divided Indian history into Hindu, Muslim, and British periods.";

        } else if (chapter.contains("Resources")) {
            return "A resource is anything that can be used to satisfy our needs.\n\n"
                 + "Types of Resources:\n"
                 + "• Natural Resources: Obtained from nature. E.g., Air, Water, Minerals.\n"
                 + "• Human-Made Resources: Created by humans. E.g., Buildings, Machines.\n"
                 + "• Human Resources: People's skills and knowledge.\n\n"
                 + "Classification by Availability:\n"
                 + "• Renewable: Can be replenished. E.g., Solar energy, Wind energy, Water.\n"
                 + "• Non-Renewable: Limited stock. E.g., Coal, Petroleum, Natural gas.\n\n"
                 + "Conservation of resources is very important to protect them for future generations.\n"
                 + "Sustainable development means using resources wisely without harming future needs.";

        } else if (chapter.contains("Christmas")) {
            return "Summary: Jim's wife finds an old letter written by a British soldier named Connie to his wife.\n"
                 + "The letter was written in 1914 during World War I from the trenches.\n"
                 + "On Christmas Day, British and German soldiers stopped fighting and played football together.\n\n"
                 + "Themes: Peace, humanity, love, and the pointlessness of war.\n\n"
                 + "Key Characters:\n"
                 + "• Jim - An old man; husband of Connie.\n"
                 + "• Connie - Jim's wife; she passes away during the story.\n"
                 + "• Narrator - Visits Jim in hospital during Christmas.\n\n"
                 + "The story highlights that even enemies can show kindness and humanity during war.";

        } else {
            // Default notes for any other chapter
            return "This chapter covers important concepts related to " + selectedSubject + ".\n\n"
                 + "Key Topics:\n"
                 + "• Introduction to the chapter and its importance.\n"
                 + "• Core definitions and fundamental concepts.\n"
                 + "• Examples from everyday life and the Indian context.\n"
                 + "• Important processes and their explanations.\n"
                 + "• Diagrams and charts where applicable.\n\n"
                 + "Summary:\n"
                 + "This chapter is very important for exams. Understand all the concepts carefully "
                 + "and practice the exercises given at the end of the chapter in your NCERT textbook.";
        }
    }

    // Returns Q&A text for the given chapter
    private String getQAForChapter(String chapter) {

        if (chapter.contains("Crop Production")) {
            return "Q1. What are Kharif crops? Give two examples.\n"
                 + "Ans: Kharif crops are grown during the rainy season (June to September). "
                 + "Examples: Paddy and Maize.\n\n"
                 + "Q2. What is irrigation? Name any two methods.\n"
                 + "Ans: Irrigation is the supply of water to crops at regular intervals. "
                 + "Methods: Canal irrigation and Sprinkler irrigation.\n\n"
                 + "Q3. What is threshing?\n"
                 + "Ans: Threshing is the process of separating the grain from the stalks after harvesting.\n\n"
                 + "Q4. Why is storage of food grains important?\n"
                 + "Ans: Proper storage protects grain from pests, moisture, and spoilage, ensuring food availability throughout the year.";

        } else if (chapter.contains("Rational Numbers")) {
            return "Q1. What is a rational number?\n"
                 + "Ans: A number that can be written in the form p/q where p and q are integers and q ≠ 0.\n\n"
                 + "Q2. Is 0 a rational number?\n"
                 + "Ans: Yes, 0 is a rational number because it can be written as 0/1.\n\n"
                 + "Q3. Find a rational number between 1/3 and 1/2.\n"
                 + "Ans: Add both and divide by 2: (1/3 + 1/2)/2 = (5/6)/2 = 5/12. So 5/12 is between 1/3 and 1/2.\n\n"
                 + "Q4. What is the additive identity for rational numbers?\n"
                 + "Ans: 0 is the additive identity, because a + 0 = a for any rational number a.";

        } else if (chapter.contains("How, When and Where")) {
            return "Q1. Why is it important to know dates in history?\n"
                 + "Ans: Dates help us place events in a sequence and understand how history unfolded over time.\n\n"
                 + "Q2. Who was James Mill? What did he write?\n"
                 + "Ans: James Mill was a Scottish historian. He wrote 'A History of British India' and divided Indian history into Hindu, Muslim, and British periods.\n\n"
                 + "Q3. What are the main sources used by historians?\n"
                 + "Ans: Historians use official records, diaries, newspapers, photographs, and archaeological evidence.\n\n"
                 + "Q4. When did the British Crown take control of India?\n"
                 + "Ans: After the Revolt of 1857, the British Crown took direct control of India from the East India Company in 1858.";

        } else if (chapter.contains("Resources")) {
            return "Q1. What is a resource?\n"
                 + "Ans: Anything that can be used to satisfy a human need is called a resource.\n\n"
                 + "Q2. Differentiate between renewable and non-renewable resources.\n"
                 + "Ans: Renewable resources can be replenished naturally (e.g., solar energy, wind). "
                 + "Non-renewable resources take millions of years to form and can be exhausted (e.g., coal, petroleum).\n\n"
                 + "Q3. What is sustainable development?\n"
                 + "Ans: Sustainable development means meeting current needs without compromising the ability of future generations to meet their own needs.\n\n"
                 + "Q4. Name two human-made resources.\n"
                 + "Ans: Buildings and machines are examples of human-made resources.";

        } else {
            // Default Q&A for other chapters
            return "Q1. What is the main focus of this chapter?\n"
                 + "Ans: This chapter focuses on core concepts of " + selectedSubject
                 + " as prescribed in the NCERT curriculum for " + selectedClass + ".\n\n"
                 + "Q2. Why is this chapter important for board exams?\n"
                 + "Ans: This chapter frequently appears in board examinations and contains many concepts "
                 + "that are foundational for higher studies.\n\n"
                 + "Q3. How should a student prepare this chapter?\n"
                 + "Ans: Read the NCERT textbook carefully, solve all exercises, make short notes, "
                 + "and revise the important definitions and formulas regularly.\n\n"
                 + "Q4. What are key points to remember from this chapter?\n"
                 + "Ans: Focus on definitions, formulas, diagrams, and examples. Practice previous year questions "
                 + "to understand the exam pattern.";
        }
    }
}
