public class Answer {
    private final QuizData answer;
    private final boolean isCorrect;

    private final String feedback;

    public QuizData getAnswer() {
        return answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public static Answer makeCorrectAnswer(final QuizData answer) {
        return new Answer(answer, null, true);
    }

    public static Answer makeIncorrectAnswer(final QuizData answer) {
        return new Answer(answer, null, false);
    }

    public static Answer makeCorrectAnswerWithFeedback(final QuizData answer,
                                                       final String feedback) {
        return new Answer(answer, feedback, true);
    }

    public static Answer makeIncorrectAnswerWithFeedback(final QuizData answer,
                                                         final String feedback) {
        return new Answer(answer, feedback, false);
    }

    public Answer(final QuizData answer, final String feedback,
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
