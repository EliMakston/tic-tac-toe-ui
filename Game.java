import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    public static void main(String[] args) {
        Window frame = new Window("Tic Tac Toe");
        Board gameBoard = new Board();
        //RandomAI random = new RandomAI(gameBoard, 1);
        MiniMaxAI minimax = new MiniMaxAI(gameBoard, 1);
        gameBoard.printCurrentPosition();
        myMouseListener mml = new myMouseListener(frame.panel, gameBoard, minimax);
        frame.panel.addMouseListener(mml);
        frame.drawBoard(gameBoard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Need this to run the window for a reason
    }
}
