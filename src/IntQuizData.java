public final class IntQuizData implements QuizDataInterface<Integer> {
    private final int val;

    private IntQuizData(final int val) {
        this.val = val;
    }

    public static IntQuizData makeIntQuizData(final int val) {
        return new IntQuizData(val);
    }

    public Integer get() {
        return val;
    }

    public String valueOf() {
        return Integer.toString(val);
    }
}
