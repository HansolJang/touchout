package kr.jroad.touchout.dialog;

import kr.jroad.touchout.activity.AccountInfoAcitivity;
import kr.jroad.touchout.activity.AppInfoActivity;
import kr.jroad.touchout.activity.ExtraMyWalletActivity;
import kr.jroad.touchout.activity.NoticeActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.ExtraDialogGridItemAdapter;
import kr.jroad.touchout.data.ExtraDialogGridItemData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ExtraDialogFragment extends DialogFragment {
	
	ListView dialogGridView;
	ExtraDialogGridItemAdapter dialogAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DyDialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_extra_dialog, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		dialogGridView = (ListView)view.findViewById(R.id.extra_dialog_grid);
		dialogAdapter = new ExtraDialogGridItemAdapter(getActivity());
		
		dialogGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Object selectedView = dialogGridView.getItemAtPosition(position);
				if(selectedView == dialogGridView.getItemAtPosition(0)) {
					Intent i = new Intent(getActivity(), NoticeActivity.class);
					startActivity(i);
					
				} else if(selectedView == dialogGridView.getItemAtPosition(1)) {
					Intent i = new Intent(getActivity(), ExtraMyWalletActivity.class);
					startActivity(i);
				} else if(selectedView == dialogGridView.getItemAtPosition(2)) {
					Intent i = new Intent(getActivity(),AccountInfoAcitivity.class);
					startActivity(i);
				} else {
					Intent i = new Intent(getActivity(), AppInfoActivity.class);
					startActivity(i);
				}
				
				
			}
		});
		
		dialogGridView.setAdapter(dialogAdapter);
		
		Button btnExit = (Button)view.findViewById(R.id.extra_exit_btn);
		btnExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		initExtraDialogData();
	}
	
	private void initExtraDialogData() {
		ExtraDialogGridItemData d1 = new ExtraDialogGridItemData();
		d1.resId = R.drawable.extra_tab1_selector;
		dialogAdapter.add(d1);
		
		ExtraDialogGridItemData d2 = new ExtraDialogGridItemData();
		d2.resId = R.drawable.extra_tab2_selector;
		dialogAdapter.add(d2);
		
		ExtraDialogGridItemData d4 = new ExtraDialogGridItemData();
		d4.resId = R.drawable.extra_tab3_selector;
		dialogAdapter.add(d4);
		
		ExtraDialogGridItemData d3 = new ExtraDialogGridItemData();
		d3.resId = R.drawable.extra_tab4_selector;
		dialogAdapter.add(d3);
		
	}
}
