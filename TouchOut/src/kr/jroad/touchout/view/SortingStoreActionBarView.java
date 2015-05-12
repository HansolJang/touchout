package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class SortingStoreActionBarView extends FrameLayout {

	public SortingStoreActionBarView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.actionbar_sorting_store, this);
	}

}
