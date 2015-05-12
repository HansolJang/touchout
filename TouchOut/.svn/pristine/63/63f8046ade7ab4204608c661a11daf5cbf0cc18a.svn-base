package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.AccountInfoAcitivity;
import kr.jroad.touchout.activity.MainActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInfoFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_account_info, container, false);
	}
	
	LinearLayout withdrawalLayout;
	TextView btnLogout;
	TextView nameView;
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		nameView = (TextView)view.findViewById(R.id.account_info_facebook_id_txt);
		String name = PropertyManager.getInstance().getUserName();
		if(name != null && !name.equals("")) {
			nameView.setText(name);
		}
		
		withdrawalLayout = (LinearLayout)view.findViewById(R.id.account_withdrawal_layout);
		withdrawalLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				withdrawalLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.clicked));
				AccountInfoAcitivity activity = (AccountInfoAcitivity) getActivity();
				activity.goWithdrawalFragment();
			}
		});
		
		btnLogout = (TextView)view.findViewById(R.id.account_logout_btn);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				logout();
			}
		});
	}
	
	private void logout() {
		NetworkManager.getInstance().doLogout(getActivity(), new OnResultListener<PostResult>() {
			
			@Override
			public void onSuccess(PostResult result) {
				if(result.success == 1) {
					// 로그아웃하고 자동로그인 해제 하고 메인으로 ㄱㄱ 
					Toast.makeText(getActivity(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
					PropertyManager.getInstance().setFacebookLogin(false);
					Intent mainIntent = new Intent(getActivity(), MainActivity.class);
					startActivity(mainIntent);
				}
			}
			
			@Override
			public void onFail(int code) {
				
			}
		});
	}
}
