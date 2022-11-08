import java.util.HashMap;

public abstract class CreateQuestion {

    private Question question = new Question();
    private HashMap<String, QuizDataInterface> seedList = new HashMap<>();

    private int qNum;

    public abstract String createQuestionTitle();
    public abstract String createQuestionText();

    public abstract void createDataSeeds();

    public abstract Answer createCorrectAnswer();

    public abstract Answer createIncorrectAnswer();

    public int createQuestionPoints() {
        return 1;
    }

    public String createGeneralFeedback() {
        return null;
    }

    public String createCorrectFeedback() {
        return null;
    }

    public String createIncorrectFeedback() {
        return null;
    }

    public final boolean createMcqAnswerSet(final int numAnswers) {
        createDataSeeds();
        question.addQuestionTitle(createQuestionTitle());
        question.addQuestionText(createQuestionText());
        question.addQuestionPoints(createQuestionPoints());
        question.addGeneralFeedback(createGeneralFeedback());
        question.addCorrectFeedback(createCorrectFeedback());
        question.addIncorrectAnswerFeedback(createIncorrectFeedback());
        if (!question.addAnswer(createCorrectAnswer())) {
            return false;
        }

        int incorrectCount = 0;
        while (incorrectCount < numAnswers) {
            if (question.addAnswer(createIncorrectAnswer())) {
                incorrectCount++;
            }
        }
        return true;
    }

    public final void addSeedItem(final String key, final QuizDataInterface val) {
        seedList.putIfAbsent(key, val);
    }

    public final QuizDataInterface getSeedItem(final String key) {
        return seedList.getOrDefault(key, null);
    }

    public final int getQNum() {
        return qNum;
    }

    public final void setQNum(final int num) {
        qNum = num;
    }

    @Override
    public final String toString() {
        java.util.List<java.util.Map.Entry<String, Answer>> list = randomize();
        StringBuilder builder = new StringBuilder(question.getQuestionTitle());
        builder.append("\n");
        builder.append(question.getQuestionText());
        builder.append("\n");
        builder.append("Points: " + question.getQuestionPoints() + "\n");
        for (java.util.Map.Entry<String, Answer> ans: list) {
            builder.append(ans.getValue().getAnswer().valueOf());
            if (ans.getValue().isCorrect()) {
                builder.append(" *");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public final String toText2Qti() {
        StringBuilder builder = new StringBuilder("Title: " + question.getQuestionTitle() + "\n");
        builder.append("Points: " + question.getQuestionPoints() + "\n");
        builder.append(qNum + ". " + question.getQuestionText() + "\n");
        if (question.getGeneralFeedback() != null) {
            builder.append("... " + question.getGeneralFeedback() + "\n");
        }
        if (question.getCorrectAnswerFeedback() != null) {
            builder.append("+ " + question.getCorrectAnswerFeedback() + "\n");
        }
        if (question.getIncorrectAnswerFeedback() != null) {
            builder.append("- " + question.getIncorrectAnswerFeedback() + "\n");
        }
        java.util.List<java.util.Map.Entry<String, Answer>> list = randomize();
        char qItem = 'a';
        for (java.util.Map.Entry<String, Answer> ans: list) {
            if (ans.getValue().isCorrect()) {
                builder.append("*");
            }
            builder.append(qItem + ") " + ans.getValue().getAnswer().valueOf());
            builder.append("\n");
            if (ans.getValue().getFeedback() != null) {
                builder.append("... " + ans.getValue().getFeedback() + "\n");
            }
            qItem++;
        }
        return builder.toString();
    }

    private java.util.List<java.util.Map.Entry<String, Answer>> randomize() {
        java.util.List<java.util.Map.Entry<String, Answer>> list =
                new java.util.ArrayList<>(question.getAnswerList().entrySet());
        java.util.Collections.shuffle(list);
        return list;
    }
}
