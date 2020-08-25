package dto;

import enums.Player;

import java.util.Objects;

public class PlayerStatus {
    private final Player player;
    private final int lines;
    private final boolean isTurn;

    public PlayerStatus(Player player, int lines, boolean isTurn) {
        this.player = player;
        this.lines = lines;
        this.isTurn = isTurn;
    }

    public int getLines() {
        return lines;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTurn() {
        return isTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatus that = (PlayerStatus) o;
        return player == that.player &&
                lines == that.lines &&
                isTurn == that.isTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, lines, isTurn);
    }

    @Override
    public String toString() {
        return "dto.PlayerState{" +
                "player=" + player +
                ", lines=" + lines +
                ", isTurn=" + isTurn +
                '}';
    }
}
