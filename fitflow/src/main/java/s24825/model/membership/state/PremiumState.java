package s24825.model.membership.state;

import s24825.model.membership.Membership;

public class PremiumState implements MembershipState {

    @Override
    public boolean canBook(Membership context) {
        return context.isActive();
    }

    @Override
    public String getTypeName() {
        return "Premium";
    }

    @Override
    public void handleBooking(Membership context) {
        // No usage to track for premium, so this method does nothing.
    }
}