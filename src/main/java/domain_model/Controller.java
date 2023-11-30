package domain_model;

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
                                String discipline,
                                double subscription) {

        memberDatabase.addMemberToList(type, name, birthDate, email, discipline, subscription);
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
}
