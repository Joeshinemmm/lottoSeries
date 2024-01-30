package lotto2.L2lotto2;

public class L2LottoMachineMain {
    public static void main(String[] args) {
        L2LottoMachine lottoGame = new L2LottoMachine();
        lottoGame.inputUserLotto();
        lottoGame.generateLottoNumbers();
        lottoGame.checkMatchingNumbers();
    }
}
