public final class StringQuizData implements QuizDataInterface<String> {
    private final String val;

    private StringQuizData(final String val) {
        this.val = val;
    }

    public static StringQuizData stringQuizData(final String val) {
        return new StringQuizData(val);
    }
    public String get() {
        return val;
    }

    public String valueOf() {
        return val;
    }
}
