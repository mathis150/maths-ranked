package fr.mathis150.maths_ranked;

import java.util.Objects;
import java.util.Random;


public class Problem {
    public String equation;
    public String answer;

    Problem(String equation,String answer) {
        this.equation = equation;
        this.answer = answer;
    }

    boolean verify(String attempt) {
        return Objects.equals(attempt, this.answer);
    }

    Problem generate(int seed,int n) {

        Random generator = new Random(seed);
        for (int i = 0;i < n; i++) {
        }
        return null;
    }
}
