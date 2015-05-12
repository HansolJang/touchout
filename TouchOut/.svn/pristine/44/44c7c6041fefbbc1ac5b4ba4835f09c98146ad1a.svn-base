package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.TermsGroupData;
import kr.jroad.touchout.view.TermsChildView;
import kr.jroad.touchout.view.TermsGroupView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class TermsListAdapter extends BaseExpandableListAdapter {
	
	Context mContext;
	ArrayList<TermsGroupData> items = new ArrayList<TermsGroupData>();

	public TermsListAdapter(Context context) {
		mContext = context;
	}
	
	public void clearAll(){
		for(int i = 0; i < items.size(); i++){
			items.get(i).children.clear();
		}
		items.clear();
		notifyDataSetChanged();
	}
	
	public void add(String groupKey, String data){
		TermsGroupData group = null;
		for (TermsGroupData item : items) {
			if (item.title.equals(groupKey)) {
				group = item;
				break;
			}
		}
		if (group == null) {
			group = new TermsGroupData();
			group.title = groupKey;
			group.iconId = R.drawable.abc_ic_ab_back_holo_light;
			items.add(group);
		}
		
		group.children.add(data);
		
		notifyDataSetChanged();
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
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TermsChildView child;
		if (convertView == null) {
			child = new TermsChildView(mContext);
		} else {
			child = (TermsChildView)convertView;
		}
		child.setData(items.get(groupPosition).children.get(childPosition));
		return child;
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
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TermsGroupView group;
		if (convertView == null) {
			group = new TermsGroupView(mContext);
		} else {
			group = (TermsGroupView)convertView;
		}
		group.setData(items.get(groupPosition));
		return group;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
