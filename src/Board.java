/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floodFill;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author FilPag 
 */
public class Board {

    public int boardSize = 20;
    static final int CellSize = 23;
    Color Purple = new Color(167, 0, 202);
    Color[][] color = new Color[24][24];
    int Margin = 120;
    public int maxMoves = 45;
    public int moves = 0;

    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(Margin - 10, Margin, 10, (CellSize * boardSize));
        g.fillRect(Margin - 10, Margin - 10, (CellSize * boardSize) + 10, 10);
        g.fillRect(Margin + (CellSize * boardSize), Margin - 10, 10,
                (CellSize * boardSize) + 10);
        g.fillRect(Margin - 10, Margin + (CellSize * boardSize),
                (CellSize * boardSize) + 20, 10);

        for (int rad = 0; rad < boardSize; rad++) {
            for (int kollumn = 0; kollumn < boardSize; kollumn++) {
                g.setColor(color[rad][kollumn]);
                g.fillRect(Margin + (23 * kollumn), Margin + (23 * rad), 23, 23);
            }
        }
    }

    public void floodFill(int X, int Y, Color replaceC, Color targetC) {
        if (targetC.equals(replaceC)) {
            return;
        }
        if (!this.color[Y][X].equals(targetC)) {
            return;
        }
        this.color[Y][X] = replaceC;
        //Väst
        if (X > 0) {
            floodFill(X - 1, Y, replaceC, targetC);
        }
        //Öst
        if (X < 19) {
            floodFill(X + 1, Y, replaceC, targetC);
        }
        //Syd
        if (Y < 19) {
            floodFill(X, Y + 1, replaceC, targetC);
        }
        //Norr
        if (Y > 0) {
            floodFill(X, Y - 1, replaceC, targetC);
        }

    }

    public void generateColors() {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < color[0].length; j++) {
                color[i][j] = randomColor((int) (Math.random() * 7) + 1);
            }
        }
    }

    public Color getColor(int mouseX, int mouseY) {
        mouseX = (mouseX - Margin) / CellSize;
        mouseY = (mouseY - Margin) / CellSize;
        if (this.color[mouseY][mouseX] == this.color[0][0]) {
            return this.color[0][0];
        } else if (mouseX > boardSize || mouseY > boardSize) {
            return this.color[0][0];
        } else {
            this.moves++;
            return this.color[mouseY][mouseX];
        }
    }

    public void setSize(int Size) {
        switch (Size) {
            case 0:
                this.boardSize = 12;
                Margin = (700 - (CellSize * boardSize)) / 2;
                maxMoves = 24;
                break;
            case 1:
                this.boardSize = 16;
                Margin = (700 - (CellSize * boardSize)) / 2;
                maxMoves = 33;
                break;
            case 2:
                this.boardSize = 20;
                Margin = (700 - (CellSize * boardSize)) / 2;
                maxMoves = 40;
                break;
            case 3:
                this.boardSize = 24;
                Margin = (700 - (CellSize * boardSize)) / 2;
                maxMoves = 48;
                break;
            default:
                this.boardSize = 20;
                Margin = (700 - (CellSize * boardSize)) / 2;

        }
    }

    private Color randomColor(int rand) {
        switch (rand) {
            case 1:
                return new Color(0x3F, 0x51, 0xB5);
            case 2:
                return new Color(0x76, 0xFF, 0x03);
            case 3:
                return new Color(0xF4, 0x43, 0x36);
            case 4:
                return new Color(0xFF, 0xC1, 0x07);
            case 5:
                return new Color(0xF0, 0x62, 0x92);
            case 6:
                return new Color(0x00, 0xBC, 0xD4);
            case 7:
                return new Color(0x9C, 0x27, 0xB0);
            default:
                return Color.WHITE;
        }
    }

    public boolean win() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (color[0][0] == color[i][j] && moves < maxMoves) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
