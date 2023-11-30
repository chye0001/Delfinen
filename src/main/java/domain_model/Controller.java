package domain_model;

import domain_model.members.MemberType;

import java.time.LocalDate;

public class Controller {

    MemberDatabase memberDatabase = new MemberDatabase();

    public Controller() {
        memberDatabase.loadMemberDatabase();
        memberDatabase.loadCompetitiveResults();
    }

    public void addMemberToList(MemberType type,
                                String name,
                                LocalDate birthDate,
                                String email,
                                String discipline) {

        memberDatabase.addMemberToList(type, name, birthDate, email, discipline);
    }

    public void addResultToTeam(String email, double time) {
        memberDatabase.addResultToTeam(email, time);
    }

    public String showListOfMembers() {
        return memberDatabase.showListOfMembers();
    }

    public String showListOfSubscriptions() {
        return memberDatabase.showListOfSubscription();
    }

    public double showIncomeForecast() {
        return memberDatabase.showIncomeForecast();
    }

    public Team getJuniorTeam() {
        return memberDatabase.getJuniorTeam();
    }

    public Team getSeniorTeam() {
        return memberDatabase.getSeniorTeam();
    }

    public String showLeaderBoard(Discipline discipline) {
        return memberDatabase.showLeaderBoard(discipline);
    }
}
