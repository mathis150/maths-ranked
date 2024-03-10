package fr.mathis150.maths_ranked;

public class User {
    public String uuid;
    public Object data;

    User(String docId,Object docData) {
        uuid = docId;
        data = docData;
    }
}
