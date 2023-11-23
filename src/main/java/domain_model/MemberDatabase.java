package domain_model;

import java.util.ArrayList;

public class MemberDatabase {
    private ArrayList<Member> members = new ArrayList<>();
    private final Team[] teams = {new JuniorTeam(), new SeniorTeam()};

    public ArrayList<Member> getMembers() {
        return members;
    }

    /** TODO
     Overview
     * Method for overview of members with all info (administrator)
     * Method for overview of subscriptions / debt (accountant)
     * Method for overview of teams (coach)
     Add
     * Method for adding new member (administrator)
     * Method for adding new team member result (coach)
     Save for persistence
     * Save members to a list (administrator)
     * Save subscriptions to a list (accountant)
     * Save team leaderboards to a list (coach)
     */

}
