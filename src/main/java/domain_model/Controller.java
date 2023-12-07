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

    public String showIncomeForecast() {
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

    public void changePaymentStatus(int accountantChoise) {
        memberDatabase.changePaymentStatus(accountantChoise);

    }

    public void editMember(int index, Member member){
        memberDatabase.editMember(index,member);
    }

    public String showLeaderBoard(int chosenTeam, Discipline discipline){
        return memberDatabase.showLeaderBoard(chosenTeam, discipline);
    }
}
