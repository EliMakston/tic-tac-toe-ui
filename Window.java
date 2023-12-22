import javax.swing.JFrame;

public class Window extends JFrame {
    Panel panel;

    Window(String name) {
        panel = new Panel();
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(name);
        this.setVisible(true);
    }

    public void drawBoard(Board gameBoard) {
        panel.setBoard(gameBoard);
        panel.repaint();
    }
}
