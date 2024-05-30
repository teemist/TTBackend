package main;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int number;
    private int distance;
    private List<Node> neigs;

    public void setNeigs(List<Node> neigs) {
        this.neigs = neigs;
    }

    public Node(int number, int distance, boolean isVisited) {
        this.number = number;
        this.distance = distance;
        this.neigs = new ArrayList<>();
    }

    public List<Node> getNeigs() {
        return neigs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
