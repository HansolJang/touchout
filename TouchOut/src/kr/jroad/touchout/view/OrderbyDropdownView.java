package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.OrderbyDropdownData;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderbyDropdownView extends FrameLayout {

	public OrderbyDropdownView(Context context) {
		super(context);
		init();
	}
	
	TextView textView;
	ImageView checkImg;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.orderby_dropdown_item, this);
		
		textView = (TextView)findViewById(R.id.orderby_dropdown_txt);
		checkImg = (ImageView)findViewById(R.id.orderby_dropdown_img);
	}
	
	public void setData(OrderbyDropdownData data) {
		textView.setText(data.text);
		
		if(data.selected) {
			checkImg.setVisibility(ImageView.VISIBLE);
		} else {
			checkImg.setVisibility(ImageView.GONE);
		}
	}
	
}
