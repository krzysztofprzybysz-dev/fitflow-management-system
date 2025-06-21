package s24825.model.membership.state;

import s24825.model.membership.Membership;

/**
 * Interface representing the state of a Membership, according to the State design pattern.
 * It allows a membership's behavior to change when its state changes.
 */
public interface MembershipState {


    boolean canBook(Membership context);

    String getTypeName();

    void handleBooking(Membership context);
}