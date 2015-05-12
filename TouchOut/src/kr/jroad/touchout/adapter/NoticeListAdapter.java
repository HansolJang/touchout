package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.NoticeItem;
import kr.jroad.touchout.data.NoticeItemGroup;
import kr.jroad.touchout.view.NoticeListChildView;
import kr.jroad.touchout.view.NoticeListGroupView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class NoticeListAdapter extends BaseExpandableListAdapter {
	
	Context mContext;
	ArrayList<NoticeItemGroup> items = new ArrayList<NoticeItemGroup>();
	
	public NoticeListAdapter(Context context) {
		mContext = context;
	}

	public void clear(){
		for(int i = 0; i < items.size(); i++){
			items.get(i).children.clear();
		}
		items.clear();
		notifyDataSetChanged();
	}
	
	public void add(String groupKey, NoticeItem data){
		NoticeItemGroup group = null;
		for (NoticeItemGroup item : items) {
			if (item.title.equals(groupKey)) {
				group = item;
				break;
			}
		}
		if (group == null) {
			group = new NoticeItemGroup();
			group.title = groupKey;
			items.add(group);
		}
		
		group.children.add(data);
		
		notifyDataSetChanged();
	}
	
	@Override
	public int getGroupCount() {
		return items.size();
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
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return (long)groupPosition << 32;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((long)groupPosition) << 32 | (long)childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		NoticeListGroupView group;
		if (convertView == null) {
			group = new NoticeListGroupView(mContext);
		} else {
			group = (NoticeListGroupView)convertView;
		}
		group.setData(items.get(groupPosition));
		return group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		NoticeListChildView child;
		if (convertView == null) {
			child = new NoticeListChildView(mContext);
		} else {
			child = (NoticeListChildView)convertView;
		}
		child.setData(items.get(groupPosition).children.get(childPosition));
		return child;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
