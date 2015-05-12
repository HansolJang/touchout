package kr.jroad.touchout.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.PaymentHistoryItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentHistoryGroupView extends FrameLayout {

	public PaymentHistoryGroupView(Context context) {
		super(context);
		init();
	}
	
	TextView storeNameView;
	TextView orderedTimeView;
	SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
//	SimpleDateFormat setFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.payment_history_group_item, this);
		
		storeNameView = (TextView)findViewById(R.id.payment_store_name_txt);
		orderedTimeView = (TextView)findViewById(R.id.payment_ordered_time_txt);
	}
	
	public void setData(PaymentHistoryItem data) {
		storeNameView.setText(data.storeName);
//		try {
//			String time = setFormat.format(df.parse(data.orderedTime));
//			orderedTimeView.setText(time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		orderedTimeView.setText(data.orderedTime);
		
	}

}
