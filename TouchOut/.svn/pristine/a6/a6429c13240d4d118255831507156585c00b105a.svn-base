package kr.jroad.touchout.data;

import com.google.gson.annotations.SerializedName;

public class ReviewItem {
	public int review_id;
	public int user_id;
	
	public int is_mine;  //리뷰가 로그인한 사용자가 쓴것인지
	
	@SerializedName("user_name")
	public String name;
	
	@SerializedName("image")
	public String profileURL; //리뷰올린 사람의 프로필 사진
	
	public int star; //avg만 float
	public String content;
	
	@SerializedName("create_time")
	public String time;

}
