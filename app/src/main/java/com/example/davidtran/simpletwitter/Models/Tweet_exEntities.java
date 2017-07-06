package com.example.davidtran.simpletwitter.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davidtran on 7/6/17.
 */

public class Tweet_exEntities {
    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    @SerializedName("media")
    public List<Media> media;

    public static class Thumb {
        @SerializedName("w")
        public int w;
        @SerializedName("h")
        public int h;
        @SerializedName("resize")
        public String resize;
    }

    public static class Large {
        @SerializedName("w")
        public int w;
        @SerializedName("h")
        public int h;

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        @SerializedName("resize")
        public String resize;
    }

    public static class Small {
        @SerializedName("w")
        public int w;

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        @SerializedName("h")
        public int h;
        @SerializedName("resize")
        public String resize;
    }

    public static class Medium {
        @SerializedName("w")
        public int w;

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        @SerializedName("h")
        public int h;
        @SerializedName("resize")
        public String resize;
    }

    public static class Sizes {
        @SerializedName("thumb")
        public Thumb thumb;

        public Thumb getThumb() {
            return thumb;
        }

        public void setThumb(Thumb thumb) {
            this.thumb = thumb;
        }

        public Large getLarge() {
            return large;
        }

        public void setLarge(Large large) {
            this.large = large;
        }

        public Small getSmall() {
            return small;
        }

        public void setSmall(Small small) {
            this.small = small;
        }

        public Medium getMedium() {
            return medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

        @SerializedName("large")
        public Large large;
        @SerializedName("small")
        public Small small;
        @SerializedName("medium")
        public Medium medium;
    }

    public static class Media {
        @SerializedName("id")
        public String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        @SerializedName("id_str")
        public String id_str;
        @SerializedName("indices")
        public int[] indices;

        public String getMedia_url() {
            return media_url;
        }

        public void setMedia_url(String media_url) {
            this.media_url = media_url;
        }

        @SerializedName("media_url")
        public String media_url;
        @SerializedName("media_url_https")
        public String media_url_https;
        @SerializedName("url")
        public String url;
        @SerializedName("display_url")
        public String display_url;
        @SerializedName("expanded_url")
        public String expanded_url;
        @SerializedName("type")
        public String type;

        public Sizes getSizes() {
            return sizes;
        }

        public void setSizes(Sizes sizes) {
            this.sizes = sizes;
        }

        @SerializedName("sizes")
        public Sizes sizes;
    }


}
