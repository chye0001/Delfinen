package domain_model;

import domain_model.members.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    public void createExerciseMember(){
        String type = "Exercise";
        String name = "Arny Test";
        LocalDate birthDate = LocalDate.parse("2023-11-24");
        String email = "test@test.test";
        String discipline = "Test";
        double subscriptionCost = 400.0;
        Member testMember = new ExerciseMember(name,birthDate,email,discipline,subscriptionCost);

        assertAll(
                () -> assertEquals(testMember.getType(),type),
                () -> assertEquals(testMember.getName(),name),
                () -> assertEquals(testMember.getBirthDate(),birthDate),
                () -> assertEquals(testMember.getEmail(),email),
                () -> assertEquals(testMember.getDiscipline(),discipline),
                () -> assertEquals(testMember.getSubscriptionCost(),subscriptionCost)
        );
    }

    @Test
    public void createCompetitiveMember(){
        String type = "Competitive";
        String name = "Mary Test";
        LocalDate birthDate = LocalDate.parse("2023-11-24");
        String email = "test@test.test";
        String discipline = "Test";
        double subscriptionCost = 400.0;
        Member testMember = new CompetitiveMember(name,birthDate,email,discipline,subscriptionCost);

        assertAll(
                () -> assertEquals(testMember.getType(),type),
                () -> assertEquals(testMember.getName(),name),
                () -> assertEquals(testMember.getBirthDate(),birthDate),
                () -> assertEquals(testMember.getEmail(),email),
                () -> assertEquals(testMember.getDiscipline(),discipline),
                () -> assertEquals(testMember.getSubscriptionCost(),subscriptionCost)
        );
    }

    @Test
    public void createPassiveMember(){
        String type = "Passive";
        String name = "Siri Test";
        LocalDate birthDate = LocalDate.parse("2023-11-24");
        String email = "test@test.test";
        String discipline = "Test";
        double subscriptionCost = 400.0;
        Member testMember = new PassiveMember(name,birthDate,email,discipline,subscriptionCost);

        assertAll(
                () -> assertEquals(testMember.getType(),type),
                () -> assertEquals(testMember.getName(),name),
                () -> assertEquals(testMember.getBirthDate(),birthDate),
                () -> assertEquals(testMember.getEmail(),email),
                () -> assertEquals(testMember.getDiscipline(),discipline),
                () -> assertEquals(testMember.getSubscriptionCost(),subscriptionCost)
        );
    }

}