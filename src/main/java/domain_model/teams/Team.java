package domain_model.teams;

import domain_model.Result;
import domain_model.members.Member;

import java.util.ArrayList;

public abstract class Team {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Result> leaderboard = new ArrayList<>();

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Result> getLeaderboard() {
        return leaderboard;
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void addResultToLeaderboard(Result result){
        leaderboard.add(result);
    }
}
