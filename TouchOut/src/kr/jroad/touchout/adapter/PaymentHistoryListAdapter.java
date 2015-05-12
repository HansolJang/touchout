package kr.jroad.touchout.adapter;

import java.util.ArrayList;
import kr.jroad.touchout.data.PaymentHistoryItem;
import kr.jroad.touchout.data.PaymentHistoryList;
import kr.jroad.touchout.view.PaymentHistoryChildOrderView;
import kr.jroad.touchout.view.PaymentHistoryChildResultView;
import kr.jroad.touchout.view.PaymentHistoryGroupView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class PaymentHistoryListAdapter extends BaseExpandableListAdapter {

	public static final int TYPE_COUNT = 2;

	public static final int VIEW_TYPE_ORDERED_MENU = 0;
	public static final int VIEW_TYPE_RESULT_PRICE = 1;

	Context mContext;
	ArrayList<PaymentHistoryItem> items = new ArrayList<PaymentHistoryItem>();
	PaymentHistoryList data;

	public PaymentHistoryListAdapter(Context context) {
		mContext = context;
	}

	public void clearAll() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).items.clear();
		}
		items.clear();
		notifyDataSetChanged();
	}

	// public void add(PaymentHistoryItem groupData) {
	//
	// PaymentHistoryItem group = null;
	// for(PaymentHistoryItem item : items) {
	// if(item.payment_id == groupData.payment_id) {
	// group = item;
	// break;
	// }
	// }
	//
	// if(group == null) {
	// group = new PaymentHistoryItem();
	// group.storeName = groupData.storeName;
	// group.orderedTime = groupData.orderedTime;
	// items.add(group);
	// }
	//
	// }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if (data != null) {
			if (childPosition < data.items.get(groupPosition).items.size()) {
				// menu data
				return data.items.get(groupPosition).items.get(childPosition);
			} else {
				// result price data
				return data.items.get(groupPosition);
			}
		}
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((long) groupPosition) << 32 | (long) childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if (data != null) {
			return data.items.get(groupPosition).items.size() + 1;
		}
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		if (data != null) {
			return data.items.get(groupPosition);
		}
		return null;
	}

	@Override
	public int getGroupCount() {
		if (data != null) {
			return data.items.size();
		}
		return 0;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return (long) groupPosition << 32;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	public int getChildViewTypeCount() {
		return TYPE_COUNT;
	}

	public int getChildItemViewType(int groupPosition, int childPosition) {
		// switch (items.get(groupPosition).childeren.get(childPosition).type) {
		// case PaymentHistoryChild.TYPE_ORDERED_MENU:
		// return VIEW_TYPE_ORDERED_MENU;
		// case PaymentHistoryChild.TYPE_RESULT_PRICE:
		// return VIEW_TYPE_RESULT_PRICE;
		// }
		// return VIEW_TYPE_ORDERED_MENU;
		if (data != null) {
			if (childPosition < data.items.get(groupPosition).items.size()) {
				return VIEW_TYPE_ORDERED_MENU;
			} else {
				return VIEW_TYPE_RESULT_PRICE;
			}
		} else
			return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		PaymentHistoryGroupView group;
		if (convertView == null) {
			group = new PaymentHistoryGroupView(mContext);
		} else {
			group = (PaymentHistoryGroupView) convertView;
		}
		group.setData(data.items.get(groupPosition));
		return group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		switch (getChildItemViewType(groupPosition, childPosition)) {
		case VIEW_TYPE_ORDERED_MENU:
			PaymentHistoryChildOrderView orderView;
			if (convertView == null
					|| !(convertView instanceof PaymentHistoryChildOrderView)) {
				orderView = new PaymentHistoryChildOrderView(mContext);
			} else {
				orderView = (PaymentHistoryChildOrderView) convertView;
			}
			orderView
					.setData(data.items.get(groupPosition).items.get(childPosition));
			return orderView;
		case VIEW_TYPE_RESULT_PRICE:
			PaymentHistoryChildResultView resultView;
			if (convertView == null
					|| !(convertView instanceof PaymentHistoryChildResultView)) {
				resultView = new PaymentHistoryChildResultView(mContext);
			} else {
				resultView = (PaymentHistoryChildResultView) convertView;
			}
			resultView.setData(data.items.get(groupPosition));
			return resultView;
		}
		return null;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	public void setPaymentHistoryList(PaymentHistoryList result) {
		data = result;
	}

}
