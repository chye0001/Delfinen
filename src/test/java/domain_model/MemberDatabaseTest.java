package domain_model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberDatabaseTest {
    MemberDatabase memberDatabase;

    @BeforeEach
    public void startUp() {
        File testerFile = new File("TestList.csv");
        memberDatabase = new MemberDatabase();
        memberDatabase.setAdministratorFile(testerFile);
    }

    @Test
    public void testLoadDatabase(){
        memberDatabase.loadMemberDatabase();

        String type = memberDatabase.getClubMembers().get(0).getType();
        String name = memberDatabase.getClubMembers().get(0).getName();
        LocalDate birthDate = memberDatabase.getClubMembers().get(0).getBirthDate();
        String email = memberDatabase.getClubMembers().get(0).getEmail();
        String discipline = memberDatabase.getClubMembers().get(0).getDiscipline();
        double subscriptionCost = memberDatabase.getClubMembers().get(0).getSubscriptionCost();

        assertAll(
    () -> assertEquals(1,memberDatabase.getClubMembers().size()),
                () -> assertEquals("Exercise",type),
                () -> assertEquals("Jane Test",name),
                () -> assertEquals("03/09/1999",birthDate),
                () -> assertEquals("test@email.dk",email),
                () -> assertEquals("Crawl",discipline),
                () -> assertEquals(299.99,subscriptionCost)
        );
    }

    @Test
    public void testAddMemberToDatabase(){
        int typeCode = 2; //equals "Exercise"
        String name = "Jane Test";
        LocalDate birthDate = LocalDate.parse("1999-09-03");
        String email = "test@email.dk";
        String discipline = "Crawl";
        double subscriptionCost = 299.99;

        memberDatabase.addMemberToList(typeCode,name,birthDate,email,discipline,subscriptionCost);

        String actualType = memberDatabase.getClubMembers().get(0).getType();
        String actualName = memberDatabase.getClubMembers().get(0).getName();
        LocalDate actualBirthDate = memberDatabase.getClubMembers().get(0).getBirthDate();
        String actualEmail = memberDatabase.getClubMembers().get(0).getEmail();
        String actualDiscipline = memberDatabase.getClubMembers().get(0).getDiscipline();
        double actualSubscriptionCost = memberDatabase.getClubMembers().get(0).getSubscriptionCost();

        assertAll(
                () -> assertEquals(1,memberDatabase.getClubMembers().size()),
                () -> assertEquals("Exercise",actualType),
                () -> assertEquals("Jane Test",actualName),
                () -> assertEquals("03/09/1999",actualBirthDate),
                () -> assertEquals("test@email.dk",actualEmail),
                () -> assertEquals("Crawl",actualDiscipline),
                () -> assertEquals(299.99,actualSubscriptionCost)
        );
    }

    @Test
    public void testShowListOfMembers(){
        int typeCode = 2; //equals "Exercise"
        String name = "Jane Test";
        LocalDate birthDate = LocalDate.parse("1999-09-03");
        String email = "test@email.dk";
        String discipline = "Crawl";
        double subscriptionCost = 299.99;

        memberDatabase.addMemberToList(typeCode,name,birthDate,email,discipline,subscriptionCost);

        String expectedString =
        "Name: Jane Test - Membership type: Exercise - " +
        "Date of birth: 03/09/1999 - Email address: test@email.dk\n";

        assertEquals(expectedString,memberDatabase.showListOfMembers());
    }


}