package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.TermsGroupData;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TermsGroupView extends FrameLayout {

	public TermsGroupView(Context context) {
		super(context);
		init();
	}
	
	TextView groupTitleView;
	ImageView groupIconView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.terms_group_item, this);
		
		groupTitleView = (TextView)findViewById(R.id.terms_group_txt);
		groupIconView = (ImageView)findViewById(R.id.terms_group_icon);
	}
	
	public void setData(TermsGroupData data) {
		groupTitleView.setText(data.title);
	}

}
