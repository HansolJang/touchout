package kr.jroad.touchout.view;

import java.text.DecimalFormat;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.NowSaleItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class NowSaleListView extends FrameLayout {

	public interface OnPaymentListener {
		public void onPaymentListener(NowSaleListView view, NowSaleItem data);
	}

	OnPaymentListener paymentListener;

	public void setOnPaymentListener(OnPaymentListener listener) {
		paymentListener = listener;
	}

	public NowSaleListView(Context context) {
		super(context);
		init();
	}

	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	NowSaleItem data;
	ImageView cartView;
	ImageView paymentView;
	TextView storeNameView;
	ImageView saleImgView;
	ImageLoader imgLoader;
	TextView priceView;
	TextView descView;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.now_sale_list_item,
				this);

		imgLoader = ImageLoader.getInstance();

		storeNameView = (TextView) findViewById(R.id.now_sale_store_txt);
		saleImgView = (ImageView) findViewById(R.id.now_sale_img);
		priceView = (TextView) findViewById(R.id.now_sale_price_txt);
		descView = (TextView) findViewById(R.id.now_sale_desc_txt);

		priceView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(paymentListener != null) {
					paymentListener.onPaymentListener(NowSaleListView.this, data);
				}
			}
		});
	}

	public void setData(NowSaleItem data) {
		this.data = data;

		if (data.is_hot == 0) {
			data.isHot = false;
		} else {
			data.isHot = true;
		}

		String thousandPrice = thousandFormat.format(data.amount);
		storeNameView.setText(data.storeName);
		descView.setText(data.description);
		imgLoader.displayImage(data.saleImgId, saleImgView);
		priceView.setText(thousandPrice);
	}

}
