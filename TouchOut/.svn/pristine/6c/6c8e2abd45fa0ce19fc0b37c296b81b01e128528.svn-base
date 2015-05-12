package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.ExtraDialogGridItemData;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ExtraDialogGridItemView extends FrameLayout {

	public ExtraDialogGridItemView(Context context) {
		super(context);
		init();
	}
	
	ImageView extraDialogImgView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.extra_dialog_grid_item, this);
		
		extraDialogImgView = (ImageView)findViewById(R.id.extra_dialog_grid_img);		
	}
	
	public void setData(ExtraDialogGridItemData data) {
		extraDialogImgView.setImageResource(data.resId);
	}

}
