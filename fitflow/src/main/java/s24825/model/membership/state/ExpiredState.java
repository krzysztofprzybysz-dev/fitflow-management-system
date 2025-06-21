package s24825.model.membership.state;

import s24825.model.membership.Membership;

public class ExpiredState implements MembershipState {

    @Override
    public boolean canBook(Membership context) {
        return false;
    }

    @Override
    public String getTypeName() {
        return "Expired";
    }

    @Override
    public void handleBooking(Membership context) {
    }
}