import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NoTakToeGame {
    String[] players;
    NoTicTakToe noTicTakToe;
    int row, col;
    ActPlay actPlay;


    private ActPlay computerPlay() {
        Random random = new Random();
        ActPlay computer;
        int randomX = random.nextInt(noTicTakToe.getRow());
        int randomY = random.nextInt(noTicTakToe.getCol());
        computer = noTicTakToe.play(randomX, randomY);
        return computer;
    }

    public NoTakToeGame(String[] players, NoTicTakToe noTicTakToe) {
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
            actPlay = play();
//            mohsen += play;
//            System.out.print(play);
            System.out.println(Arrays.toString(actPlay.getPlayerStates()));
            System.out.println(noTicTakToe);

            if (noTicTakToe.isFinished()) {
                System.out.println("Game is finished");
                return;
            }
            System.out.println("_____" + "player2 " + "turn" + "______");
            actPlay = computerPlay();
            System.out.println(Arrays.toString(actPlay.getPlayerStates()));
            System.out.println(noTicTakToe);
        }
    }


    ActPlay play() {
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
            if (noTicTakToe.valueOf(row - 1, col - 1) == BoardStates.EMPTY.getValue()) {
                return noTicTakToe.play(row - 1, col - 1);
            }
        }
    }

    public static void main(String[] args) {
        NoTicTakToe noTicTakToe = new NoTicTakToe(5, 7);
        String[] players = new String[2];
        players[0] = "mohsen";
        players[1] = "mehdi";
        NoTakToeGame noTakToeGame = new NoTakToeGame(players, noTicTakToe);
        noTakToeGame.start();
    }

}

