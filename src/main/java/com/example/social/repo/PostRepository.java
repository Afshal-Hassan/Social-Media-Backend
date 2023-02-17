package com.example.social.repo;

import com.example.social.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostsByUserEmail(String email);

    @Query(value = "select post_image from posts where post_id = 9",nativeQuery = true)
    byte[] findImage();

    Post findByPostId(Integer id);
}
