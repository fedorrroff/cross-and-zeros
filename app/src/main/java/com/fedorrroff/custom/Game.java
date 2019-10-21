package com.fedorrroff.custom;

import android.widget.Button;

import java.io.Serializable;

public class Game implements Serializable {

    private OnWinnerListener l;

    public static final int FIELD_SIZE = 3;
    private boolean isCross = true;

    private Case[][] caseMatrix = new Case[FIELD_SIZE][FIELD_SIZE];

    public void createField(Button[] buttons) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                caseMatrix[i][j] = Case.UNDEFINED;
                buttons[j*3 + i].setText(Case.UNDEFINED.toString());
            }
        }
    }

    public void attachButtons(Button[] buttons) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                int finalI = i;
                int finalJ = j;
                final boolean[] isClicked = {false};
                buttons[j*3 + i].setOnClickListener((v) ->{
                    if (!isClicked[0]) {
                        if (isCross) {
                            buttons[finalJ * 3 + finalI].setText(Case.CROSS.toString());
                            caseMatrix[finalI][finalJ] = Case.CROSS;
                        } else {
                            buttons[finalJ * 3 + finalI].setText(Case.ZERO.toString());
                            caseMatrix[finalI][finalJ] = Case.ZERO;
                        }
                        isCross = !isCross;
                        isClicked[0] = true;

                        if(checkLines(finalI, finalJ) || checkDiagonals(finalI, finalJ)){
                            l.onGameWon(caseMatrix[finalI][finalJ].toString());
                        }
                    }
                });
            }
        }
    }

    private boolean checkLines(int iPos, int jPos) {
        if (!caseMatrix[iPos][jPos].equals(Case.UNDEFINED)) {
            //Check vertical line
            //TODO loop
            if (caseMatrix[iPos][0].equals(caseMatrix[iPos][1]) && caseMatrix[iPos][0].equals(caseMatrix[iPos][2])) {
                return true;
            }
            //Check vertical line
            return caseMatrix[0][jPos].equals(caseMatrix[1][jPos]) && caseMatrix[0][jPos].equals(caseMatrix[2][jPos]);
        }
        return false;
    }

    private boolean checkDiagonals(int iPos, int jPos) {
        if (!caseMatrix[iPos][jPos].equals(Case.UNDEFINED)){
            if (iPos == jPos) {
                if (caseMatrix[0][0].equals(caseMatrix[1][1]) && caseMatrix[0][0].equals(caseMatrix[2][2])) {
                    return true;
                }
            } else if(jPos == FIELD_SIZE - iPos - 1) {
                if (caseMatrix[2][0].equals(caseMatrix[1][1]) && caseMatrix[2][0].equals(caseMatrix[0][2])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setOnWinnerListener(OnWinnerListener l) {
        this.l = l;
    }

    public interface OnWinnerListener{
        public void onGameWon(String winner);
    }

    public enum Case{
        CROSS, ZERO, UNDEFINED
    }
}
