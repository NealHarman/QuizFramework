public final class DoubleQuizData implements QuizDataInterface<Double> {
    private final double val;

    private DoubleQuizData(final double val) {
        this.val = val;
    }

    public static DoubleQuizData makeDoubleQuizData(final double val) {
        return new DoubleQuizData(val);
    }

    public Double get() {
        return val;
    }

    public String valueOf() {
        return Double.toString(val);
    }
}
