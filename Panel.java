import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel {
    Board gameBoard = new Board();
    int playerWonID = -1;

    Panel() {
        this.setPreferredSize(new Dimension(500, 500));
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        drawBoard(g2D);
        g2D.setFont(new Font("Courier", Font.BOLD,100));
        for (int i = 0; i < gameBoard.currentPosition.length; i++) {
            for (int z = 0; z < gameBoard.currentPosition[i].length; z++) {
                int x = (z + 1) * 50 + 30 + (z * 85);
                int y = (i + 1) * 50 + 100 + (i * 85);
                if (gameBoard.currentPosition[i][z].ID == 0) {
                    g2D.drawString("X", x, y);
                }
                if (gameBoard.currentPosition[i][z].ID == 1) {
                    g2D.drawString("O", x, y);
                }
            }
        }
        if (playerWonID == 1) {
            g2D.setFont(new Font("Courier", Font.BOLD,30));
            g2D.drawString("Player O won", 150, 480);
        }
        if (playerWonID == 0) {
            g2D.setFont(new Font("Courier", Font.BOLD,30));
            g2D.drawString("Player X won", 150, 480);
        }
        if (playerWonID == 2) {
            g2D.setFont(new Font("Courier", Font.BOLD,30));
            g2D.drawString("The game tied", 125, 480);
        }
        // Format for drawaing in the 50, 50 space with font 100
        // g2D.drawString("X", 80, 150);
    }

    public void setBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void drawBoard(Graphics2D g2D) {
        g2D.fillRoundRect(165, 50, 20, 400, 20, 20);
        g2D.fillRoundRect(305, 50, 20, 400, 20, 20);
        g2D.fillOval(50, 165, 20, 20);
        g2D.fillOval(50, 315, 20, 20);
        g2D.fillOval(430, 165, 20, 20);
        g2D.fillOval(430, 315, 20, 20);
        g2D.fillRect(60, 165, 380, 20);
        g2D.fillRect(60, 315, 380, 20);
    }

    public void gameOver(int playerID, int wasATie) {
        playerWonID = playerID;
        if (wasATie == 2) {
            playerWonID = 2;
        }
        repaint();
    }
}
