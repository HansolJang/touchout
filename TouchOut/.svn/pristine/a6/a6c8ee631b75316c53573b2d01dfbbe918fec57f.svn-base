package kr.jroad.touchout.adapter;

import com.viewpagerindicator.IconPagerAdapter;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.fragment.HomeAllStoreFragment;
import kr.jroad.touchout.fragment.HomeCoffeeStoreFragment;
import kr.jroad.touchout.fragment.HomeLunchboxStoreFragment;
import kr.jroad.touchout.fragment.HomeSortingStoreFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsIndicatorAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

	// ///////////////////if ~ fragment f = new ~

	private String[] content;
	private static final int[] ICONS = new int[] {
		R.drawable.category_all_selector,
		R.drawable.category_coffee_selector,
		R.drawable.category_lunch_selector,
		R.drawable.category_pizza_selector,
		R.drawable.category_bob_selector,
		R.drawable.category_chicken_selector,
		R.drawable.category_snack_selector
	};

	public TabsIndicatorAdapter(FragmentManager fm) {
		super(fm);
		content = HomeSortingStoreFragment.CONTENT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			HomeAllStoreFragment allStore = new HomeAllStoreFragment();
			return allStore;
		case 1:
			HomeCoffeeStoreFragment coffeeStore = new HomeCoffeeStoreFragment();
			return coffeeStore;
		case 2:
			HomeLunchboxStoreFragment lunchboxStore = new HomeLunchboxStoreFragment();
			return lunchboxStore;
		case 3:
			HomeLunchboxStoreFragment pizzaStore = new HomeLunchboxStoreFragment();
			return pizzaStore;
		case 4:
			HomeLunchboxStoreFragment riceburgerStore = new HomeLunchboxStoreFragment();
			return riceburgerStore;
		case 5:
			HomeLunchboxStoreFragment chickenStore = new HomeLunchboxStoreFragment();
			return chickenStore;
		case 6:
			HomeLunchboxStoreFragment snackStore = new HomeLunchboxStoreFragment();
			return snackStore;
		default:
			return null;
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "";
	}

	@Override
	public int getCount() {
		return content.length;
	}

	@Override
	public int getIconResId(int index) {
		return ICONS[index];
	}

}
