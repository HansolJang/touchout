package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.NoticeItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class NoticeListChildView extends FrameLayout {

	public NoticeListChildView(Context context) {
		super(context);
		init();
	}
	
	TextView contentView;
	TextView detailTimeView;
	
	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.notice_child_item, this);
		
		contentView = (TextView)findViewById(R.id.notice_content_txt);
		detailTimeView = (TextView)findViewById(R.id.notice_content_time_txt);
		
	}

	public void setData(NoticeItem data) {
		contentView.setText(data.content);
		detailTimeView.setText(data.time);
	}
}
