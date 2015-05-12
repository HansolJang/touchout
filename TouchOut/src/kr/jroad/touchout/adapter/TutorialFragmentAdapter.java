package kr.jroad.touchout.adapter;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.fragment.TutorialContentFragment;
import kr.jroad.touchout.fragment.TutorialFinalContentFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TutorialFragmentAdapter extends FragmentPagerAdapter {

	public TutorialFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		TutorialContentFragment f = new TutorialContentFragment();
		Bundle b = new Bundle();

		switch (position) {
		case 0:
			b.putInt(TutorialContentFragment.PARAM_TUTORIAL_IMG,
					R.drawable.tutorial_page1);
			f.setArguments(b);
			return f;
		case 1:
			b.putInt(TutorialContentFragment.PARAM_TUTORIAL_IMG,
					R.drawable.tutorial_page2);
			f.setArguments(b);
			return f;
		case 2:
			b.putInt(TutorialContentFragment.PARAM_TUTORIAL_IMG,
					R.drawable.tutorial_page3);
			f.setArguments(b);
			return f;
		case 3:
			b.putInt(TutorialContentFragment.PARAM_TUTORIAL_IMG,
					R.drawable.tutorial_page4);
			f.setArguments(b);
			return f;
		//마지막 페이지	
		case 4:
			TutorialFinalContentFragment ff = new TutorialFinalContentFragment();
			return ff;
		default:
			b.putInt(TutorialContentFragment.PARAM_TUTORIAL_IMG,
					R.drawable.ic_launcher);
			f.setArguments(b);
			return f;
		}
	}

	@Override
	public int getCount() {
		return 5;
	}

}
