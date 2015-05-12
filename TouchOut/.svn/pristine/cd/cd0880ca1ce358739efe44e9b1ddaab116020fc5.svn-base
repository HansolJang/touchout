package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable{
	public int item_id;
	public String item_name;
	public int count;
	public int is_whipping;
	public String size;
	public int amount;
	
	public OrderItem() {
		
	}
	
	public OrderItem(Parcel source) {
		item_id = source.readInt();
		item_name = source.readString();
		count = source.readInt();
		is_whipping = source.readInt();
		size = source.readString();
		amount = source.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(item_id);
		dest.writeString(item_name);
		dest.writeInt(count);
		dest.writeInt(is_whipping);
		dest.writeString(size);
		dest.writeInt(amount);	
	}
	
	public static Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {

		@Override
		public OrderItem createFromParcel(Parcel source) {
			return new OrderItem(source);
		}

		@Override
		public OrderItem[] newArray(int size) {
			return new OrderItem[size];
		}
		
	};

}
