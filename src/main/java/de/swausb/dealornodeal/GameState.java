package de.swausb.dealornodeal;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private EState currentState;
    private int luckChest;
    private ArrayList<Integer> openedChest, calledDeals;

    public GameState(EState eState) {
        currentState = eState;
        this.luckChest = 0;
        this.openedChest = new ArrayList<>();
        this.calledDeals = new ArrayList<>();
    }

    public GameState(EState currentState, int luckChest, ArrayList<Integer> openedChest, ArrayList<Integer> calledDeals) {
        this.currentState = currentState;
        this.luckChest = luckChest;
        this.openedChest = openedChest;
        this.calledDeals = calledDeals;
    }

    public EState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EState currentState) {
        this.currentState = currentState;
    }

    public ArrayList<Integer> getOpenedChest() {
        return openedChest;
    }

    public ArrayList<Integer> getCalledDeals() {
        return calledDeals;
    }

    public int getLuckChest() {
        return luckChest;
    }

    public void setLuckChest(int luckChest) {
        this.luckChest = luckChest;
    }
}
