package com.muhammedabuali.app.data;

import com.muhammedabuali.app.Downloader;

/**
 * Created by mohamed on 4/16/14.
 */
public class Comic {
    // user data
    String userName;
    String profileUrl;
    String pictureUrl;
    // comic image
    String imageUrl;
    String imageCaption;
    String comicUrl;
    String likes;
    String rankUrl;

    public Comic(String userName, String profileUrl, String pictureUrl,
                 String comicUrl, String imageUrl, String imageCaption) {
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.pictureUrl = pictureUrl;
        this.comicUrl = comicUrl;
        this.imageUrl = imageUrl;
        this.imageCaption = imageCaption;
    }

    public Comic(String userName, String profileUrl, String pictureUrl, String comicUrl, String imageUrl,
                 String imageCaption, String likes, String rankUrl) {
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.pictureUrl = pictureUrl;
        this.comicUrl = comicUrl;
        this.imageUrl = imageUrl;
        this.imageCaption = imageCaption;
        this.likes = likes;
        this.rankUrl = Downloader.baseUrl + rankUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfilePic() {
        return pictureUrl;
    }

    public String getCaption() {
        return imageCaption;
    }

    public String getComicUrl() {
        return comicUrl;
    }

    public String getLikes() {
        return likes;
    }

    public String getRankUrl() {
        return rankUrl;
    }
}
