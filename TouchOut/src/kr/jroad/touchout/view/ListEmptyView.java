package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ListEmptyView extends FrameLayout {

	public ListEmptyView(Context context) {
		super(context);
		init();
	}
	
	public ListEmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	TextView emptyTitleView;
	TextView emptySubView;
	ImageView emptyImgView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.fragment_no_result, this);
		
		emptyTitleView = (TextView)findViewById(R.id.no_result_title_txt);
		emptySubView = (TextView)findViewById(R.id.no_result_sub_txt);
		emptyImgView = (ImageView)findViewById(R.id.no_result_img);
	}
	
	public void setData(int id, String title, String sub) {
		emptyImgView.setBackgroundResource(id);
		emptyTitleView.setText(title);
		emptySubView.setText(sub);
	}

}
