package com.example.rotundalaboratoryusermanual;




import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;


public class MyGestureListener extends SimpleOnGestureListener {

	CircleMenu cm;

	
	public MyGestureListener(CircleMenu cm) {
		this.cm = cm;
		
	}

	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		super.onFling(e1, e2, velocityX, velocityY);
		
		int Rotating;
		
		float x1 = e1.getX();
		float y1 = e1.getY();
		float x2 = e2.getX();
		
		float y2 = e2.getY();

	
	
		if ((cm.getS2() - cm.getS1() < 300)) {

			// float delta=(float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			float delta = (float) Math.abs((x1 - x2));

			
			boolean direction = (x1 - x2 < 0) ? true : false;

			if(!TouchDomain.getDomain(cm, y1))
				Rotating=1;
			else
				Rotating = (int) ((delta) / 100);

//			Log.d("**************", Rotating+"");
			if(Rotating!=0)
				Rotate(direction,cm.getFlingDelay(),Rotating);
			else
			
				BalancingCircleItems.setBalance(cm, cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getMenuItemImageView());
				
				
		}
		
		return true;

	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
	
		return super.onDown(e);
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
	
		
		
		if (cm.getDeltaDistance()<5) {
			
			int itemSelected = PointToMenuItem.pointToPosition(cm, e.getX(),
					e.getY());
			if (itemSelected != cm.getFirstItem()) {

				
				if (itemSelected != -1) {

				
					if (!cm.getFlingRunning()) {

						
						
						RotateToCenter.simpleFling(cm,
								cm.getCircleMenuItemsArray().get(itemSelected).getMenuItemImageView(),cm.getFlingDelay());
					}
					if (cm.getMenuItemsListeners().mOnItemClickListener != null)
						cm.getMenuItemsListeners().mOnItemClickListener.onItemClick(
								cm.getCircleMenuItemsArray().get(itemSelected),
								cm.getCircleMenuItemsArray().get(itemSelected).getName());
				}

			} else {
				if (cm.getMenuItemsListeners().mOnItemClickListener != null)
					cm.getMenuItemsListeners().mOnItemClickListener
							.onItemClick(
									cm.getCircleMenuItemsArray().get(cm.getFirstItem()),
									cm.getCircleMenuItemsArray().get(itemSelected).getName());
			}
		}
		return super.onSingleTapUp(e);
	}

	private void Rotate(boolean direction,int sleep,int Rotating) {
		if (direction) {

			int newCenterIndex = (Math.abs(findCenterIndex() + Rotating))
					% (cm.getCircleMenuItemsArray().size() );
			if (Rotating == 0)
				newCenterIndex = findCenterIndex();
			else
				newCenterIndex = checkIndex1(newCenterIndex);

//			if(Rotating!=1)
//			RotateToCenter.fling(cm,cm.getCircleItemsArray().get(newCenterIndex).getMenuItemImageView(),sleep);
//			else
				RotateToCenter.simpleFling(cm,cm.getCircleMenuItemsArray().get(newCenterIndex).getMenuItemImageView(),sleep);
		} 
		else {

			int newCenterIndex = (Math.abs(cm.getCircleMenuItemsArray().size() 
					+ findCenterIndex() - Rotating))
					% (cm.getCircleMenuItemsArray().size() );
			if (Rotating == 0)
				newCenterIndex = findCenterIndex();
			else
				newCenterIndex = checkIndex2(newCenterIndex);

//			if(Rotating!=1)
//			RotateToCenter.fling(cm,cm.getCircleItemsArray().get(newCenterIndex).getMenuItemImageView(),sleep);
//			else
				RotateToCenter.simpleFling(cm,cm.getCircleMenuItemsArray().get(newCenterIndex).getMenuItemImageView(),sleep);

		}
	}

	private int checkIndex1(int index) {

		while (!(cm.getCircleMenuItemsArray().get(index).getAngle() >= 90 && cm.getCircleMenuItemsArray()
				.get(index).getAngle() <= 270)) {

			index -= 1;
			if (index < 0)
				index += cm.getCircleMenuItemsArray().size();
		}

		return index;
	}

	private int checkIndex2(int index) {

		while (!(cm.getCircleMenuItemsArray().get(index).getAngle()>= 270 || cm.getCircleMenuItemsArray()
				.get(index).getAngle() <= 90)) {

			index += 1;
			if (index >= cm.getCircleMenuItemsArray().size())
				index %= cm.getCircleMenuItemsArray().size();
		}

		return index;
	}

	private int findCenterIndex() {

	
		
		return cm.getFirstItem();
	}

}
