package kr.jroad.touchout.fragment;

import com.nostra13.universalimageloader.core.ImageLoader;

import kr.jroad.touchout.activity.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class StoreImagePagerFragment extends Fragment {

	public static final String PARAM_SUB_IMAGE_URL = "subImageURL";

	String content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		imgLoader = ImageLoader.getInstance();

		Bundle b = getArguments();
		if (b != null) {
			content = b.getString(PARAM_SUB_IMAGE_URL);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.store_image_pager_item, container,
				false);
	}

	ImageView storeImagePager;
	ImageLoader imgLoader;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		storeImagePager = (ImageView) view
				.findViewById(R.id.store_info_img_view);
		if (content != null) {
			imgLoader.displayImage(content, storeImagePager);
		}
	}
}
