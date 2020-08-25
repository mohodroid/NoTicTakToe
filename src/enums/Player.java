package enums;

public enum Player {

    PLAYER_ONE(1),
    PLAYER_TWO(2);

    private final int player;

    Player(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

}
