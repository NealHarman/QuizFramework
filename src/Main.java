public class Main {
    public static void main(String[] args) {
        CreateQuestion scaff = new QuizScaffolding();
        scaff.setQNum(1);
        scaff.createMcqAnswerSet(5);
        System.out.println(scaff);

        System.out.println();

        System.out.println(scaff.toText2Qti());
    }
}
