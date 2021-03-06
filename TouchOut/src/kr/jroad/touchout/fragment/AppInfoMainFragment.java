package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.AppInfoActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.AppInfoListAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AppInfoMainFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_app_info_main, container, false);
	}

	ListView appInfoList;
	AppInfoListAdapter appInfoAdapter;

	String[] menu = {"Touch Out 이용약관", "개인정보 수집 및 이용약관", "위치기반서비스 이용약관", "개인정보 제3자 제공 약관"};
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		appInfoList = (ListView)view.findViewById(R.id.app_info_list);
		appInfoAdapter = new AppInfoListAdapter(getActivity());
		
		appInfoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				((AppInfoActivity) getActivity()).changeDetail(position);
			}
		});
		
		appInfoList.setAdapter(appInfoAdapter);
		
		for(int i = 0; i <menu.length; i++) {
			appInfoAdapter.add(menu[i]);
		}
		
		
	}

}
