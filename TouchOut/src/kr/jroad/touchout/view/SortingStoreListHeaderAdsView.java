package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SortingStoreListHeaderAdsView extends FrameLayout {
	
	ImageView adsView;

	public SortingStoreListHeaderAdsView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.home_all_store_header_ads, this);

		adsView = (ImageView) findViewById(R.id.all_store_ads_img);
		adsView.setImageResource(R.drawable.ad);
		
	}
}
