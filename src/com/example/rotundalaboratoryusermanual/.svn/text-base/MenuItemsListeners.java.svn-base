package com.example.rotundalaboratoryusermanual;



import android.view.View;



public class MenuItemsListeners {

	
	public  OnItemClickListener mOnItemClickListener = null;
	public  OnItemSelectedListener mOnItemSelectedListener = null;
	
	
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}

	public  static interface OnItemClickListener {
		void onItemClick(CircleMenuItem circleItem, String name);
	}

	public void setOnItemSelectedListener(
			OnItemSelectedListener onItemSelectedListener) {
		this.mOnItemSelectedListener = onItemSelectedListener;
	}

	public static interface OnItemSelectedListener {
		void onItemSelected(CircleMenuItem circleItem, String name);
	}
}
