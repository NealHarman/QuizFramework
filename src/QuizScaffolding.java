
import java.util.Random;

public class QuizScaffolding extends CreateQuestion {

    private Random rnd = new Random();

    public String createQuestionTitle() {
        return "Squaring Numbers";
    }
    public String createQuestionText() {
        int seedVal = (int)getSeedItem("number").get();
        String qText = "What is the square of " + seedVal + " ?";
        return qText;
    }

    public void createDataSeeds() {
        IntQuizData val = IntQuizData.makeIntQuizData(rnd.nextInt(15));
        addSeedItem("number", val);
    }

    public int createQuestionPoints() {
        return 1;
    }

    public Answer createCorrectAnswer() {
        int seedVal = (int)getSeedItem("number").get();
        return Answer.MakeCorrectAnswer( IntQuizData.makeIntQuizData(seedVal * seedVal),
                "some correct feedback");
    }

    public Answer createIncorrectAnswer() {
        return Answer.MakeIncorrectAnswer( IntQuizData.makeIntQuizData(rnd.nextInt(20)),
                "some incorrect feedback");
    }
}
