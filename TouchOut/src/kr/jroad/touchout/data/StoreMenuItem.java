package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StoreMenuItem implements Parcelable{
	public int item_id;
	public int store_id;
	
	@SerializedName("name")
	public String menuName;
	
	@SerializedName("amount")
	public int menuPrice;
	
	public String image;
	
	
	public int is_whipping;
	public boolean whippingEnabled;

	public int is_hot;
	public boolean isHot;
	
	public String size;
	
	/////////////////////////view variable
	public boolean whippingSelected = false;
	public boolean menuSelected;
	public int count = 1;
	public String selectedSize;
	public boolean isFavorite = false;
	public int oneRegularPrice;
	public int oneLargePrice;
	
	public StoreMenuItem() {
		
	}
	
	//payment finish 화면에서 띄워줄 품목 이름만 파슬러블로 전달
	public StoreMenuItem(Parcel source) {
		menuName = source.readString();
		item_id = source.readInt();
		size = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(menuName);
		dest.writeInt(item_id);
		dest.writeString(size);
	}
	
	public static Parcelable.Creator<StoreMenuItem> CREATOR = new Parcelable.Creator<StoreMenuItem>() {

		@Override
		public StoreMenuItem createFromParcel(Parcel source) {
			return new StoreMenuItem(source);
		}

		@Override
		public StoreMenuItem[] newArray(int size) {
			return new StoreMenuItem[size];
		}
	};
	
}
