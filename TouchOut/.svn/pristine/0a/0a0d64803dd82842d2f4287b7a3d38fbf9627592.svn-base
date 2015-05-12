package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.CartItem;
import kr.jroad.touchout.data.CartItemGroup;
import kr.jroad.touchout.view.CartListChildView;
import kr.jroad.touchout.view.CartListGroupView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class CartListAdapter extends BaseExpandableListAdapter implements CartListChildView.OnCountListener{
	
	public interface OnCountAdapterListener {
		public void onCountPlusListener(CartListChildView view, CartItem data);
		public void onCountMinusListener(CartListChildView view, CartItem data);
	}
	
	OnCountAdapterListener plusListener;
	OnCountAdapterListener minusListener;
	
	public void setOnCountAdapterListener(OnCountAdapterListener listener) {
		plusListener = listener;
		minusListener = listener;
	}
	
	Context mContext;
	ArrayList<CartItemGroup> items = new ArrayList<CartItemGroup>();
	
	public CartListAdapter(Context context) {
		mContext = context;
	}
	
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	
	public void add(String groupKey, CartItem data){
		CartItemGroup group = null;
		for (CartItemGroup item : items) {
			if (item.storeName.equals(groupKey)) {
				group = item;
				break;
			}
		}
		if (group == null) {
			group = new CartItemGroup();
			group.storeName = groupKey;
			items.add(group);
		}
		
		group.children.add(data);
		
		notifyDataSetChanged();
	}
	
	public ArrayList<CartItem> getChildList(int groupPosition) {
		return items.get(groupPosition).children;
	}
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((long)groupPosition) << 32 | (long)childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return items.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return items.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return items.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return (long)groupPosition << 32;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		CartListGroupView group;
		if (convertView == null) {
			group = new CartListGroupView(mContext);
		} else {
			group = (CartListGroupView)convertView;
		}
		group.setData(items.get(groupPosition));
		return group;
		
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		CartListChildView child;
		if (convertView == null) {
			child = new CartListChildView(mContext);
			child.setOnCountListener(this);
		} else {
			child = (CartListChildView)convertView;
		}
		child.setData(items.get(groupPosition).children.get(childPosition));
		return child;
	}

	@Override
	public void onCountPlusListener(CartListChildView view, CartItem data) {
		if(plusListener != null) {
			plusListener.onCountPlusListener(view, data);
		}
	}

	@Override
	public void onCountMinusListener(CartListChildView view, CartItem data) {
		if(minusListener != null) {
			minusListener.onCountMinusListener(view, data);
		}
	}

}
