package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class CenterTextActionBarView extends FrameLayout {

	public CenterTextActionBarView(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.actionbar_center_text, this);
	}

}
