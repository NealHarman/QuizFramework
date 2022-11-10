public class QuizData<T> {
    private T value;

    public QuizData(T value) {
        this.value = value;
    }
    <T> T get() {
        return (T)value;
    }
    String valueOf() {
        return value.toString();
    }
}
