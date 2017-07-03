package com.example.davidtran.simpletwitter.Utils;

import com.example.davidtran.simpletwitter.Models.OwnerUser;
import com.example.davidtran.simpletwitter.Models.Tweet;
import com.example.davidtran.simpletwitter.Models.User;
import com.google.gson.JsonObject;


/**
 * Created by davidtran on 7/1/17.
 */

public class mJsonParser {
    public static User fromJsonObjectToUser(JsonObject jsonUserObject){
        User user = new User();
        if(jsonUserObject.has("id_str"))
        {
            user.setIdStr(jsonUserObject.get("id_str").getAsString());
        }
        if(jsonUserObject.has("id")){
            user.setId(jsonUserObject.get("id").getAsInt());
        }
        if(jsonUserObject.has("name")){
            user.setName(jsonUserObject.get("name").getAsString());
        }
        if(jsonUserObject.has("screen_name")){
            user.setScreenName("@"+jsonUserObject.get("screen_name").getAsString());
        }

        if(jsonUserObject.has("profile_image_url")){
            user.setProfileImageUrl(jsonUserObject.get("profile_image_url").getAsString());
        }
        return user;

    }
    public static Tweet fromJsonObjectToTweet(JsonObject jsonTweetObject){
        Tweet tweet = new Tweet();
        if (jsonTweetObject.has("created_at")){
            tweet.setCreatedAt(jsonTweetObject.get("created_at").getAsString());
        }
        if (jsonTweetObject.has("id")){
            tweet.setId(jsonTweetObject.get("id").getAsInt());
        }
        if (jsonTweetObject.has("id_str")){
            tweet.setIdStr(jsonTweetObject.get("id_str").getAsString());
        }
        if (jsonTweetObject.has("text")){
            tweet.setText(jsonTweetObject.get("text").getAsString());
        }
        if(jsonTweetObject.has("user")){
            tweet.setUser(mJsonParser.fromJsonObjectToUser(jsonTweetObject.get("user").getAsJsonObject()));
        }
        return tweet;
    }
    public static OwnerUser fromJsonObjectToOwnerUser(JsonObject jsonOwnerUserObject){
        OwnerUser ownerUser = new OwnerUser();
        if(jsonOwnerUserObject.has("name")){
            ownerUser.setName(jsonOwnerUserObject.get("name").getAsString());
        }
        if(jsonOwnerUserObject.has("screen_name")){
            ownerUser.setScreenName("@"+jsonOwnerUserObject.get("screen_name").getAsString());
        }
        if(jsonOwnerUserObject.has("profile_image_url")){
            ownerUser.setProfileImageUrl(jsonOwnerUserObject.get("profile_image_url").getAsString());
        }
        return ownerUser;
    }
}
