package fr.mathis150.maths_ranked;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Queue;

public class Match {
    public Queue<Problem> problems;
    private final RealTimeConnection matchDatabaseP1;
    private boolean P1finished = false;

    Match(String matchId,int matchLength) {
        RealTimeConnection matchDatabaseP2 = new RealTimeConnection("match/" + matchId + "/P2");
        matchDatabaseP1 = new RealTimeConnection(matchId.concat("match/" + matchId + "/P1"));

        matchDatabaseP2.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    switch (snapshot.getValue().toString()) {
                        case "next":

                            break;
                        case "finished":
                            if (P1finished) {
                                //ignore
                                break;
                            }

                            //handle lost
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i = 0; i < matchLength; i++)
            problems.add(new Problem(getSeed(matchId)));
    }

    public void submitAnswer() {
        //TODO: grab answer
        String answer = "";
        if (problems.element().verify(answer)) {
            next();
        } else {
            //TODO: tell user they made an error
        }

    }

    private int getSeed(String matchId) {
        int hash = 7;
        for (int i = 0; i < matchId.length(); i++) {
            hash = hash*31 + matchId.charAt(i);
        }
        return hash;
    }

    private void finished() {
        P1finished = true;
        matchDatabaseP1.database.setValue("finished");

        Connection userDatabase = new Connection("users");

        //TODO update elo of both players
        //TODO go back to menu
    }

    private Problem next() {
        if (problems.size() == 1) {
            finished();
        }

        matchDatabaseP1.database.setValue("next");
        return problems.remove();
    }
}
