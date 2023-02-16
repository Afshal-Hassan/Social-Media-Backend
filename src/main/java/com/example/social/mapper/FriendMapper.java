package com.example.social.mapper;
import com.example.social.dto.FriendsData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;


@Component
public class FriendMapper {

    @Autowired
    private ModelMapper mapper;

    public List<FriendsData> mapToFriendsData(List<Tuple> friends) {
        List<FriendsData> friendsDataList = new ArrayList<>();

        for (var index = 0; index < friends.size(); index++) {

            FriendsData friendsData = new FriendsData();
            friendsData.setId(friends.get(index).get(0,Integer.class));
            friendsData.setName(friends.get(index).get(1, String.class));
            friendsData.setEmail(friends.get(index).get(2, String.class));
            friendsData.setCountry(friends.get(index).get(3,String.class));
            friendsData.setPhoneNo(friends.get(index).get(4, String.class));
            friendsData.setProfilePic(friends.get(index).get(5, String.class));
            friendsData.setBackgroundImage(friends.get(index).get(6, String.class));

            friendsDataList.add(friendsData);
        }
        return friendsDataList;
    }

}
