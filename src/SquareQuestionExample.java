
import java.util.Random;

public class SquareQuestionExample extends CreateQuestion {

    private Random rnd = new Random();

    public String createQuestionTitle() {
        return "Squaring Numbers";
    }
    public String createQuestionText() {
        int seedVal = (int)getSeedItem("number").get();
        String qText = "What is the square of " + seedVal + " ?";
        return qText;
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
        IntQuizData val = IntQuizData.makeIntQuizData(rnd.nextInt(15));
        addSeedItem("number", val);
    }

    public int createQuestionPoints() {
        return 5;
    }

    public Answer createCorrectAnswer() {
        int seedVal = (int)getSeedItem("number").get();
        return Answer.makeCorrectAnswerWithFeedback( IntQuizData.makeIntQuizData(seedVal * seedVal),
                "some correct feedback");
    }

    public Answer createIncorrectAnswer() {
        return Answer.makeIncorrectAnswerWithFeedback( IntQuizData.makeIntQuizData(rnd.nextInt(20)),
                "some incorrect feedback");
    }
}
