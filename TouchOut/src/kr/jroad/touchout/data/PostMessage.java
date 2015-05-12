package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PostMessage implements Parcelable{
	public String message;
	
	//장바구니 수정삭제 시 받을 장바구니 내의 아이템 개수
	public int item_cnt;
	
	/*로그인 상태 - 성공(자동로그인 = isloggedin =1, isnew=0), 등록(이용약관 , isloggedin = 1, isnew = 1)
	* 실패(이동x => isloggedin = 0)
	*/
	public int is_loggedin;
	public int is_new;
	
	
	/*
	 *  다른 매장의 상품을 장바구니에 넣었을때 원래와 같은 매장인지 알려주는 변수
	 *  이것만 다이얼 로그로 전달
	 */
	public int is_same_store;
	
	public PostMessage() {
		
	}
	
	public PostMessage(Parcel source) {
		is_same_store = source.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(is_same_store);
	}
	
	public static final Parcelable.Creator<PostMessage> CREATOR = new Parcelable.Creator<PostMessage>() {

		@Override
		public PostMessage createFromParcel(Parcel source) {
			return new PostMessage(source);
		}
		
		@Override
		public PostMessage[] newArray(int size) {
			return new PostMessage[size];
		}
		
	};
}
