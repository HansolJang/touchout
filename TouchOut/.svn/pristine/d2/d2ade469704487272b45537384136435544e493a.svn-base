package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

public class SortingStore implements Parcelable {
	public int store_id;
	public String name;
	public String description;
	public String hours;
	public String address;
	public String phone;
	public float star; // avg
	public float distance;
	public float latitude;
	public float longitude;
	public String image; // 대표 이미지 하나

	public SortingStore() {

	}

	public SortingStore(Parcel source) {
		store_id = source.readInt();
		name = source.readString();
		description = source.readString();
		hours = source.readString();
		address = source.readString();
		phone = source.readString();
		star = source.readFloat();
		distance = source.readFloat();
		latitude = source.readFloat();
		longitude = source.readFloat();
		image = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(store_id);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(hours);
		dest.writeString(address);
		dest.writeString(phone);
		dest.writeFloat(star);
		dest.writeFloat(distance);
		dest.writeFloat(latitude);
		dest.writeFloat(longitude);
		dest.writeString(image);
	}

	public static Parcelable.Creator<SortingStore> CREATOR = new Parcelable.Creator<SortingStore>() {

		@Override
		public SortingStore createFromParcel(Parcel source) {
			return new SortingStore(source);
		}

		@Override
		public SortingStore[] newArray(int size) {
			return new SortingStore[size];
		}
	};

}
