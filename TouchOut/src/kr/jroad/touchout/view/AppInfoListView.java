package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AppInfoListView extends FrameLayout {

	public AppInfoListView(Context context) {
		super(context);
		init();
	}
	
	TextView titleView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.app_info_list_item, this);
		
		titleView = (TextView)findViewById(R.id.app_info_title_txt);
	}
	
	public void setData(String data) {
		titleView.setText(data);
	}
	
}
