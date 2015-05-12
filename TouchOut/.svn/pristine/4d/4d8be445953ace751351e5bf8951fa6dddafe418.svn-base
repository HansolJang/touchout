package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.HomeCategoryListItemData;
import kr.jroad.touchout.view.HomeCategoryListItemView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeCategoryListItemAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<HomeCategoryListItemData> items = new ArrayList<HomeCategoryListItemData>();
	
	public HomeCategoryListItemAdapter(Context context) {
		mContext = context;
	}
	
	public void clear() {
		items.clear();
		notifyDataSetChanged();
	}
	
	public void addAll(ArrayList<HomeCategoryListItemData> list) {
		for(HomeCategoryListItemData data : list) {
			items.add(data);
		}
		notifyDataSetChanged();
	}
	
	public void add(HomeCategoryListItemData data) {
		items.add(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public HomeCategoryListItemData getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HomeCategoryListItemView view;
		if(convertView == null) {
			view = new HomeCategoryListItemView(mContext);
		} else {
			view = (HomeCategoryListItemView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
