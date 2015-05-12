package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.StoreMenuItem;
import kr.jroad.touchout.view.MenuListView;
import kr.jroad.touchout.view.MenuListView.OnMenuClickListner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuListAdapter extends BaseAdapter implements OnMenuClickListner {
	public interface OnMenuAdapterClickListner {
		public void onFavoriteSelectListener(MenuListView view, StoreMenuItem data);
		public void onAddCartListener(MenuListView view, StoreMenuItem data);
		public void onCountPlusListener(MenuListView view, StoreMenuItem data);
		public void onCountMinusListener(MenuListView view, StoreMenuItem data);
		public void onWhippingSelectListener(MenuListView view, StoreMenuItem data);
		public void onPaymentListener(MenuListView view, StoreMenuItem data);
		public void onSizeRegularListener(MenuListView view, StoreMenuItem data);
		public void onSizeLargeListener(MenuListView view, StoreMenuItem data);
	}
	
	OnMenuAdapterClickListner favoriteListener;
	OnMenuAdapterClickListner cartListener;
	OnMenuAdapterClickListner plusListener;
	OnMenuAdapterClickListner minusListener;
	OnMenuAdapterClickListner whippingListener;
	OnMenuAdapterClickListner paymentListener;
	OnMenuAdapterClickListner regularListener;
	OnMenuAdapterClickListner largeListener;
	
	public void setOnMenuAdapterClickListner(OnMenuAdapterClickListner listener) {
		favoriteListener = listener;
		cartListener = listener;
		plusListener = listener;
		minusListener = listener;
		whippingListener = listener;
		paymentListener = listener;
		regularListener = listener;
		largeListener = listener;
	}
	
	Context mContext;
	ArrayList<StoreMenuItem> items = new ArrayList<StoreMenuItem>();
	
	public void clearSelected() {
		for(StoreMenuItem data : items) {
			data.menuSelected = false;
		}
		notifyDataSetChanged();
	}

	public MenuListAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuListView view;
		if(convertView == null) {
			view = new MenuListView(mContext);
			view.setOnMenuClickListener(this);
		} else {
			view = (MenuListView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	public void add(StoreMenuItem data) {
		items.add(data);
		notifyDataSetChanged();
	}
	@Override
	public void onFavoriteSelectListener(MenuListView view, StoreMenuItem data) {
		if(favoriteListener != null) {
			favoriteListener.onFavoriteSelectListener(view, data);
		}
		
	}
	@Override
	public void onAddCartListener(MenuListView view, StoreMenuItem data) {
		if(cartListener != null) {
			cartListener.onAddCartListener(view, data);
		}
	}
	@Override
	public void onCountPlusListener(MenuListView view, StoreMenuItem data) {
		if(plusListener != null) {
			plusListener.onCountPlusListener(view, data);
		}
	}
	@Override
	public void onCountMinusListener(MenuListView view, StoreMenuItem data) {
		if(minusListener != null) {
			minusListener.onCountMinusListener(view, data);
		}
	}
	@Override
	public void onWhippingSelectListener(MenuListView view, StoreMenuItem data) {
		if(whippingListener != null) {
			whippingListener.onWhippingSelectListener(view, data);
		}
	}
	@Override
	public void onPaymentListener(MenuListView view, StoreMenuItem data) {
		if(paymentListener != null) {
			paymentListener.onPaymentListener(view, data);
		}
	}
	@Override
	public void onSizeRegularListener(MenuListView view, StoreMenuItem data) {
		if(regularListener != null) {
			regularListener.onSizeRegularListener(view, data);
		}
	}
	@Override
	public void onSizeLarageListener(MenuListView view, StoreMenuItem data) {
		if(largeListener != null) {
			largeListener.onSizeLargeListener(view, data);
		}
	}

	
}
