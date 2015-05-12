package kr.jroad.touchout.view;

import java.text.DecimalFormat;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.FavoriteItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class FavoriteMenuListView extends FrameLayout {

	public interface OnEditClickListener {
		public void onEditClickListener(FavoriteMenuListView view,
				FavoriteItem data);
	}

	public interface OnRemoveClickListener {
		public void onRemoveClickListener(FavoriteMenuListView view,
				FavoriteItem data);
	}

	OnEditClickListener editListener;
	OnRemoveClickListener removeListener;

	public void setOnEditClickListener(OnEditClickListener listener) {
		editListener = listener;
	}

	public void setOnRemoveClickListener(OnRemoveClickListener listener) {
		removeListener = listener;
	}

	public FavoriteMenuListView(Context context) {
		super(context);
		init();
	}

	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	FavoriteItem data;
	ImageLoader imgLoader;
	ImageView favoriteMenuImgView;
	TextView favoriteMenuStoreView;
	TextView favoriteMenuNameView;
	TextView favoriteMenuPriceView;
	ImageView favoriteEditView;
	ImageView favortieRemoveView;
	ImageView favoriteMoreView;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.favorite_list_item,
				this);
		imgLoader = ImageLoader.getInstance();

		favoriteMenuImgView = (ImageView) findViewById(R.id.favorite_menu_img);
		favoriteMenuStoreView = (TextView) findViewById(R.id.favorite_menu_store);
		favoriteMenuNameView = (TextView) findViewById(R.id.favorite_menu_name);
		favoriteMenuPriceView = (TextView) findViewById(R.id.favorite_menu_price);

		favoriteEditView = (ImageView) findViewById(R.id.favorite_edit_img);
		favoriteEditView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (editListener != null) {
					editListener.onEditClickListener(FavoriteMenuListView.this,
							data);
				}
			}
		});

		favortieRemoveView = (ImageView) findViewById(R.id.favorite_remove_img);
		favortieRemoveView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (removeListener != null) {
					removeListener.onRemoveClickListener(
							FavoriteMenuListView.this, data);
				}
			}
		});

		favoriteMoreView = (ImageView) findViewById(R.id.favorite_more_img);
	}

	public void setData(FavoriteItem data) {
		this.data = data;

		imgLoader.displayImage(data.image, favoriteMenuImgView);
		favoriteMenuStoreView.setText(data.storeName);
		favoriteMenuNameView.setText(data.menuName);

		String thousandPrice = thousandFormat.format(data.menuPrice);
		favoriteMenuPriceView.setText(thousandPrice);
	}

	public void setEditable(boolean editable) {
		if (editable) {
			favoriteMoreView.setVisibility(ImageView.GONE);
			favoriteEditView.setVisibility(ImageView.VISIBLE);
			favortieRemoveView.setVisibility(ImageView.VISIBLE);

		} else {
			favoriteMoreView.setVisibility(ImageView.VISIBLE);
			favoriteEditView.setVisibility(ImageView.GONE);
			favortieRemoveView.setVisibility(ImageView.GONE);
		}
	}

}
