package happyJavaS4.lotto;

public class LottoMachineMain {

    public static void main(String[] args) {
        Ball[] balls = new Ball[LottoMachine.MAX_BALL_COUNT];
        for (int i = 0; i < LottoMachine.MAX_BALL_COUNT; i++) {
            balls[i] = new Ball(i + 1);
        }

        LottoMachine lottoMachine = new LottoMachineImpl();
        lottoMachine.setBalls(balls);
        lottoMachine.mix();
        Ball[] result = lottoMachine.getBalls();

        for (int i = 0; i < result.length; i++) {
            if (i < result.length - 1) {
                System.out.print(result[i].getNumber() + " ");
            } else {
                System.out.println("+ " + result[result.length - 1].getNumber());
            }
        }

    }
}
