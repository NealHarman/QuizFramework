import java.util.*;

public abstract class CreateQuestion {

    private Question question = new Question();
    protected HashMap<String, QuizDataInterface> seedList = new HashMap<>();

    private int qNum;

    public abstract String createQuestionTitle();
    public abstract String createQuestionText();

    public abstract int createQuestionPoints();

    public abstract void createDataSeeds();

    public abstract Answer createCorrectAnswer();

    public abstract Answer createIncorrectAnswer();

    public boolean createMcqAnswerSet(final int numAnswers) {
        createDataSeeds();
        question.addQuestionTitle(createQuestionTitle());
        question.addQuestionText(createQuestionText());
        question.addQuestionPoints(createQuestionPoints());
        if (!question.addAnswer(createCorrectAnswer())) {
            return false;
        }

        int incorrectCount = 0;
        while (incorrectCount < numAnswers){
            if (question.addAnswer(createIncorrectAnswer())) {
                incorrectCount ++;
            }
        }
        return true;
    }

    public void addSeedItem(final String key, final QuizDataInterface val) {
        seedList.putIfAbsent(key, val);
    }

    public QuizDataInterface getSeedItem(final String key) {
        return seedList.getOrDefault(key, null);
    }

    public int getQNum() {
        return qNum;
    }

    public void setQNum(final int num) {
        qNum = num;
    }

    @Override
    public String toString() {
        List<Map.Entry<String, Answer>> list = randomize();
        StringBuilder builder = new StringBuilder(question.getQuestionTitle());
        builder.append("\n");
        builder.append(question.getQuestionText());
        builder.append("\n");
        builder.append("Points: " + question.getQuestionPoints() + "\n");
        for(Map.Entry<String, Answer> ans: list) {
            builder.append(ans.getValue().getAnswer().valueOf());
            if(ans.getValue().isCorrect()) {
                builder.append(" *");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String toText2Qti() {
        StringBuilder builder = new StringBuilder("Title: " + question.getQuestionTitle() + "\n");
        builder.append("Points: " + question.getQuestionPoints() + "\n");
        builder.append(qNum + ". " + question.getQuestionText() + "\n");
        List<Map.Entry<String, Answer>> list = randomize();
        char qItem = 'a';
        for(Map.Entry<String, Answer> ans: list) {
            if(ans.getValue().isCorrect()) {
                builder.append("*");
            }
            builder.append(qItem + ") " + ans.getValue().getAnswer().valueOf());
            builder.append("\n");
            qItem++;
        }
        return builder.toString();
    }

    private List<Map.Entry<String, Answer>> randomize() {
        List<Map.Entry<String,Answer>> list =
                new ArrayList<>(question.getAnswerList().entrySet());
        Collections.shuffle(list);
        return list;
    }
}
