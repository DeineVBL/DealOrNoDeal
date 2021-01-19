package de.swausb.dealornodeal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

    private EState currentState;
    private int luckChest;
    private ArrayList<Integer> openedChest, calledDeals;
    private ArrayList<Integer> availableMoney;

    public Game(EState eState) {
        currentState = eState;
        this.luckChest = 0;
        this.openedChest = new ArrayList<>();
        this.calledDeals = new ArrayList<>();
        this.availableMoney = new ArrayList<>(Arrays.asList(1, 5, 10, 15, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000));
    }

    public Game(EState currentState, int luckChest, ArrayList<Integer> openedChest, ArrayList<Integer> calledDeals) {
        this.currentState = currentState;
        this.luckChest = luckChest;
        this.openedChest = openedChest;
        this.calledDeals = calledDeals;
    }

    public int getRandomMoney () {
        return getAvailableMoney().get(new Random().nextInt(getAvailableMoney().size()));
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

    public ArrayList<Integer> getAvailableMoney() {
        return availableMoney;
    }
}
