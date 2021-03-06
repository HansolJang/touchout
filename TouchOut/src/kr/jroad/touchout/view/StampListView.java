package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.StampAdapter;
import kr.jroad.touchout.data.StampData;
import kr.jroad.touchout.data.StampItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class StampListView extends FrameLayout {

	public static final int[] stampedIcon = new int[] { R.drawable.stamp1_on,
			R.drawable.stamp2_on, R.drawable.stamp3_on, R.drawable.stamp4_on,
			R.drawable.stamp5_on, R.drawable.stamp5_on, R.drawable.stamp6_on,
			R.drawable.stamp8_on, R.drawable.stamp9_on, R.drawable.stamp10_on };

	public static final int[] unstampedIcon = new int[] { R.drawable.stamp1_off,
		R.drawable.stamp2_off, R.drawable.stamp3_off, R.drawable.stamp4_off,
		R.drawable.stamp5_off, R.drawable.stamp5_off, R.drawable.stamp6_off,
		R.drawable.stamp8_off, R.drawable.stamp9_off, R.drawable.stamp10_off };

	public interface OnDeleteListener {
		public void onDeleteListener();
	}

	public interface GoStoreInfoListener {
		public void goStoreInfoListener();
	}

	OnDeleteListener deleteListener;
	GoStoreInfoListener infoListener;

	public void setOnDeleteListener(OnDeleteListener listener) {
		deleteListener = listener;
	}

	public void setGoStoreInfoListener(GoStoreInfoListener listener) {
		infoListener = listener;
	}

	public StampListView(Context context) {
		super(context);
		init();
	}

	TextView storeNameView;
	TextView stampDescView;
	GridView stampGridView;
	TextView stampRewardView;
	StampAdapter stampAdaper;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.stamp_list_item,
				this);
//		deleteView.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (deleteListener != null) {
//					deleteListener.onDeleteListener();
//				}
//			}
//		});

		storeNameView = (TextView) findViewById(R.id.dialog_stamp_store_name_txt);
		storeNameView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (infoListener != null) {
					infoListener.goStoreInfoListener();
				}
			}
		});

		stampDescView = (TextView) findViewById(R.id.stamp_desc_txt);
		stampRewardView = (TextView) findViewById(R.id.stamp_reward_txt);
		stampGridView = (GridView) findViewById(R.id.dialog_stamp_grid_view);
		stampAdaper = new StampAdapter(getContext());
		stampGridView.setAdapter(stampAdaper);
	}

	public void setData(StampItem data) {
		if(data.is_used == 0) {
			data.isUsed = false;
		} else {
			data.isUsed = true;
		}
		
		storeNameView.setText(data.storeName);
		stampDescView.setText(data.stampDesc);
		stampRewardView.setText("스탬프 10개 달성시 " + data.reward + " 한 잔 무료!");
		
		stampAdaper.clear();
		for(int i = 0; i < data.stamp.length; i++) {
			data.stamp[i] = new StampData();
			
			if(i < data.stampCount) {
				data.stamp[i].stamped = true;
				data.stamp[i].stampedId = stampedIcon[i];
			} else {
				data.stamp[i].stamped = false;
				data.stamp[i].unStampedId = unstampedIcon[i];
			}
			stampAdaper.add(data.stamp[i]);
		}
//		if(data.isUsed) {
//			deleteView.setVisibility(ImageView.VISIBLE);
//		} else {
//			deleteView.setVisibility(ImageView.GONE);
//		}
	}
}
