package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.HomeCategoryListItemData;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeCategoryListItemView extends FrameLayout {

	public HomeCategoryListItemView(Context context) {
		super(context);
		init();
	}
	
	TextView homeCategoryTxtView;
	LinearLayout homeCategoryBg;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.category_list_item, this);
		
		homeCategoryTxtView = (TextView)findViewById(R.id.category_item_txt);
		homeCategoryBg = (LinearLayout)findViewById(R.id.category_bg);
		
	}
	
	public void setData(HomeCategoryListItemData data) {
		homeCategoryTxtView.setText(data.category);
		homeCategoryBg.setBackgroundResource(data.resId);
	}

}
