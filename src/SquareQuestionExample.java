
import java.util.Random;

public class SquareQuestionExample extends CreateQuestion {

    private final Random rnd = new Random();

    public String createQuestionTitle() {
        return "Squaring Numbers";
    }
    public String createQuestionText() {
        QuizData<Integer> item = getQuizDataItem("number");
        int seedVal = item.get();
        return "What is the square of " + seedVal + " ?";
    }

    public String createGeneralFeedback() {
        return "Some generic feedback";
    }

    public String createCorrectFeedback() {
        return "Some feedback for the correct answer";
    }

    public String createIncorrectFeedback() {
        return "Some general feedback for incorrect answers";
    }

    public void createDataSeeds() {
        QuizData<Integer> val1 = new QuizData<Integer>(rnd.nextInt(15));
        addQuizDataItem("number", val1);
    }

    public int createQuestionPoints() {
        return 3;
    }

    public Answer createCorrectAnswer() {
        QuizData<Integer> item = getQuizDataItem("number");
        int seedVal = item.get();
        //int seedVal = (int)getSeedItem("number").get();
        return Answer.makeCorrectAnswerWithFeedback(new QuizData<Integer>(seedVal * seedVal),
                "some correct feedback");
    }

    public Answer createIncorrectAnswer() {
        return Answer.makeIncorrectAnswerWithFeedback(new QuizData<Integer>(rnd.nextInt(20)),
                "some incorrect feedback");
    }
}
