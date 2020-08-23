
import java.io.Serializable;
import java.util.Arrays;

public final class NoTicTakToe implements BoardGame {
    //board game array
    private byte[][] gameBoard;
    //number of rows
    private final int row;
    //number of cols
    private final int col;

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    private PlayerState[] playerStates;

    @Override
    public void updateMatrix(byte[][] matrix) {
        this.gameBoard = matrix;
    }

    @Override
    public void updatePlayerState(PlayerState[] playerStates) {
        this.playerStates = playerStates;
    }

    @Override
    public ActPlay startNewRound() {
        gameBoard = new byte[row][col];
        for (byte[] rows : gameBoard) {
            Arrays.fill(rows, BoardStates.EMPTY.getValue());
        }
        createPlayerStates();
        return new ActPlay(playerStates, gameBoard);
    }

    @Override
    public PlayerState[] playerState() {
        return playerStates;
    }

    @Override
    public int getHeight() {
        return col;
    }

    @Override
    public int getWidth() {
        return row;
    }

    @Override
    public byte[][] getBoard() {
        return gameBoard;
    }

    public NoTicTakToe(int row, int col) {
        this.row = row;
        this.col = col;
        gameBoard = new byte[row][col];
        for (byte[] rows : gameBoard) {
            Arrays.fill(rows, BoardStates.EMPTY.getValue());
        }
        createPlayerStates();
    }

    public NoTicTakToe(int row, int col, PlayerState[] playerStates) {
        this.row = row;
        this.col = col;
        gameBoard = new byte[row][col];
        for (byte[] rows : gameBoard)
            Arrays.fill(rows, BoardStates.EMPTY.getValue());
        this.playerStates = playerStates;
    }

    public NoTicTakToe(int row, int col, PlayerState[] playerStates, byte[][] gameBoard) {
        this.row = row;
        this.col = col;
        this.playerStates = playerStates;
        this.gameBoard = gameBoard;
    }

    @Override
    public ActPlay play(int x, int y) {
        int player = 0;
        for (int i = 0; i < playerStates.length; i++) {
            if (playerStates[0].isTurn())
                player = Player.PLAYER_ONE.getPlayer();
            else player = Player.PLAYER_TWO.getPlayer();
        }
        if (gameBoard[x][y] == BoardStates.EMPTY.getValue()) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    //change player nut to default nut
                    if (gameBoard[i][j] == BoardStates.FILL_PLAYER_ONE.getValue() ||
                            gameBoard[i][j] == BoardStates.FILL_PLAYER_TWO.getValue())
                        gameBoard[i][j] = BoardStates.FILL_DEFAULT.getValue();

                    //change new line of player_one to default line of player one
                    if (gameBoard[i][j] == BoardStates.NEW_LINE_PLAYER_ONE.getValue() ||
                            gameBoard[i][j] == BoardStates.NEW_LINE_PLAYER_ONE_FACTOR.getValue())
                        gameBoard[i][j] = BoardStates.LINE_PLAYER_ONE.getValue();

                    //change new line of player_two to default line of player two
                    if (gameBoard[i][j] == BoardStates.NEW_LINE_PLAYER_TWO.getValue() ||
                            gameBoard[i][j] == BoardStates.NEW_LINE_PLAYER_TWO_FACTOR.getValue())
                        gameBoard[i][j] = BoardStates.LINE_PLAYER_TWO.getValue();
                }
            }
            //fill board with player one nut or player two nut
            if (player == Player.PLAYER_ONE.getPlayer())
                gameBoard[x][y] = BoardStates.FILL_PLAYER_ONE.getValue();
            else gameBoard[x][y] = BoardStates.FILL_PLAYER_TWO.getValue();

            //update player states and board
            int numberOfLines = isCreateLine((byte) player, x, y);
            if (numberOfLines > 0) {
                if (player == Player.PLAYER_ONE.getPlayer())
                    gameBoard[x][y] = BoardStates.NEW_LINE_PLAYER_ONE_FACTOR.getValue();
                else
                    gameBoard[x][y] = BoardStates.NEW_LINE_PLAYER_TWO_FACTOR.getValue();
            }
            if (playerStates[0].isTurn()) {
                int totalLine = playerStates[1].getLines() + numberOfLines;
                playerStates[0] = new PlayerState(playerStates[0].getUserId(), playerStates[0].getLines(), false);
                playerStates[1] = new PlayerState(playerStates[1].getUserId(), totalLine, true);
            } else if (playerStates[1].isTurn()) {
                int totalLine = playerStates[0].getLines() + numberOfLines;
                playerStates[0] = new PlayerState(playerStates[0].getUserId(), totalLine, true);
                playerStates[1] = new PlayerState(playerStates[1].getUserId(), playerStates[1].getLines(), false);
            }
        }
        return new ActPlay(playerStates, gameBoard);
    }

    private void createPlayerStates() {
        playerStates = new PlayerState[2];
        for (int i = 0; i < playerStates.length; i++) {
            boolean isTurn;
            Player player;
            if (i == 0) player = Player.PLAYER_ONE;
            else player = Player.PLAYER_TWO;
            isTurn = i == 0;
            playerStates[i] = new PlayerState(player.getPlayer(), 0, isTurn);
        }
    }

    private int isCreateLine(byte player, int x, int y) {
        // should traverse
        int up = 0;
        int up_right = 0;
        int right = 0;
        int btm_right = 0;
        int btm = 0;
        int btm_left = 0;
        int left = 0;
        int up_left = 0;
        int numberOfLine = 0;
        // iterate to up to looking for Fill state{col is static}
        for (int i = x - 1; i >= x - 2 && i >= 0; i--) {
            if (gameBoard[i][y] == BoardStates.FILL_DEFAULT.getValue()) {
                up++;
            } else break;
        }
        //iterate to btm to looking for fill state{col is static}
        for (int i = x + 1; i <= x + 2 && i < row; i++) {
            if (gameBoard[i][y] == BoardStates.FILL_DEFAULT.getValue()) {
                btm++;
            } else break;
        }
        //iterate to right to looking for fill state{row is static}
        for (int i = y + 1; i <= y + 2 && i < col; i++) {
            if (gameBoard[x][i] == BoardStates.FILL_DEFAULT.getValue()) {
                right++;
            } else break;
        }
        //iterate to left to looking for fill state{row is static}
        for (int i = y - 1; i >= y - 2 && i >= 0; i--) {
            if (gameBoard[x][i] == BoardStates.FILL_DEFAULT.getValue()) {
                left++;
            } else break;
        }
        //iterate to up-right to looking for fill state{nor row and col is static}
        for (int i = x - 1; i >= x - 2 && i >= 0; i--) {
            int colY = y + up_right + 1;
            if (colY >= col) break;
            byte value = gameBoard[i][colY];
            if (value == BoardStates.FILL_DEFAULT.getValue()) {
                up_right++;
            } else break;
        }
        //iterate to btm_right to looking for fill states(nor row and col is static)
        for (int i = y + 1; i <= y + 2 && i < col; i++) {
            int rowX = x + btm_right + 1;
            if (rowX >= row) break;
            byte value = gameBoard[rowX][i];
            if (value == BoardStates.FILL_DEFAULT.getValue()) {
                btm_right++;
            } else break;
        }
        //iterate to up-left to looking for fill states(nor row and col is static)
        for (int i = y - 1; i >= y - 2 && i >= 0; i--) {
            int rowX = x - 1 - up_left;
            if (rowX < 0) break;
            byte value = gameBoard[rowX][i];
            if (value == BoardStates.FILL_DEFAULT.getValue()) {
                up_left++;
            } else break;
        }
        //iterate to btm-left  to looking for fill st{ates(nor row and col is static)
        for (int i = x + 1; i <= x + 2 && i < row; i++) {
            int colY = y - 1 - btm_left;
            if (colY < 0) break;
            byte value = gameBoard[i][colY];
            if (value == BoardStates.FILL_DEFAULT.getValue()) {
                btm_left++;
            } else break;
        }
        //check for creating line up to btm
        if (up + btm + 1 >= 3) {
            for (int i = x - up; i <= x + btm; i++) {
                if (player == Player.PLAYER_ONE.getPlayer())
                    gameBoard[i][y] = BoardStates.NEW_LINE_PLAYER_ONE.getValue();
                else gameBoard[i][y] = BoardStates.NEW_LINE_PLAYER_TWO.getValue();
            }
            numberOfLine++;
        }
        //check for creating line left to right
        if (right + left + 1 >= 3) {
            for (int i = y - left; i <= y + right; i++) {
                if (player == Player.PLAYER_ONE.getPlayer())
                    gameBoard[x][i] = BoardStates.NEW_LINE_PLAYER_ONE.getValue();
                else gameBoard[x][i] = BoardStates.NEW_LINE_PLAYER_TWO.getValue();
            }
            numberOfLine++;
        }
        //check for creating line form up-right to btm-left
        if (up_right + btm_left + 1 >= 3) {
            int count = 0;
            for (int i = y - btm_left; i <= y + up_right; i++) {
                if (player == Player.PLAYER_ONE.getPlayer())
                    gameBoard[x + btm_left - count][i] = BoardStates.NEW_LINE_PLAYER_ONE.getValue();
                else
                    gameBoard[x + btm_left - count][i] = BoardStates.NEW_LINE_PLAYER_TWO.getValue();
                count++;
            }
            numberOfLine++;
        }
        //check for creating line from up-left to btm-right
        if (up_left + btm_right + 1 >= 3) {
            int count = 0;
            for (int i = x - up_left; i <= x + btm_right; i++) {
                if (player == Player.PLAYER_ONE.getPlayer())
                    gameBoard[i][y - up_left + count] = BoardStates.NEW_LINE_PLAYER_ONE.getValue();
                else
                    gameBoard[i][y - up_left + count] = BoardStates.NEW_LINE_PLAYER_TWO.getValue();
                count++;
            }
            numberOfLine++;
        }
        //number of lines
        return numberOfLine;
    }

    @Override
    public boolean isFinished() {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (gameBoard[i][j] != BoardStates.EMPTY.getValue()) count++;
            }
        }
        return count == row * col;
    }

    @Override
    public byte valueOf(int x, int y) {
        return gameBoard[x][y];
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(gameBoard[i][j]);
                sb.append(" | ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append('\n');
        }
        return sb.toString();
    }
}
