package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.ExtraDialogGridItemData;
import kr.jroad.touchout.view.ExtraDialogGridItemView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ExtraDialogGridItemAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<ExtraDialogGridItemData> items = new ArrayList<ExtraDialogGridItemData>();
	
	public ExtraDialogGridItemAdapter(Context context) {
		mContext = context;
	}
	
	public void add(ExtraDialogGridItemData data) {
		items.add(data);
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
		ExtraDialogGridItemView view;
		if(convertView == null) {
			view = new ExtraDialogGridItemView(mContext);
		} else {
			view = (ExtraDialogGridItemView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
