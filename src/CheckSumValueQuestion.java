
import java.util.Random;

public class CheckSumValueQuestion extends CreateQuestion {

    private final Random rnd = new Random();

    public String createQuestionTitle() {
        return "Basic Checksum";
    }
    public String createQuestionText() {
        QuizData<String> item = getQuizDataItem("checkString");
        String seedVal = item.get();

        return "What is the result of running the simple checksum algorithm on the string " + seedVal + " ?";
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
        QuizData<String> val = new QuizData<String>(QuizUtils.genRandomString(65, 20, 'a', 'z'));
        addQuizDataItem("checkString", val);
    }

    public int createQuestionPoints() {
        return 8;
    }

    public Answer createCorrectAnswer() {
        QuizData<String> item = getQuizDataItem("checkString");
        String seedVal = item.get();

        return Answer.makeCorrectAnswerWithFeedback(new QuizData<Long>(genCheck(seedVal)),
                "some correct feedback");
    }

    public Answer createIncorrectAnswer() {
        return Answer.makeIncorrectAnswerWithFeedback(new QuizData<Long>(rnd.nextLong()),
                "some incorrect feedback");
    }

    private long genCheck(String str) {
        long k = 7;//7
        for (int i = 0; i < str.length(); i++) {
            k *= 23;//23
            k += str.charAt(i);
            k *= 13;//13
            k %= 1000000009;
        }
        return k;
    }
}
