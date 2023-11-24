package domain_model;

import datasource.FileHandler;

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
                                   double subscription) throws FileNotFoundException {

        return memberDatabase.addMemberToList(type, name, birthDate, email, discipline, subscription);
    }
}
