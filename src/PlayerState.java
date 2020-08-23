import java.util.Objects;

public class PlayerState {
    private final int userId;
    private final int lines;
    private final boolean isTurn;

    public PlayerState(int userId, int lines, boolean isTurn) {
        this.userId = userId;
        this.lines = lines;
        this.isTurn = isTurn;
    }

    public int getLines() {
        return lines;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isTurn() {
        return isTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerState that = (PlayerState) o;
        return userId == that.userId &&
                lines == that.lines &&
                isTurn == that.isTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lines, isTurn);
    }

    @Override
    public String toString() {
        return "PlayerState{" +
                "userId=" + userId +
                ", lines=" + lines +
                ", isTurn=" + isTurn +
                '}';
    }
}
