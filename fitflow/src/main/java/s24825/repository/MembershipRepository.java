package s24825.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24825.model.membership.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
}