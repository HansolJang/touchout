package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.SortingStore;
import kr.jroad.touchout.view.SortingStoreListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SortingStoreListAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<SortingStore> items = new ArrayList<SortingStore>();
	
	public SortingStoreListAdapter(Context context) {
		mContext = context;
	}
	
	public void add(SortingStore data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void clear() {
		items.clear();
		notifyDataSetChanged();
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
		SortingStoreListView view;
		if (convertView == null) {
			view = new SortingStoreListView(mContext);
		} else {
			view = (SortingStoreListView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
