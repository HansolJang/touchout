package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StampItem implements Parcelable {
	public int userstamp_id;
	public int stamp_id;
	public int store_id;
	
	@SerializedName("store_name")
	public String storeName;
	
	@SerializedName("count")
	public int stampCount;
	
	public int is_used;
	public boolean isUsed;
	
	public String create_date;
	public String delete_date; //스탬프 만료 날짜
	public String reward;
	
	@SerializedName("description")
	public String stampDesc;
	
	//////////////////////////view variable
	
	//열개에 다 다른 이미지 줘야함
	public StampData[] stamp = new StampData[10];
	
	public StampItem() {

	}

	public StampItem (Parcel source) {
		stampCount = source.readInt();
		reward = source.readString();
		stampDesc = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeInt(stampCount);
		dest.writeString(reward);
		dest.writeString(stampDesc);
	}
	
	public static Parcelable.Creator<StampItem> CREATOR = new Parcelable.Creator<StampItem>(){

		@Override
		public StampItem createFromParcel(Parcel source) {
			return new StampItem(source);
		}

		@Override
		public StampItem[] newArray(int size) {
			return new StampItem[size];
		}
		
	};

}

