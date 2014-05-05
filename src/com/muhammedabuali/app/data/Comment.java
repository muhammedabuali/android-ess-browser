package com.muhammedabuali.app.data;

public class Comment {
	private String pp;
	private String pname;
	private String text;
	private String likes;
	private String lames;
	
	public Comment( String pp, String pname, 
			String text, String likes, String lames){
		this.pp = pp;
		this.pname = pname;
		this.text = text;
		this.likes = likes;
		this.lames = lames;
	}
	
	@Override
	public String toString(){
		return "pp: " + pp +","
				+"pname: " + pname +","
				+"text: " + text +","
				+"likes: " + likes +","
				+"lames: " + lames ;
	}

	public String getText() {
		return text;
	}

	public String getUserName() {
		return pname;
	}

	public String getProfilePic() {
		return pp;
	}
	
}
