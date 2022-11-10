public class Main {
    public static void main(String[] args) {
        CreateQuestion squareExample = new SquareQuestionExample();
        squareExample.setQNum(1);
        squareExample.createMcqAnswerSet(5);

        System.out.println(squareExample.toText2Qti());

        CreateQuestion multExample = new MultQuestionExample();
        multExample.setQNum(squareExample.getQNum() + 1);
        multExample.createMcqAnswerSet(5);

        System.out.println(multExample.toText2Qti());

        CreateQuestion checkQuestion = new CheckSumValueQuestion();
        checkQuestion.setQNum(multExample.getQNum() + 1);
        checkQuestion.createMcqAnswerSet(5);

        System.out.println(checkQuestion.toText2Qti());

    }
}
