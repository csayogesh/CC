package ibit;

import java.util.ArrayList;

public class Sudoku {
    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        if (isValidSudoku(a) == 1) ;
        else return;
        int i = 0, j = 0;
        one:
        for (i = 0; i < 9; i++)
            for (j = 0; j < 9; j++)
                if (a.get(i).get(j) == '.')
                    break one;
        fill(a, i, j);
    }

    public boolean fill(ArrayList<ArrayList<Character>> board, int i, int j) {
        if (i == 9 || j == 9) return true;
        for (char ch = '1'; ch <= '9'; ch++) {
            if (available(ch, i, j)) {
                put(ch, i, j);
                board.get(i).set(j, ch);
                int a = i, b = j;
                boolean flg = false;
                one:
                for (a = 0; a < 9; a++)
                    for (b = 0; b < 9; b++)
                        if (available(board.get(a).get(b), a, b)) {
                            break one;
                        }
                if (fill(board, a, b)) return true;
                remove(ch, i, j);
                board.get(i).set(j, '.');
            }
        }
        return false;
    }

    boolean rows[][] = new boolean[9][9];
    boolean cols[][] = new boolean[9][9];
    boolean box[][] = new boolean[9][9];

    public int isValidSudoku(final ArrayList<ArrayList<Character>> a) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                if (!available(a.get(i).get(j), i, j)) return 0;
                else if (a.get(i).get(j) != '.') put(a.get(i).get(j), i, j);
        }
        return 1;
    }

    public boolean available(char ch, int i, int j) {
        if (ch == '.') return true;
        if ((ch < '1' || ch > '9')) return false;
        return !(rows[i][ch - '1'] || cols[j][ch - '1'] || box[3 * (i / 3) + j / 3][ch - '1']);
    }

    public void put(char ch, int i, int j) {
        rows[i][ch - '1'] = true;
        cols[j][ch - '1'] = true;
        box[3 * (i / 3) + j / 3][ch - '1'] = true;
    }

    public void remove(char ch, int i, int j) {
        rows[i][ch - '1'] = false;
        cols[j][ch - '1'] = false;
        box[3 * (i / 3) + j / 3][ch - '1'] = false;
    }
}
