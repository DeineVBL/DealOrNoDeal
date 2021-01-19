package de.swausb.dealornodeal;

public class GameState {

    private EState currentState;
    private int openedChest, calledDeals;

    public GameState (EState eState) {
        currentState = eState;
        this.openedChest = 0;
        this.calledDeals = 0;
    }

    public GameState(EState currentState, int openedChest, int calledDeals) {
        this.currentState = currentState;
        this.openedChest = openedChest;
        this.calledDeals = calledDeals;
    }

    public EState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EState currentState) {
        this.currentState = currentState;
    }

    public int getOpenedChest() {
        return openedChest;
    }

    public void setOpenedChest(int openedChest) {
        this.openedChest = openedChest;
    }

    public int getCalledDeals() {
        return calledDeals;
    }

    public void setCalledDeals(int calledDeals) {
        this.calledDeals = calledDeals;
    }
}
