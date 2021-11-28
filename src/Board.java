package floodFill;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Filip Pagliaro
 */
public class Board {

    private static final int CellSize = 23;

    public int boardSize = 20;
    public int maxMoves = 45;
    public int moves = 0;
    public Color[][] colors = new Color[24][24];

    private int Margin = 120;

    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(this.Margin - 10, this.Margin, 10, (this.CellSize * this.boardSize));
        g.fillRect(this.Margin - 10, this.Margin - 10, (this.CellSize * this.boardSize) + 10, 10);
        g.fillRect(this.Margin + (this.CellSize * this.boardSize), this.Margin - 10, 10,
                (this.CellSize * this.boardSize) + 10);
        g.fillRect(this.Margin - 10, this.Margin + (this.CellSize * this.boardSize),
                (this.CellSize * this.boardSize) + 20, 10);

        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col< this.boardSize; col++) {
                g.setColor(colors[row][col]);
                g.fillRect(this.Margin + (23 * col), this.Margin + (23 * row), 23, 23);
            }
        }
    }

    public void floodFill(int X, int Y, Color replaceC, Color targetC) {
        if (targetC.equals(replaceC)) {
            return;
        }
        if (!this.colors[Y][X].equals(targetC)) {
            return;
        }
        this.colors[Y][X] = replaceC;
        //West
        if (X > 0) {
            floodFill(X - 1, Y, replaceC, targetC);
        }
        //East
        if (X < 19) {
            floodFill(X + 1, Y, replaceC, targetC);
        }
        //South
        if (Y < 19) {
            floodFill(X, Y + 1, replaceC, targetC);
        }
        //North
        if (Y > 0) {
            floodFill(X, Y - 1, replaceC, targetC);
        }

    }

    public void generateColors() {
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < this.colors[0].length; j++) {
                this.colors[i][j] = randomColor((int) (Math.random() * 7) + 1);
            }
        }
    }

    public Color getColor(int mouseX, int mouseY) {
        mouseX = (mouseX - Margin) / this.CellSize;
        mouseY = (mouseY - Margin) / this.CellSize;
        if (this.colors[mouseY][mouseX] == this.colors[0][0]) {
            return this.colors[0][0];
        } else if (mouseX > this.boardSize || mouseY > this.boardSize) {
            return this.colors[0][0];
        } else {
            this.moves++;
            return this.colors[mouseY][mouseX];
        }
    }

    public void setSize(int Size) {
        switch (Size) {
            case 0:
                this.boardSize = 12;
                this.Margin = (700 - (this.CellSize * this.boardSize)) / 2;
                this.maxMoves = 24;
                break;
            case 1:
                this.boardSize = 16;
                this.Margin = (700 - (this.CellSize * this.boardSize)) / 2;
                this.maxMoves = 33;
                break;
            case 2:
                this.boardSize = 20;
                this.Margin = (700 - (this.CellSize * this.boardSize)) / 2;
                this.maxMoves = 40;
                break;
            case 3:
                this.boardSize = 24;
                this.Margin = (700 - (this.CellSize * this.boardSize)) / 2;
                this.maxMoves = 48;
                break;
            default:
                this.boardSize = 20;
                this.Margin = (700 - (this.CellSize * this.boardSize)) / 2;

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
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.colors[0][0] == this.colors[i][j] && moves < this.maxMoves) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
