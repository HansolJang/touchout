package kr.jroad.touchout.dialog;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.StampAdapter;
import kr.jroad.touchout.data.StampData;
import kr.jroad.touchout.data.StampItem;
import kr.jroad.touchout.fragment.StoreHotMenuFragment;
import kr.jroad.touchout.view.StampListView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class StampDialogFragment extends DialogFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DyDialog);
		// /////////////////arraylist<stampItem> => parcelable!
		Bundle b = getArguments();
		if (b != null) {
			stamp = b.getParcelable(StoreHotMenuFragment.STORE_STAMP_KEY);
		}
	}
	
	StampItem stamp;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.dialog_stamp, container, false);
	}

	TextView storeNameView;
	GridView stampGridView;
	StampAdapter stampAdapter;
	TextView descView;
	TextView rewardView;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		storeNameView = (TextView) view
				.findViewById(R.id.dialog_stamp_store_name_txt);
		stampGridView = (GridView) view
				.findViewById(R.id.dialog_stamp_grid_view);
		stampAdapter = new StampAdapter(getActivity());
		descView = (TextView) view.findViewById(R.id.stamp_desc_txt);
		rewardView = (TextView) view.findViewById(R.id.stamp_reward_txt);
		Button btnExit = (Button) view.findViewById(R.id.dialog_stamp_exit_btn);
		Button btnExitUp = (Button)view.findViewById(R.id.dialog_stamp_up_exit_btn);
		
		stampGridView.setAdapter(stampAdapter);
		
		if (stamp != null) {
			
			storeNameView.setText(stamp.storeName);
			descView.setText(stamp.stampDesc);
			rewardView.setText("스탬프 10개 달성시 " + stamp.reward + " 한 잔 무료!");
			
			for (int i = 0; i < 10; i++) {
				if (i < stamp.stampCount) {
					StampData data = new StampData();
					data.stamped = true;
					data.stampedId = StampListView.stampedIcon[i];
					stampAdapter.add(data);
				} else {
					StampData data = new StampData();
					data.stamped = false;
					data.unStampedId = StampListView.unstampedIcon[i];
					stampAdapter.add(data);
				}
			}
			
		}

		btnExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		btnExitUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
	}
}
