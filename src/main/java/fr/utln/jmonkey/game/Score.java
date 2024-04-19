package fr.utln.jmonkey.game;

public class Score {

    private int pointAmount = 0;

    public void addPoint() {
        this.pointAmount++;
    }

    public void resetPointAmount() {
        pointAmount = 0;
    }

}
