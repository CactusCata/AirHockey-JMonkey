package fr.utln.jmonkey.bot;

import fr.utln.jmonkey.bot.behaviors.BotBehaviorUpdater;
import fr.utln.jmonkey.game.Player;
import fr.utln.jmonkey.game.PlayerNumber;
import fr.utln.jmonkey.items.list.Puck;
import fr.utln.jmonkey.items.list.Racket;

public class Bot extends Player {

    private final BotBehaviorUpdater botBehaviorUpdater;

    public Bot() {
        super(PlayerNumber.PLAYER_TWO);
        this.botBehaviorUpdater = new BotBehaviorUpdater();
    }

    public void updateRacketPos(Puck puck, Racket botRacket, Racket enemyRacket) {
        botBehaviorUpdater.update(puck, botRacket, enemyRacket);
    }

}
