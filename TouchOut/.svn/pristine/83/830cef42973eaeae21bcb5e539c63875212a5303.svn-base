package kr.jroad.touchout.data;

import android.os.Parcel;
import android.os.Parcelable;

public class SortingRecentListData implements Parcelable {
	public String recentPlace;

	public SortingRecentListData() {
		
	}
	
	public SortingRecentListData(Parcel source){
		recentPlace = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(recentPlace);
	}
	
	public static Parcelable.Creator<SortingRecentListData> CREATOR = new Parcelable.Creator<SortingRecentListData>() {

		@Override
		public SortingRecentListData createFromParcel(Parcel source) {
			return new SortingRecentListData(source);
		}

		@Override
		public SortingRecentListData[] newArray(int size) {
			return new SortingRecentListData[size];
		}
	};
}
