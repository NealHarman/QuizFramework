import java.util.HashMap;

public class Question {
    private String questionTitle;
    private String questionText;

    private int points;
    private final HashMap<String,Answer> answerList;

    private final SeedList questionData;

    public Question() {
        answerList = new HashMap<>();
        questionData = new SeedList();
    }

    public void addQuestionTitle(final String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public void addQuestionText(final String questionText) {
        this.questionText = questionText;
    }

    public void addQuestionPoints(final int points) {
        this.points = points;
    }

    public boolean addAnswer(final Answer answer) {
        if (getAnswerList().containsKey(answer.getAnswer())) {
            return false;
        } else {
            getAnswerList().put(answer.getAnswer().valueOf(), answer);
            return true;
        }
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getQuestionPoints() {
        return points;
    }

    public HashMap<String, Answer> getAnswerList() {
        return answerList;
    }

    public QuizDataInterface getQuestionDataItem(final String key) {
            return questionData.getSeedItem(key);
    }

    public boolean setQuestionDataItem(final String key, final QuizDataInterface value) {
        if (questionData.containsSeedKey(key)) {
            return false;
        } else {
            questionData.addSeedItem(key, value);
            return true;
        }
    }
}
