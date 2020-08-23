
public enum Player {

    PLAYER_ONE((byte) 1),
    PLAYER_TWO((byte) 2);

    private final byte player;

    public byte getPlayer() {
        return player;
    }

    private Player(byte player) {
        this.player = player;
    }

}
