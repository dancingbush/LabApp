package com.example.rotundalaboratoryusermanual;



import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AppInfoAdapter extends BaseAdapter {
	
	
	/*
	 * Can alter size of image returned to the Gridvoew here in getView()
	 * LayoutParmeter mehods of a imageview, imageview wrapped in Linearlayout so we must use LinearLayout parameters
	 * We can set according to screen denstity/size in getView()
	 * 
	 *  <!-- layout_appinfo.xml is the layout for every grid column, so set text and imageview sizes there -->
	 * 
	 */
	private Context mContext;
	private List<AppInfo> mListAppInfo;
	
	public AppInfoAdapter(Context context, List<AppInfo> list) {
		mContext = context;
		mListAppInfo = list;
	}

	@Override
	public int getCount() {
		return mListAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return mListAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppInfo entry = mListAppInfo.get(position);
		
		//create a new ImageView and TextView for each item refernced by teh adpter
		
		
		if(convertView == null) {
			
			//if its not recycled, intilise some attributs
			
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.layout_appinfo, null);
		}
		
		//want to change the soze of the image according to screensize then place in the gridview with this apdter
		
		
		ImageView ivIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
		
		
		//looks fine on 5 inch and a 8 inch tablet, if having touble on other screen sizes implment ths code
		DisplayMetrics metrics = new DisplayMetrics();
		
		//ivIcon.setLayoutParams(new GridView.LayoutParams(
				//(int)mContext.getResources().getDimension(R.dimen.widthImageGridview),
				//(int)mContext.getResources().getDimension(R.dimen.heihgtImageGridview)));
		//ivIcon.setLayoutParams(new RelativeLayout.LayoutParams(85, 85));
		//ivIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
		//ivIcon.setPadding(8, 8, 8, 8);
		ivIcon.setImageBitmap(entry.getIcon());
		
		TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
		tvName.setText(entry.getName());
		tvName.setTextColor(Color.WHITE);		
		return convertView;
	}
	
	

}
