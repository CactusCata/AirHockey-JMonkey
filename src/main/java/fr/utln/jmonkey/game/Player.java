package fr.utln.jmonkey.game;

import lombok.Getter;

public class Player {

    @Getter
    private final PlayerNumber playerNumber;
    @Getter
    private final Score score;

    public Player(PlayerNumber playerNumber) {
        this.playerNumber = playerNumber;
        this.score = new Score();
    }

}
