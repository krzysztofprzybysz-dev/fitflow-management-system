package pl.maksp.fitflow.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maksp.fitflow.repository.MembershipRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MembershipTests {
    
    private MembershipRepository membershipRepository;
    
    @Test
    void createMembership() {
        // Test creating a membership
        Membership membership = Membership.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .build();
                
        assertNotNull(membership);
        // ...existing code...
    }
    
    // ...existing code...
}
