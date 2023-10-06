package com.sorianotapia.combat;

import com.sorianotapia.accessories.Arm;

public class Policeman extends NPC {
    public Policeman(Arm arm) {
        super(arm);
    }

    @Override
    public boolean escapeEnemies(Fighter... enemies) {
        return true;
    }

    @Override
    public void act(Fighter... enemies) {

    }
}
