package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class ReviewListHeaderView extends FrameLayout {

	public ReviewListHeaderView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.store_info_header_view, this);
	}

}
