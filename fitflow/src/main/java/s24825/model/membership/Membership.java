package s24825.model.membership;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import s24825.model.membership.state.ExpiredState;
import s24825.model.membership.state.MembershipState;
import s24825.model.membership.state.PremiumState;
import s24825.model.membership.state.StandardState;
import s24825.model.person.Member;

import java.time.LocalDate;

@Entity
@Table(name = "memberships")
@Getter
@Setter
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate purchaseDate;
    private LocalDate expirationDate;
    private String membershipType;
    private int entriesUsed;

    @Transient
    private MembershipState currentState;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;



    public boolean canBook() {
        if (this.currentState == null) {
            initializeState();
        }
        return this.currentState.canBook(this);
    }

    public void handleBooking() {
        if (this.currentState == null) {
            initializeState();
        }
        this.currentState.handleBooking(this);
    }

    public String getTypeName() {
        if (this.currentState == null) {
            initializeState();
        }
        return this.currentState.getTypeName();
    }


    @PostLoad
    public void initializeState() {
        if (!isActive()) {
            this.currentState = new ExpiredState();
            return;
        }

        if ("PREMIUM".equalsIgnoreCase(this.membershipType)) {
            this.currentState = new PremiumState();
        } else {
            this.currentState = new StandardState();
        }
    }


    public void upgradeToPremium() {
        this.setMembershipType("PREMIUM");
        this.setEntriesUsed(0);
        this.initializeState();
    }

    public boolean isActive() {
        return getExpirationDate() != null && !LocalDate.now().isAfter(getExpirationDate());
    }
}