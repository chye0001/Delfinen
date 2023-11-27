package domain_model.teams;

import domain_model.Leaderboard;
import domain_model.members.Member;

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

    public void addMember(Member member){
        members.add(member);
    }

    /* TODO
    public void addLeaderboardResult()
     */

}
