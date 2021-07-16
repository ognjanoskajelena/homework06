package mk.ukim.finki.hci.homework06.repository;

import mk.ukim.finki.hci.homework06.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
