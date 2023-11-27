package domain_model;

import datasource.FileHandler;
import domain_model.members.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    MemberDatabase memberDatabase = new MemberDatabase();
    public Controller(){
        memberDatabase.loadMemberDatabase();
    }
    public boolean addMemberToList(int type,
                                   String name,
                                   String birthDate,
                                   String email,
                                   String discipline,
                                   double subscription) {

        return memberDatabase.addMemberToList(type, name, birthDate, email, discipline, subscription);
    }

    public String showListOfMembers () {
        return memberDatabase.showListOfMembers();
    }

    public String showListOfSubscriptions(){
        return memberDatabase.showListOfSubscription();
    }
    public double showIncomeForecast() {
        return memberDatabase.showIncomeForecast();
    }

    public ArrayList<Member> getJuniorTeam(){
        return memberDatabase.getJuniorTeam();
    }

    public ArrayList<Member> getSeniorTeam(){
        return memberDatabase.getSeniorTeam();
    }
}
