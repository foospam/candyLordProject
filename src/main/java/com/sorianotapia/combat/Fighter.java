package com.sorianotapia.combat;

public interface Fighter {

    public void shootRandomEnemy(Fighter... enemies);
    public boolean escapeEnemies(Fighter... enemies);
    public void setHarm(int harm);
    public int gunRoll();
    public int harmRoll();

}
