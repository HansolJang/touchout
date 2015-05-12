package kr.jroad.touchout.dialog;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.TutorialFragmentAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Session;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class TutorialDialogFragment extends DialogFragment {
	
	ViewPager tutorialPager;
	TutorialFragmentAdapter tutorialAdapter;
	PageIndicator mIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DyDialog);
		setCancelable(false);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tutorial_dialog, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tutorialPager = (ViewPager)view.findViewById(R.id.tutorial_pager);
		tutorialAdapter = new TutorialFragmentAdapter(getChildFragmentManager());
		
		
		tutorialPager.setAdapter(tutorialAdapter);
		
		CirclePageIndicator indicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
		mIndicator = indicator;
		indicator.setViewPager(tutorialPager);
		indicator.setSnap(true);		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().onActivityResult(getActivity(),
					requestCode, resultCode, data);
		}
	}

	
}
