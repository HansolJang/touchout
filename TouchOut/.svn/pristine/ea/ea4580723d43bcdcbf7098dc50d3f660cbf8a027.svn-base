package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.NoticeItemGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class NoticeListGroupView extends FrameLayout {

	public NoticeListGroupView(Context context) {
		super(context);
		init();
	}
	
	TextView titleView;
	TextView timeView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.notice_group_item, this);
		
		titleView = (TextView)findViewById(R.id.notice_title_txt);
		timeView = (TextView)findViewById(R.id.notice_time_txt);
	}
	
	public void setData(NoticeItemGroup data) {
		titleView.setText(data.title);
		timeView.setText(data.time);
	}

}
