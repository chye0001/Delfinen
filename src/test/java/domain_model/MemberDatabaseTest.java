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

        memberDatabase.addMemberToList(MemberType.EXERCISE, "Jane Test", LocalDate.parse("1999-09-03"), "test@email.dk", "Crawl", 299.99);
        memberDatabase.loadMemberDatabase();

        MemberType type = memberDatabase.getClubMembers().get(0).getType();
        String name = memberDatabase.getClubMembers().get(0).getName();
        LocalDate birthDate = memberDatabase.getClubMembers().get(0).getBirthDate();
        String email = memberDatabase.getClubMembers().get(0).getEmail();
        String discipline = memberDatabase.getClubMembers().get(0).getDiscipline();
        double subscriptionCost = memberDatabase.getClubMembers().get(0).getSubscriptionCost();

        assertAll(
    () -> assertEquals(1,memberDatabase.getClubMembers().size()),
                () -> assertEquals(MemberType.EXERCISE,type),
                () -> assertEquals("Jane Test",name),
                () -> assertEquals(LocalDate.parse("1999-09-03"),birthDate),
                () -> assertEquals("test@email.dk",email),
                () -> assertEquals("Crawl",discipline),
                () -> assertEquals(299.99,subscriptionCost)
        );
    }

    @Test
    public void testAddMemberToDatabase(){
        MemberType type = MemberType.EXERCISE;
        String name = "Jane Test";
        LocalDate birthDate = LocalDate.parse("1999-09-03");
        String email = "test@email.dk";
        String discipline = "Crawl";
        double subscriptionCost = 299.99;

        memberDatabase.addMemberToList(type,name,birthDate,email,discipline,subscriptionCost);

        MemberType actualType = memberDatabase.getClubMembers().get(0).getType();
        String actualName = memberDatabase.getClubMembers().get(0).getName();
        LocalDate actualBirthDate = memberDatabase.getClubMembers().get(0).getBirthDate();
        String actualEmail = memberDatabase.getClubMembers().get(0).getEmail();
        String actualDiscipline = memberDatabase.getClubMembers().get(0).getDiscipline();
        double actualSubscriptionCost = memberDatabase.getClubMembers().get(0).getSubscriptionCost();

        assertAll(
                () -> assertEquals(1,memberDatabase.getClubMembers().size()),
                () -> assertEquals(MemberType.EXERCISE,actualType),
                () -> assertEquals("Jane Test",actualName),
                () -> assertEquals(LocalDate.parse("1999-09-03"),actualBirthDate),
                () -> assertEquals("test@email.dk",actualEmail),
                () -> assertEquals("Crawl",actualDiscipline),
                () -> assertEquals(299.99,actualSubscriptionCost)
        );
    }

    @Test
    public void testShowListOfMembers(){
        MemberType type = MemberType.EXERCISE;
        String name = "Jane Test";
        LocalDate birthDate = LocalDate.parse("1999-09-03");
        String email = "test@email.dk";
        String discipline = "Crawl";
        double subscriptionCost = 299.99;

        memberDatabase.addMemberToList(type,name,birthDate,email,discipline,subscriptionCost);

        String expectedString =
        "Name: Jane Test - Membership type: EXERCISE - " +
        "Date of birth: 1999-09-03 - Email address: test@email.dk\n";

        assertEquals(expectedString,memberDatabase.showListOfMembers());
    }

    @Test
    void show_One_Year_Income_Forecast(){
        memberDatabase.addMemberToList(MemberType.PASSIVE, "Test", LocalDate.parse("2001-02-23"), "test@test.dk", "none", 100.50);
        memberDatabase.addMemberToList(MemberType.EXERCISE, "Test2", LocalDate.parse("2002-02-23"), "test2@test2.dk", "none", 200.40);

        double expectedOneYearIncome = memberDatabase.getClubMembers().get(0).getSubscriptionCost() +
                memberDatabase.getClubMembers().get(1).getSubscriptionCost();
        double actualOneYearIncome = memberDatabase.showIncomeForecast();

        assertEquals(expectedOneYearIncome, actualOneYearIncome);
    }

    @Test
    void showListOfSubscription() {
        MemberType type = MemberType.PASSIVE;
        String name = "PersonTest";
        LocalDate birthDate = LocalDate.parse("1999-09-03");
        String email = "test@email.dk";
        String discipline = "Crawl";
        double subscriptionCost = 299.99;

        memberDatabase.addMemberToList(type , name, birthDate, email, discipline, subscriptionCost);
        String expectedSubscriptionList = "Name: PersonTest / Age: 24 / Activity type: PASSIVE / Subscription: Subscription: , Price: 299.99, Last payment date: 2023-11-29, Next payment date: 2024-11-29, Payment status: Paid, Debt: 0.0\n";
        String actualSubscriptionList = memberDatabase.showListOfSubscription();

        assertEquals(expectedSubscriptionList, actualSubscriptionList);
    }
}