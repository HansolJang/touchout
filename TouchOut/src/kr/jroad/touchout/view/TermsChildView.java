package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TermsChildView extends FrameLayout {

	public TermsChildView(Context context) {
		super(context);
		init();
	}
	
	TextView contentView;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.terms_child_item, this);
		
		contentView = (TextView)findViewById(R.id.terms_child_txt);
	}
	
	public void setData(String content) {
		contentView.setText(Html.fromHtml(content));
	}
}
