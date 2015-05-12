package kr.jroad.touchout.data;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderList implements Parcelable {
	public String message;
	public int order_id;
	public int store_id;
	public int total_amount;
	public int total_count;
	public int item_cnt;
	public int mileage;
	public ArrayList<OrderItem> items = new ArrayList<OrderItem>();
	
	public OrderList(){
		
	}
	
	public OrderList(Parcel source) {
		order_id = source.readInt();
		store_id = source.readInt();
		total_amount = source.readInt();
		total_count = source.readInt();
		item_cnt = source.readInt();
		mileage = source.readInt();
		source.readList(items, OrderItem.class.getClassLoader());
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(order_id);
		dest.writeInt(store_id);
		dest.writeInt(total_amount);
		dest.writeInt(total_count);	
		dest.writeInt(item_cnt);
		dest.writeInt(mileage);
		dest.writeList(items);
	}
	
	public static Parcelable.Creator<OrderList> CREATOR = new Parcelable.Creator<OrderList>() {

		@Override
		public OrderList createFromParcel(Parcel source) {
			return new OrderList(source);
		}

		@Override
		public OrderList[] newArray(int size) {
			return new OrderList[size];
		}
	};
}
