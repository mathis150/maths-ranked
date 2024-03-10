package fr.mathis150.maths_ranked;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Random;

public class MatchMaking {

    RealTimeConnection matchmakingDatabase;
    int targetElo;
    int deviation;
    MatchMakingGame submitedGame;

    MatchMaking(int targetElo,int deviation) {
        matchmakingDatabase = new RealTimeConnection("matchmaking/queue");
        this.targetElo = targetElo;
        this.deviation = deviation;
    }

    void enterQueue() {
        matchmakingDatabase.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot games : snapshot.getChildren()) {
                    MatchMakingGame game = games.getValue(MatchMakingGame.class);
                    if (game.maxElo > targetElo && game.minElo < targetElo) {
                        if (Objects.equals(game.uuid, submitedGame.uuid)) {
                            continue;
                        } else {
                            joinGame(game);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void joinGame(MatchMakingGame game) {
        //TODO redirect to match screen
        RealTimeConnection matchDatabase = new RealTimeConnection("match/" + submitedGame.uuid);
        matchDatabase.Write("P2joined");
        matchDatabase.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot info : snapshot.getChildren()) {
                    if (info.toString() == "gameCanceled") {
                        joinGame(submitedGame);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //TODO handle match start (change layout, start game)
        Match match = new Match(game.uuid,game.matchLength);
    }

    void createGame() {
        //add a game to the queue and wait for someone to respond
        this.submitedGame = new MatchMakingGame(targetElo-deviation,targetElo+deviation);
        matchmakingDatabase.database.setValue(submitedGame);
        RealTimeConnection matchDatabase = new RealTimeConnection("match/" + submitedGame.uuid);
        matchDatabase.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot info : snapshot.getChildren()) {
                    if (info.toString() == "P2joined") {
                        joinGame(submitedGame);
                        //TODO delete game from the queue
                    }
                    if (info.toString() == "gameCanceled") {
                        //TODO handle canceled game (return to leaderboard screen)
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void leaveQueue() {
        RealTimeConnection matchDatabase = new RealTimeConnection("match/" + submitedGame.uuid);
        matchDatabase.Write("gameCanceled");
        //TODO delete match from database
    }
}
