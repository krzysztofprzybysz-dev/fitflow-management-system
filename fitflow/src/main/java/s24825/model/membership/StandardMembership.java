package s24825.model.membership;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "standard_memberships")
@Getter
@Setter
@NoArgsConstructor
public class StandardMembership extends Membership {

    private int entryLimit;
    private int entriesUsed;

    @Override
    public boolean canBook() {
        return isActive() &&
                getExpirationDate().isAfter(LocalDate.now()) &&
                entriesUsed < entryLimit;
    }
}
