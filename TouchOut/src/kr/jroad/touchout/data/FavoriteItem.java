package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FavoriteItem implements Parcelable{
	//favorite menu list data
	public int favorite_id;
	public int item_id;
	public int store_id;
	
	@SerializedName("store_name")
	public String storeName;
	
	@SerializedName("item_name")
	public String menuName;
	public String image;
	
	@SerializedName("amount")
	public int menuPrice;
	
	public int is_whipping;
	public boolean whippingSelected;
	
	public int is_hot;
	public boolean isHot;
	public String size;
	
	///////////////////////view variable
	
	public FavoriteItem(){
		
	}
	
	public FavoriteItem(Parcel source) {
		image = source.readString();
		storeName = source.readString();
		menuName = source.readString();
		menuPrice = source.readInt();
		store_id = source.readInt();
		favorite_id = source.readInt();
		item_id = source.readInt();
		is_whipping = source.readInt();
		size = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeString(storeName);
		dest.writeString(menuName);
		dest.writeInt(menuPrice);
		dest.writeInt(store_id);
		dest.writeInt(favorite_id);
		dest.writeInt(item_id);
		dest.writeInt(is_whipping);
		dest.writeString(size);
	}
	
	public static Parcelable.Creator<FavoriteItem> CREATOR = new Parcelable.Creator<FavoriteItem>() {

		@Override
		public FavoriteItem createFromParcel(Parcel source) {
			return new FavoriteItem(source);
		}

		@Override
		public FavoriteItem[] newArray(int size) {
			return new FavoriteItem[size];
		}
		
	};
	
}
