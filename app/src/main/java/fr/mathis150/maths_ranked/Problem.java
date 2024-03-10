package fr.mathis150.maths_ranked;

import android.annotation.SuppressLint;

import java.util.Objects;
import java.util.Random;


public class Problem {
    public String equation;
    public String answer;

    private final Random generator;

    Problem(int seed) {
        this.generator = new Random(seed);
        generateRandomProblem();
    }

    boolean verify(String attempt) {
        return Objects.equals(attempt, this.answer);
    }

    @SuppressLint("DefaultLocale")
    private void generateRandomProblem() {
        int difficulty = 10;

        int problemkind = generator.nextInt() % 4;

        String equation = "";
        int initial = generator.nextInt() % difficulty + 1;
        int mult = generator.nextInt() % difficulty + 2;
        int divide = generator.nextInt() % difficulty + 2;
        int sub = generator.nextInt() % difficulty + 1;
        int add = generator.nextInt() % difficulty + 1;

        switch (problemkind) {
            case 0:
                equation = String.format("%s%d%s%d%s%d%s%d","(",mult,"x + ",add,") / ",divide," = ",((mult*initial) + add) / divide);
            case 1:
                equation = String.format("%d%s%d%s%d%s%d",mult,"x/",divide," + ",add,") = ",((mult*initial)/divide) + add);
            case 2:
                equation = String.format("%d%s%d%s%d%s%d",mult,"(x + ",add,") - ",sub," = ",((initial + add) * mult ) - sub);
            case 3:
                equation = String.format("%s%d%s%d%s%d%s%d","(x - ", sub,") / ",divide," + ",add," = ",((initial - sub)/divide)+add);
        }

        this.answer = String.valueOf(initial);
        this.equation = equation;
    }
}
