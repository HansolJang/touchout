package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NowSaleItem implements Parcelable {
	public int store_id;
	
	@SerializedName("store_name")
    public String storeName;
	public String description;
    public float latitude;
    public float longitude;
    public float distance;
    public int item_id;
    public String item_name;
    
    public int amount;
    
    @SerializedName("image")
    public String saleImgId;
    
    public int is_hot;
    public boolean isHot;
    public String size;
    
    public NowSaleItem () {
    	
    }
    
    public NowSaleItem(Parcel source) {
    	store_id = source.readInt();
    	storeName = source.readString();
    	latitude = source.readFloat();
    	longitude = source.readFloat();
    	distance = source.readFloat();
    	item_id = source.readInt();
    	item_name = source.readString();
    	amount = source.readInt();
    	saleImgId = source.readString();
    	is_hot = source.readInt();
    	size = source.readString();
    	description = source.readString();
    }
    
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(store_id);
		dest.writeString(storeName);
		dest.writeFloat(latitude);
		dest.writeFloat(longitude);
		dest.writeFloat(distance);
		dest.writeInt(item_id);
		dest.writeString(item_name);
		dest.writeInt(amount);
		dest.writeString(saleImgId);
		dest.writeInt(is_hot);
		dest.writeString(size);
		dest.writeString(description);
	}
	
	public static Parcelable.Creator<NowSaleItem> CREATOR = new Parcelable.Creator<NowSaleItem>() {

		@Override
		public NowSaleItem createFromParcel(Parcel source) {
			return new NowSaleItem(source);
		}

		@Override
		public NowSaleItem[] newArray(int size) {
			return new NowSaleItem[size];
		}
		
	};

}
