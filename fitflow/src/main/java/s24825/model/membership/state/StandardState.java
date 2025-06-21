package s24825.model.membership.state;

import s24825.model.membership.Membership;

public class StandardState implements MembershipState {

    private static final int STANDARD_ENTRY_LIMIT = 20;

    @Override
    public boolean canBook(Membership context) {
        // A standard membership is valid if it's active and the entry limit has not been reached.
        return context.isActive() && context.getEntriesUsed() < STANDARD_ENTRY_LIMIT;
    }

    @Override
    public String getTypeName() {
        return "Standard";
    }

    @Override
    public void handleBooking(Membership context) {
        if (canBook(context)) {
            context.setEntriesUsed(context.getEntriesUsed() + 1);
        }
    }
}