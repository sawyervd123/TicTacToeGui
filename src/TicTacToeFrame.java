import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private final char[][] board = new char[3][3];

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for Tic-Tac-Toe board
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        initializeBoard(boardPanel);

        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].setFocusPainted(false);

                final int r = row, c = col;
                buttons[row][col].addActionListener(e -> handleMove(r, c));

                boardPanel.add(buttons[row][col]);
                board[row][col] = ' '; // Initialize empty board
            }
        }
    }

    private void handleMove(int row, int col) {
        if (board[row][col] != ' ') {
            JOptionPane.showMessageDialog(this, "Invalid move! Try again.");
            return;
        }

        board[row][col] = currentPlayer;
        buttons[row][col].setText(String.valueOf(currentPlayer));

        if (checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins! Play Again?");
            resetGame();
            return;
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie! Play Again?");
            resetGame();
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }
}
