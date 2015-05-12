package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.fragment.StoreImagePagerFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class StoreInfoImageAdapter extends FragmentPagerAdapter {
	ArrayList<String> image = new ArrayList<String>();

	public StoreInfoImageAdapter(FragmentManager fm, ArrayList<String> content) {
		super(fm);
		//이미지 url 이 진짜 있는 것만 add
		for(String s : content) {
			if(s != null && !s.equals("")) {
				image.add(s);
			}
		}
	}

	@Override
	public Fragment getItem(int position) {
		StoreImagePagerFragment f = new StoreImagePagerFragment();
		Bundle b = new Bundle();

		if (position <= image.size()) {
			b.putString(StoreImagePagerFragment.PARAM_SUB_IMAGE_URL,
					image.get(position));
			f.setArguments(b);
		}
		return f;
	}

	@Override
	public int getCount() {
		return image.size();
	}
}
