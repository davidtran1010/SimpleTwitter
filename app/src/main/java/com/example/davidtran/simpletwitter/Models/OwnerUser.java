package com.example.davidtran.simpletwitter.Models;

/**
 * Created by davidtran on 7/3/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerUser implements Parcelable {

    @SerializedName("contributors_enabled")
    @Expose
    private Boolean contributorsEnabled;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("default_profile")
    @Expose
    private Boolean defaultProfile;
    @SerializedName("default_profile_image")
    @Expose
    private Boolean defaultProfileImage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("favourites_count")
    @Expose
    private Integer favouritesCount;

    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;

    @SerializedName("friends_count")
    @Expose
    private Integer friendsCount;
    @SerializedName("geo_enabled")
    @Expose
    private Boolean geoEnabled;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("is_translator")
    @Expose
    private Boolean isTranslator;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("listed_count")
    @Expose
    private Integer listedCount;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("profile_background_color")
    @Expose
    private String profileBackgroundColor;
    @SerializedName("profile_background_image_url")
    @Expose
    private String profileBackgroundImageUrl;
    @SerializedName("profile_background_image_url_https")
    @Expose
    private String profileBackgroundImageUrlHttps;
    @SerializedName("profile_background_tile")
    @Expose
    private Boolean profileBackgroundTile;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("profile_image_url_https")
    @Expose
    private String profileImageUrlHttps;
    @SerializedName("profile_link_color")
    @Expose
    private String profileLinkColor;
    @SerializedName("profile_sidebar_border_color")
    @Expose
    private String profileSidebarBorderColor;
    @SerializedName("profile_sidebar_fill_color")
    @Expose
    private String profileSidebarFillColor;
    @SerializedName("profile_text_color")
    @Expose
    private String profileTextColor;
    @SerializedName("profile_use_background_image")
    @Expose
    private Boolean profileUseBackgroundImage;
    @SerializedName("protected")
    @Expose
    private Boolean _protected;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("show_all_inline_media")
    @Expose
    private Boolean showAllInlineMedia;
    /*@SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("statuses_count")*/
    @Expose
    private Integer statusesCount;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;

    @SerializedName("utc_offset")
    @Expose
    private Integer utcOffset;
    @SerializedName("verified")
    @Expose
    private Boolean verified;

    public Boolean getContributorsEnabled() {
        return contributorsEnabled;
    }

    public void setContributorsEnabled(Boolean contributorsEnabled) {
        this.contributorsEnabled = contributorsEnabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(Boolean defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public Boolean getDefaultProfileImage() {
        return defaultProfileImage;
    }

    public void setDefaultProfileImage(Boolean defaultProfileImage) {
        this.defaultProfileImage = defaultProfileImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(Integer favouritesCount) {
        this.favouritesCount = favouritesCount;
    }


    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }




    public Integer getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Integer friendsCount) {
        this.friendsCount = friendsCount;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(Boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public Boolean getIsTranslator() {
        return isTranslator;
    }

    public void setIsTranslator(Boolean isTranslator) {
        this.isTranslator = isTranslator;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getListedCount() {
        return listedCount;
    }

    public void setListedCount(Integer listedCount) {
        this.listedCount = listedCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    public void setProfileBackgroundColor(String profileBackgroundColor) {
        this.profileBackgroundColor = profileBackgroundColor;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return profileBackgroundImageUrlHttps;
    }

    public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps) {
        this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
    }

    public Boolean getProfileBackgroundTile() {
        return profileBackgroundTile;
    }

    public void setProfileBackgroundTile(Boolean profileBackgroundTile) {
        this.profileBackgroundTile = profileBackgroundTile;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    public void setProfileLinkColor(String profileLinkColor) {
        this.profileLinkColor = profileLinkColor;
    }

    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
        this.profileSidebarBorderColor = profileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    public void setProfileSidebarFillColor(String profileSidebarFillColor) {
        this.profileSidebarFillColor = profileSidebarFillColor;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    public Boolean getProfileUseBackgroundImage() {
        return profileUseBackgroundImage;
    }

    public void setProfileUseBackgroundImage(Boolean profileUseBackgroundImage) {
        this.profileUseBackgroundImage = profileUseBackgroundImage;
    }

    public Boolean getProtected() {
        return _protected;
    }

    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Boolean getShowAllInlineMedia() {
        return showAllInlineMedia;
    }

    public void setShowAllInlineMedia(Boolean showAllInlineMedia) {
        this.showAllInlineMedia = showAllInlineMedia;
    }

    /*public Status getStatus() {
        return status;
    }*/

    /* public void setStatus(Status status) {
         this.status = status;
     }
 */
    public Integer getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(Integer statusesCount) {
        this.statusesCount = statusesCount;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }




    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.contributorsEnabled);
        dest.writeString(this.createdAt);
        dest.writeValue(this.defaultProfile);
        dest.writeValue(this.defaultProfileImage);
        dest.writeString(this.description);
        dest.writeValue(this.favouritesCount);
        dest.writeValue(this.followersCount);
        dest.writeValue(this.friendsCount);
        dest.writeValue(this.geoEnabled);
        dest.writeValue(this.id);
        dest.writeString(this.idStr);
        dest.writeValue(this.isTranslator);
        dest.writeString(this.lang);
        dest.writeValue(this.listedCount);
        dest.writeString(this.location);
        dest.writeString(this.name);
        dest.writeString(this.profileBackgroundColor);
        dest.writeString(this.profileBackgroundImageUrl);
        dest.writeString(this.profileBackgroundImageUrlHttps);
        dest.writeValue(this.profileBackgroundTile);
        dest.writeString(this.profileImageUrl);
        dest.writeString(this.profileImageUrlHttps);
        dest.writeString(this.profileLinkColor);
        dest.writeString(this.profileSidebarBorderColor);
        dest.writeString(this.profileSidebarFillColor);
        dest.writeString(this.profileTextColor);
        dest.writeValue(this.profileUseBackgroundImage);
        dest.writeValue(this._protected);
        dest.writeString(this.screenName);
        dest.writeValue(this.showAllInlineMedia);
        dest.writeValue(this.statusesCount);
        dest.writeString(this.timeZone);
        dest.writeValue(this.utcOffset);
        dest.writeValue(this.verified);
    }

    public OwnerUser() {
    }

    protected OwnerUser(Parcel in) {
        this.contributorsEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createdAt = in.readString();
        this.defaultProfile = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.defaultProfileImage = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.description = in.readString();
        this.favouritesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.followersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.friendsCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.geoEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idStr = in.readString();
        this.isTranslator = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.lang = in.readString();
        this.listedCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.location = in.readString();
        this.name = in.readString();
        this.profileBackgroundColor = in.readString();
        this.profileBackgroundImageUrl = in.readString();
        this.profileBackgroundImageUrlHttps = in.readString();
        this.profileBackgroundTile = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.profileImageUrl = in.readString();
        this.profileImageUrlHttps = in.readString();
        this.profileLinkColor = in.readString();
        this.profileSidebarBorderColor = in.readString();
        this.profileSidebarFillColor = in.readString();
        this.profileTextColor = in.readString();
        this.profileUseBackgroundImage = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this._protected = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.screenName = in.readString();
        this.showAllInlineMedia = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.statusesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.timeZone = in.readString();
        this.utcOffset = (Integer) in.readValue(Integer.class.getClassLoader());
        this.verified = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<OwnerUser> CREATOR = new Parcelable.Creator<OwnerUser>() {
        @Override
        public OwnerUser createFromParcel(Parcel source) {
            return new OwnerUser(source);
        }

        @Override
        public OwnerUser[] newArray(int size) {
            return new OwnerUser[size];
        }
    };
}

