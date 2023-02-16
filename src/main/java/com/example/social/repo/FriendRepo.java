package com.example.social.repo;

import com.example.social.entities.Friends;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FriendRepo extends JpaRepository<Friends,Integer> {

    List<Friends> findByUserEmail(String email);

    @Query(value = "select count(u.user_id) from friends f inner join user u on f.user_id = u.user_id where email=:email ",nativeQuery = true)
    Tuple findCountsOfFriendOfUser(@Param("email")String email);

    @Query(value = "select f.friend_id from friends f inner join user u on f.user_id = u.user_id where f.user_id = (select u.user_id from user u where u.email =:byUserEmail) and f.user_friend_id = (select u.user_id from user u where u.email =:onUserEmail) ",nativeQuery = true)
    Tuple findFriendOfUser(@Param("byUserEmail")String byUser,@Param("onUserEmail")String onUser);

    @Query(value = "SELECT usf.user_id, usf.name, usf.email, usf.country, usf.phone_no, usf.profile_pic, usf.background_image FROM friends f inner join user u on f.user_id = u.user_id inner join user usf on f.user_friend_id =usf.user_id where u.email=:email ",nativeQuery = true)
    List<Tuple> findFriendsDataByUserEmail(@Param("email")String email);
}
