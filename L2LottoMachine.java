package lotto2.L2lotto2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class L2LottoMachine implements L2Lotto {
    private final List<Integer> balls;
    private final List<Integer> userLotto;
    private final List<Integer> lotto;
    private int bonusNumber;

    public L2LottoMachine() {
        this.balls = new ArrayList<>();
        this.userLotto = new ArrayList<>();
        this.lotto = new ArrayList<>();
        this.bonusNumber = 0;

        initializeBalls();
        Collections.shuffle(balls);
    }

    private void initializeBalls() {
        for (int i = 0; i < L2Lotto.MAX_BALL_COUNT; i++) {
            balls.add(i + 1);
        }
    }

    public void inputUserLotto() {
        Scanner input = new Scanner(System.in);

        int count = 0;
        while (count < L2LottoMachine.RETURN_BALL_COUNT) {
            try {
                System.out.print((count + 1) + "번째 로또 선택: ");
                int ball = input.nextInt();
                validateUserInput(ball);
                count++;
            } catch (Exception e) {
                input.nextLine();
                System.out.println(e.getMessage() == null ? "숫자를 입력해 주십시오." : e.getMessage());
            }
        }

        input.close();
    }

    private void validateUserInput(int ball) throws Exception {
        if (ball < 1 || ball > L2Lotto.MAX_BALL_COUNT) {
            throw new IllegalArgumentException("1번에서 " + L2Lotto.MAX_BALL_COUNT +  "번 사이에서 선택해 주십시오.");
        }
        if (userLotto.contains(ball)) {
            throw new IllegalArgumentException(ball + "번은 이미 선택했습니다.");
        }
        userLotto.add(ball);
    }

    public void generateLottoNumbers() {
        int count = 0;
        while (count <= L2Lotto.RETURN_BALL_COUNT) {
            Collections.shuffle(balls);
            if (count < L2Lotto.RETURN_BALL_COUNT) {
                lotto.add(balls.get(0));
                balls.remove(0);
            } else {
                bonusNumber = balls.get(0);
            }
            count++;
        }
    }

    public void checkMatchingNumbers() {
        List<Integer> matchNumbers = new ArrayList<>();
        int matchCount = 0;
        boolean bonusChance = false;

        for (Integer myLotto : userLotto) {
            if (lotto.contains(myLotto)) {
                matchNumbers.add(myLotto);
            }
            if (!bonusChance) {
                if (myLotto == bonusNumber) {
                    bonusChance = true;
                }
            }
        }
        matchCount = matchNumbers.size();
        System.out.println(matchCount != 0 ? "일치하는 번호: " + matchNumbers : "일치하는 번호가 없군요...");
        System.out.println(matchCount == 5 && bonusChance ? " + [" + bonusNumber + "]" : "");
        System.out.println("로또 번호: " + lotto + " + [" + bonusNumber + "]");

        printResults(matchCount, bonusChance);
    }

    private void printResults(int matchCount, boolean bonusChance) {
        if (matchCount == L2Lotto.RETURN_BALL_COUNT) {
            System.out.println("1등!!!!!");
        } else if (matchCount == L2Lotto.RETURN_BALL_COUNT - 1 && bonusChance) {
            System.out.println("2등!!!!");
        }else if (matchCount == L2Lotto.RETURN_BALL_COUNT - 1) {
            System.out.println("3등!!!");
        }else if (matchCount == L2Lotto.RETURN_BALL_COUNT - 2) {
            System.out.println("4등!!");
        } else if (matchCount == L2Lotto.RETURN_BALL_COUNT - 3) {
            System.out.println("5등!");
        }else if (matchCount == L2Lotto.RETURN_BALL_COUNT - 4) {
            System.out.println("아깝군요..");
        } else {
            System.out.println("다음 기회에....");
        }
    }
}
