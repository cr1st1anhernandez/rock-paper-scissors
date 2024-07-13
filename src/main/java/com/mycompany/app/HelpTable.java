package com.mycompany.app;
import de.vandermeer.asciitable.AsciiTable;

public class HelpTable {
    private String[] moves;

    public HelpTable(String[] moves) {
        this.moves = moves;
    }

    public void displayTable() {
        AsciiTable table = new AsciiTable();
        table.addRule();
        
        String[] headerRow = new String[moves.length + 1];
        headerRow[0] = "PC\\User";
        System.arraycopy(moves, 0, headerRow, 1, moves.length);
        table.addRow(headerRow);
        table.addRule();

        for (int i = 0; i < moves.length; i++) {
            String[] row = new String[moves.length + 1];
            row[0] = moves[i];
            for (int j = 0; j < moves.length; j++) {
                if (i == j) {
                    row[j + 1] = "Draw";
                } else if ((i > j && i <= j + moves.length / 2) || (i < j && i + moves.length <= j + moves.length / 2)) {
                    row[j + 1] = "Win";
                } else {
                    row[j + 1] = "Lose";
                }
            }
            table.addRow(row);
            table.addRule();
        }

        String rend = table.render();
        System.out.println(rend);
    }
}
