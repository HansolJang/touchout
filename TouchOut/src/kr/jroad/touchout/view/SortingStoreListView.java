package kr.jroad.touchout.view;

import com.nostra13.universalimageloader.core.ImageLoader;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.SortingStore;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SortingStoreListView extends FrameLayout {

	ImageView storeImgView;
	TextView storeNameTxtView;
	ImageView storeAvgStarView;
	TextView storeAvgStarTxtView;
	TextView distanceTxtView;
	TextView walkTimeTxtView;
	TextView workingTimeTxtView;
	String storeName;
	ImageLoader imgLoader;

	private float[] floatStar = new float[] { (float) 5.0, (float) 4.5,
			(float) 3.5, (float) 2.5, 0 };
	private int[] starResources = new int[] { R.drawable.avg_50,
			R.drawable.avg_45, R.drawable.avg_35, R.drawable.avg_25,
			R.drawable.avg_00 };

	public SortingStoreListView(Context context) {
		super(context);
		init();
	}

	public SortingStoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

		LayoutInflater.from(getContext()).inflate(
				R.layout.home_sorting_store_list_item, this);
		imgLoader = ImageLoader.getInstance();

		storeImgView = (ImageView) findViewById(R.id.sorting_store_img);
		storeNameTxtView = (TextView) findViewById(R.id.sorting_store_name_txt);
		storeAvgStarView = (ImageView) findViewById(R.id.sorting_store_star_img);
		storeAvgStarTxtView = (TextView) findViewById(R.id.sorting_store_avg_star_txt);
		distanceTxtView = (TextView) findViewById(R.id.sorting_store_distance_txt);
		walkTimeTxtView = (TextView) findViewById(R.id.sorting_store_walking_time_txt);
		workingTimeTxtView = (TextView) findViewById(R.id.sorting_store_working_time_txt);
	}

	public void setData(SortingStore data) {

		storeNameTxtView.setText(data.name);
		storeName = data.name;
		// 무조건 첫번째 이미지
		imgLoader.displayImage(data.image, storeImgView);
		storeAvgStarTxtView.setText(String.format("%.1f", data.star));
		setStar(data.star, 0);
		distanceTxtView.setText(String.format("%.1f", data.distance) + "km");
		// distance / 4km
		walkTimeTxtView.setText("도보 " + (int)((data.distance / 4) * 60) + "분");
		workingTimeTxtView.setText(data.hours + "");
	}

	//어떤 별사진 그릴건지 재귀함수로 
	private void setStar(float star, int index) {
		if(star < floatStar[index]) {
			setStar(star, index+1);
		} else {
			storeAvgStarView.setImageResource(starResources[index]);
		}
		
	}
}
