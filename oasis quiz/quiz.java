public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private Button optionA, optionB, optionC, optionD;
    private List<Question> questionList;
    private Question currentQuestion;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.question_text_view);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);

        loadQuestions();
        setQuestionView();
    }

    private void loadQuestions() {
        // This method should load questions from a resource file or database
        questionList = new ArrayList<>();
        questionList.add(new Question("Question 1", "A", "B", "C", "D", "A"));
        // Add more questions here
    }

    private void setQuestionView() {
        currentQuestion = questionList.get(0); // Get the first question
        questionTextView.setText(currentQuestion.getQuestion());
        optionA.setText(currentQuestion.getOptionA());
        optionB.setText(currentQuestion.getOptionB());
        optionC.setText(currentQuestion.getOptionC());
        optionD.setText(currentQuestion.getOptionD());
    }

    public void onAnswerSelection(View view) {
        Button clickedButton = (Button) view;
        String answer = clickedButton.getText().toString();
        if(answer.equals(currentQuestion.getCorrectAnswer())) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        // Load the next question
    }

    // Question class
    class Question {
        private String question, optionA, optionB, optionC, optionD, correctAnswer;

        public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        // Getters here
    }
}
