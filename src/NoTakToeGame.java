import algorithm.NoTicTakToe;
import dto.PlayerActResult;
import enums.BoardNutValue;
import enums.Player;
import algorithm.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NoTakToeGame {
    String[] players;
    SequentialGame noTicTakToe;
    int row, col;
    PlayerActResult playerActResult;


    private PlayerActResult computerPlay() {
        Random random = new Random();
        PlayerActResult computer = null;
        int randomX = random.nextInt(noTicTakToe.getRaw());
        int randomY = random.nextInt(noTicTakToe.getCol());
        computer = noTicTakToe.play(randomX, randomY, Player.PLAYER_TWO);
        return computer;
    }

    public NoTakToeGame(String[] players, SequentialGame noTicTakToe) {
        this.players = players;
        this.noTicTakToe = noTicTakToe;
    }

    public void start() {
        System.out.println(noTicTakToe);
        while (true) {
            if (noTicTakToe.isFinished()) {
                System.out.println("Game is finished");
                return;
            }
            System.out.println("_____" + "player1 " + "turn" + "______");
            try {
                playerActResult = play();
            }catch (Exception e) {
                playerActResult = play();
            }
            System.out.println(Arrays.toString(playerActResult.getPlayerStatuses()));
            System.out.println(noTicTakToe);

            if (noTicTakToe.isFinished()) {
                System.out.println("Game is finished");
                return;
            }
            System.out.println("_____" + "player2 " + "turn" + "______");
            try {
                playerActResult = computerPlay();
            } catch (Exception e) {
                playerActResult = computerPlay();
            }
            System.out.println(Arrays.toString(playerActResult.getPlayerStatuses()));
            System.out.println(noTicTakToe);
        }
    }


    PlayerActResult play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            do {
                System.out.print("Enter row: ");
                row = scanner.nextInt();
                System.out.println();
            } while (row <= 0);
            do {
                System.out.print("Enter col: ");
                col = scanner.nextInt();
                System.out.println();
            } while (col <= 0);
            if (noTicTakToe.valueOf(row - 1, col - 1) == BoardNutValue.EMPTY.getValue()) {
                return noTicTakToe.play(row - 1, col - 1, Player.PLAYER_ONE);
            }
        }
    }

    public static void main(String[] args) {
        SequentialGame noTicTakToe = new NoTicTakToe(4, 4);
        String[] players = new String[2];
        players[0] = "mohsen";
        players[1] = "mehdi";
        NoTakToeGame noTakToeGame = new NoTakToeGame(players, noTicTakToe);
        noTakToeGame.start();
    }

}

