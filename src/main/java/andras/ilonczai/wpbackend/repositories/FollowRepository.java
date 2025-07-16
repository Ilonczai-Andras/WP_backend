package andras.ilonczai.wpbackend.repositories;

import andras.ilonczai.wpbackend.entities.Follow;
import andras.ilonczai.wpbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowed(User followed);
    boolean existsByFollowerAndFollowed(User follower, User followed);
    void deleteByFollowerAndFollowed(User follower, User followed);
}
