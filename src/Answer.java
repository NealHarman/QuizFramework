public class Answer {
    private final QuizDataInterface answer;
    private final boolean isCorrect;

    private final String feedback;

    public QuizDataInterface getAnswer() {
        return answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public static Answer makeCorrectAnswer(final QuizDataInterface answer) {
        return new Answer(answer, null, true);
    }

    public static Answer makeIncorrectAnswer(final QuizDataInterface answer) {
        return new Answer(answer, null, false);
    }

    public static Answer makeCorrectAnswerWithFeedback(final QuizDataInterface answer,
                                                       final String feedback) {
        return new Answer(answer, feedback, true);
    }

    public static Answer makeIncorrectAnswerWithFeedback(final QuizDataInterface answer,
                                                         final String feedback) {
        return new Answer(answer, feedback, false);
    }

    public Answer(final QuizDataInterface answer, final String feedback,
                  final boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return answer.valueOf();
    }
}
