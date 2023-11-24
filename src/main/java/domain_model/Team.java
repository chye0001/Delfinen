package domain_model;

import java.util.ArrayList;

public abstract class Team {
    private ArrayList<Member> members = new ArrayList<>();
    private Leaderboard leaderboard = new Leaderboard();

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    /* TODO
    public void addMember()
    public void addLeaderboardResult()
     */

}
