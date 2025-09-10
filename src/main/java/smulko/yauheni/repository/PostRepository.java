package smulko.yauheni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smulko.yauheni.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts p WHERE p.content_tsvector @@ to_tsquery('russian', :query)", nativeQuery = true)
    List<Post> searchByContent(@Param("query") String query);

    @Query("SELECT DISTINCT p FROM Post p JOIN p.tags t WHERE t.name = :tagName")
    List<Post> findByTag(@Param("tagName") String tagName);
}
