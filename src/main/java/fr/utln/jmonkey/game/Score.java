package fr.utln.jmonkey.game;

import lombok.Getter;

@Getter
public class Score {

    private int pointAmount = 0;

    public void addPoint() {
        this.pointAmount++;
    }

    public void resetPointAmount() {
        pointAmount = 0;
    }

}
