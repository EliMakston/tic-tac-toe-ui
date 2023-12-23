import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    public static void main(String[] args) {
        Window frame = new Window("Tic Tac Toe");
        Board gameBoard = new Board();
        //RandomAI ai = new RandomAI(gameBoard, 1);
        //MiniMaxAI ai = new MiniMaxAI(gameBoard, 1);
        AlphaBetaAI ai = new AlphaBetaAI(gameBoard, 1);
        gameBoard.printCurrentPosition();
        myMouseListener mml = new myMouseListener(frame.panel, gameBoard, ai);
        frame.panel.addMouseListener(mml);
        frame.drawBoard(gameBoard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Need this to run the window for a reason
    }
}
