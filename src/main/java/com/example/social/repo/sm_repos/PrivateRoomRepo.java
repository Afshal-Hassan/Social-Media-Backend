package com.example.social.repo.sm_repos;


import com.example.social.entities.PrivateRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateRoomRepo extends JpaRepository<PrivateRooms,Integer> {

    List<PrivateRooms> findPrivateRoomsByUser1EmailOrUser2Email(String email,String email2);
}
