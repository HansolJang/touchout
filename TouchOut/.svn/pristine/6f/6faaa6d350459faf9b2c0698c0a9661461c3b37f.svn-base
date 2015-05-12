package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.NowSaleItem;
import kr.jroad.touchout.view.NowSaleListView;
import kr.jroad.touchout.view.NowSaleListView.OnPaymentListener;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NowSaleListAdapter extends BaseAdapter implements OnPaymentListener {
	
	public interface OnPaymentAdapterListener {
		public void onPaymentAdapterListener(NowSaleListView view, NowSaleItem data);
	}
	
	OnPaymentAdapterListener paymentListener;
	
	public void setOnPaymentAdapterListener(OnPaymentAdapterListener listener) {
		paymentListener = listener;
	}
	
	Context mContext;
	ArrayList<NowSaleItem> items = new ArrayList<NowSaleItem>();
	
	public NowSaleListAdapter(Context context) {
		mContext = context;
	}
	
	public void add(NowSaleItem data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void clear() {
		items.clear();
		notifyDataSetChanged();
	}
	
	public void remove(NowSaleItem data) {
		items.remove(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public NowSaleItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NowSaleListView view;
		if(convertView == null) {
			view = new NowSaleListView(mContext);
			view.setOnPaymentListener(this);
		} else {
			view = (NowSaleListView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onPaymentListener(NowSaleListView view, NowSaleItem data) {
		if(paymentListener != null) {
			paymentListener.onPaymentAdapterListener(view, data);
		}
	}

}
