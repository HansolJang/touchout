package kr.jroad.touchout.activity;

import kr.jroad.touchout.data.ProfileResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallActivity extends ActionBarActivity {
	
	TextView descView;
	TextView nameView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_call);
	    descView = (TextView)findViewById(R.id.call_desc_txt);
	    nameView = (TextView)findViewById(R.id.call_name_txt);
	    
	    descView.setText("테이크 아웃을 하고 \n 픽업하기를 눌러주세요!");
	    
	    String userName = PropertyManager.getInstance().getUserName();
	    if(userName != null && !userName.equals("")) {
		    nameView.setText(userName + "님! \n주문하신 음료가 완성되었습니다!");
	    }
	    
	    Button btnExit = (Button)findViewById(R.id.call_exit_btn);
	    btnExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    
	    Button btnPickup = (Button)findViewById(R.id.call_pickup_btn);
	    btnPickup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PropertyManager.getInstance().setIsPickUp(true);
				finish();
			}
		});
	}

}
