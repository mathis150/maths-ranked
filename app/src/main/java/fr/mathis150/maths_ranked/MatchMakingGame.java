package fr.mathis150.maths_ranked;

import java.nio.charset.Charset;
import java.util.Random;

public class MatchMakingGame {
    public int minElo;
    public int maxElo;
    public int matchLength;
    String uuid;



    MatchMakingGame(int minElo, int maxElo) {
        this.minElo = minElo;
        this.maxElo = maxElo;
        this.matchLength = 10;
        generateUuid();
    }

    public void generateUuid() {
        byte[] array = new byte[32]; // length is bounded by 7
        new Random().nextBytes(array);
        this.uuid = new String(array, Charset.forName("UTF-8"));
    }
}
