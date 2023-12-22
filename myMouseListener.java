import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class myMouseListener implements MouseListener{
    Panel panel;
    Board boardState;
    Board boardCopy;
    AI ai;

    myMouseListener(Panel panel, Board boardState, AI ai) {
        this.panel = panel;
        this.boardState = boardState;
        System.out.println("This ran");
        this.boardCopy = boardState.copy();
        System.out.println("It didn't break here");
        this.ai = ai;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 50 && e.getX() < 165) {
            if (e.getY() > 50 && e.getY() < 165) {
                if (boardCopy.currentPosition[0][0].ID == 0) {
                    boardCopy.currentPosition[0][0] = new Piece(1);
                } else {
                    boardCopy.currentPosition[0][0] = new Piece(0);
                }
            }
            if (e.getY() > 185 && e.getY() < 305) {
                if (boardCopy.currentPosition[1][0].ID == 0) {
                    boardCopy.currentPosition[1][0] = new Piece(1);
                } else {
                    boardCopy.currentPosition[1][0] = new Piece(0);
                }
            }
            if (e.getY() > 325 && e.getY() < 450) {
                if (boardCopy.currentPosition[2][0].ID == 0) {
                    boardCopy.currentPosition[2][0] = new Piece(1);
                } else {
                    boardCopy.currentPosition[2][0] = new Piece(0);
                }
            }
        }
        if (e.getX() > 185 && e.getX() < 305) {
            if (e.getY() > 50 && e.getY() < 165) {
                if (boardCopy.currentPosition[0][1].ID == 0) {
                    boardCopy.currentPosition[0][1] = new Piece(1);
                } else {
                    boardCopy.currentPosition[0][1] = new Piece(0);
                }
            }
            if (e.getY() > 185 && e.getY() < 305) {
                if (boardCopy.currentPosition[1][1].ID == 0) {
                    boardCopy.currentPosition[1][1] = new Piece(1);
                } else {
                    boardCopy.currentPosition[1][1] = new Piece(0);
                }
            }
            if (e.getY() > 325 && e.getY() < 450) {
                if (boardCopy.currentPosition[2][1].ID == 0) {
                    boardCopy.currentPosition[2][1] = new Piece(1);
                } else {
                    boardCopy.currentPosition[2][1] = new Piece(0);
                }
            }
        }
        if (e.getX() > 325 && e.getX() < 450) {
            if (e.getY() > 50 && e.getY() < 165) {
                if (boardCopy.currentPosition[0][2].ID == 0) {
                    boardCopy.currentPosition[0][2] = new Piece(1);
                } else {
                    boardCopy.currentPosition[0][2] = new Piece(0);
                }
            }
            if (e.getY() > 185 && e.getY() < 305) {
                if (boardCopy.currentPosition[1][2].ID == 0) {
                    boardCopy.currentPosition[1][2] = new Piece(1);
                } else {
                    boardCopy.currentPosition[1][2] = new Piece(0);
                }
            }
            if (e.getY() > 325 && e.getY() < 450) {
                if (boardCopy.currentPosition[2][2].ID == 0) {
                    boardCopy.currentPosition[2][2] = new Piece(1);
                } else {
                    boardCopy.currentPosition[2][2] = new Piece(0);
                }
            }
        }
        boardState.makeMove(boardCopy);
        panel.setBoard(boardState);
        panel.repaint();
        // Check if a player won
        
        if (boardState.didAnyoneWin() > 1) {
            boardState.printCurrentPosition();
            panel.gameOver(0, boardState.didAnyoneWin());
            return;
        }
        boardCopy = ai.makeMove();
        boardState.makeMove(boardCopy);
        panel.setBoard(boardState);
        panel.repaint();
        // Check if a player won
        if (boardState.didAnyoneWin() > 0) {
            boardState.printCurrentPosition();
            panel.gameOver(1, boardState.didAnyoneWin());
            return;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}
