package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeCategoryListItemData implements Parcelable {
	public int resId;
	public String category;

	public HomeCategoryListItemData() {

	}

	public HomeCategoryListItemData(Parcel source) {
		resId = source.readInt();
		category = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(resId);
		dest.writeString(category);
	}

	public static Parcelable.Creator<HomeCategoryListItemData> CREATOR = new Parcelable.Creator<HomeCategoryListItemData>() {
		@Override
		public HomeCategoryListItemData createFromParcel(Parcel source) {
			return new HomeCategoryListItemData(source);
		}

		@Override
		public HomeCategoryListItemData[] newArray(int size) {
			return new HomeCategoryListItemData[size];
		};
	};
}
