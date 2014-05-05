package com.muhammedabuali.app.data;

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

	public Comic(String userName, String profileUrl, String pictureUrl,
			String comicUrl, String imageUrl, String imageCaption) {
		this.userName = userName;
		this.profileUrl = profileUrl;
		this.pictureUrl = pictureUrl;
		this.comicUrl = comicUrl;
		this.imageUrl = imageUrl;
		this.imageCaption = imageCaption;
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
}
