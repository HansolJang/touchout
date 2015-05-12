package kr.jroad.touchout.data;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentList implements Parcelable {
	public String message;
	public String store_name;
	public int total_amount;
	public int pay_type;
	public ArrayList<StoreMenuItem> items = new ArrayList<StoreMenuItem>();

	public PaymentList() {

	}

	public PaymentList(Parcel source) {
		message = source.readString();
		store_name = source.readString();
		total_amount = source.readInt();
		pay_type = source.readInt();
		source.readList(items, StoreMenuItem.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(message);
		dest.writeString(store_name);
		dest.writeInt(total_amount);
		dest.writeInt(pay_type);
		dest.writeList(items);
	}

	public static Parcelable.Creator<PaymentList> CREATOR = new Parcelable.Creator<PaymentList>() {

		@Override
		public PaymentList createFromParcel(Parcel source) {
			return new PaymentList(source);
		}

		@Override
		public PaymentList[] newArray(int size) {
			return new PaymentList[size];
		}
	};

}
