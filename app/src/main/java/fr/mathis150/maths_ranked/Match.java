package fr.mathis150.maths_ranked;

public class Match {

    public int seed;
    public User[] players;

    public Problem[] problems;
    public int turn;

    Match(User[] players) {
        this.players = players;
    }

    public void GenerateProblems() {

    }
}
