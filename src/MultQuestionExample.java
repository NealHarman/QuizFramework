
import java.util.Random;

public class MultQuestionExample extends CreateQuestion {

    private final Random rnd = new Random();

    public String createQuestionTitle() {
        return "Multiplying Numbers";
    }
    public String createQuestionText() {
        QuizData<Integer> item1 = getQuizDataItem("number1");
        int seedVal1 = item1.get();
        QuizData<Integer> item2 = getQuizDataItem("number2");
        int seedVal2 = item2.get();
        return "What is " + seedVal1 + " * " + seedVal2 + " ?";
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
        QuizData<Integer> val = new QuizData<Integer>(rnd.nextInt(15));
        addQuizDataItem("number1", val);
        QuizData<Integer> val2 = new QuizData<Integer>(rnd.nextInt(15));
        addQuizDataItem("number2", val2);
    }

    public int createQuestionPoints() {
        return 5;
    }

    public Answer createCorrectAnswer() {
        QuizData<Integer> item1 = getQuizDataItem("number1");
        int seedVal1 = item1.get();
        QuizData<Integer> item2 = getQuizDataItem("number2");
        int seedVal2 = item2.get();
        //int seedVal = (int)getSeedItem("number").get();
        return Answer.makeCorrectAnswerWithFeedback(new QuizData<Integer>(seedVal1 * seedVal2),
                "some correct feedback");
    }

    public Answer createIncorrectAnswer() {
        return Answer.makeIncorrectAnswerWithFeedback(new QuizData<Integer>(rnd.nextInt(20)),
                "some incorrect feedback");
    }
}
