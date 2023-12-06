package domain_model;

import domain_model.members.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    MemberDatabase memberDatabase = new MemberDatabase();

    public Controller() {
        memberDatabase.loadMemberDatabase();
        //memberDatabase.loadCompetitiveResults();
    }

    public void addMemberToList(MemberType type,
                                String name,
                                LocalDate birthDate,
                                String email) {

        memberDatabase.addMemberToList(type, name, birthDate, email);
    }

    public void addResultToTeam(String email, double time, String discipline) {
        //memberDatabase.addResultToTeam(email,time,discipline);
    }

    public String showListOfMembers(boolean withNumbers) {
        return memberDatabase.showListOfMembers(withNumbers);
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

    public ArrayList<Member> getClubMembers() {
        return memberDatabase.getClubMembers();
    }

    public int getSizeOfMemberDatabase () {
        return memberDatabase.getSizeOfClubMembers();
    }

    public String getMemberName(int memberIndex) {
        return memberDatabase.getMemberName(memberIndex);
    }

    public void deleteMember(int memberIndex) {
        memberDatabase.deleteMember(memberIndex);
    }
}
