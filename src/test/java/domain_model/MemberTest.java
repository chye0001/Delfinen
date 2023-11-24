package domain_model;

import domain_model.Members.JuniorMember;
import domain_model.Members.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    public void createJuniorMember(){
        String name = "John Test";
        String birthDate = "24/11/2023";
        String email = "test@test.test";
        String discipline = "Test";
        double subscriptionCost = 400.0;
        Member testMember = new JuniorMember(name,birthDate,email,discipline,subscriptionCost);

        assertAll(
                () -> assertEquals(testMember.getName(),name),
                () -> assertEquals(testMember.getBirthDate(),birthDate),
                () -> assertEquals(testMember.getEmail(),email),
                () -> assertEquals(testMember.getDiscipline(),discipline),
                () -> assertEquals(testMember.getSubscriptionCost(),subscriptionCost)
        );
    }

}