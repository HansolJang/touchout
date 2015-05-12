package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.StampData;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class StampView extends FrameLayout {

	public StampView(Context context) {
		super(context);
		init();
	}

	ImageView stampView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.stamp_item, this);
		
		stampView = (ImageView)findViewById(R.id.stamp_img);
	}
	
	public void setData(StampData data) {
		if(data.stamped) {
			stampView.setImageResource(data.stampedId);
		}else {
			stampView.setImageResource(data.unStampedId);
		}
	}
}
