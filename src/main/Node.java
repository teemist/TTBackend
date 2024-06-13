package main;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int number;
    private boolean isVisited;
    private List<Node> neigs;

    public void setNeigs(List<Node> neigs) {
        this.neigs = neigs;
    }

    public Node(int number, boolean isVisited) {
        this.number = number;
        this.isVisited = isVisited;
        this.neigs = new ArrayList<>();
    }

    public List<Node> getNeigs() {
        return neigs;
    }

    public int getNumber() {
        return number;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

}
