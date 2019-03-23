package com.example.rotundalaboratoryusermanual;






import android.view.View;


public class RotateToCenter {



	
	public static void fling(final CircleMenu cm, View v,final int sleep) {

		int values[] = new int[2];
		v.getLocationOnScreen(values);
		final double ang = ((GetAngle.getAngle(cm, v.getX() + v.getWidth()
				/ 2.0, v.getY() + v.getHeight() / 2.0)));

		if (ang > 0 && ang < 181) {

			cm.setDeltaAngle(-1);

		} else {
			cm.setDeltaAngle(1);

		}
		cm.setFirstItem(BalancingCircleItems.findIndex(cm, v)) ;

		cm.getCircleMenuItemsArray().get(cm.getFirstItem()).setAngle(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle()%360) ;

	
		Thread chooseThread = new Thread(new Runnable() {

			public void run() {

				try {
					int measurement = 90;
					cm.setFlingRunning(true) ;
					if ((cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() > 270)
							|| (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() < 90)) {
						cm.setDeltaAngle(1);
						measurement = 94;
					} else {
						cm.setDeltaAngle(-1);
						measurement = 86;
					}
  
					if (cm.getDeltaAngle() > 0) {
						int velocity = sleep;
						measurement = 93;
						while (cm.getFlingRunning()
								&& (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() <= measurement ||cm.getCircleMenuItemsArray()
										.get(cm.getFirstItem()).getAngle() > 270)) {

							Thread.sleep(velocity);

							cm.getHandler()
									.sendMessage(cm.getHandler()
											.obtainMessage());

							if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= 270)
									&& cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= 80)
								velocity += 1;

							if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() <= measurement || cm.getCircleMenuItemsArray()
									.get(cm.getFirstItem()).getAngle() > 270)) {

								velocity = 100;
								Thread.sleep(velocity);
							}

						}
					}

					else {
						int velocity = sleep;
						measurement = 87;
						while (cm.getFlingRunning()
								&& (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= measurement)) {

							Thread.sleep(velocity);

							cm.getHandler()
									.sendMessage(cm.getHandler()
											.obtainMessage());

							if (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() <= 100)
								velocity += 1;

						}

						if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= measurement)) {

							velocity = 100;
							Thread.sleep(velocity);
						}
					}

					cm.setFlingRunning(false) ;

				} catch (Throwable t) {
					// just end the background thread
				}

//			if(cm.isFlingRunning)
				BalancingCircleItems.setBalance(cm,
						cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getMenuItemImageView());

			}

		});

		chooseThread.start();
	}

	public static void balancing(final CircleMenu cm, View v,final int sleep) {
		

		int values[] = new int[2];
		v.getLocationOnScreen(values);
		final double ang = ((GetAngle.getAngle(cm, v.getX() + v.getWidth()
				/ 2.0, v.getY() + v.getHeight() / 2.0)));

		if (ang > 0 && ang < 181) {
			
			cm.setDeltaAngle(-1);

		} else {

			cm.setDeltaAngle(1);;

		}
		cm.setFirstItem(BalancingCircleItems.findIndex(cm, v)) ;

		cm.getCircleMenuItemsArray().get(cm.getFirstItem()).setAngle(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle()%360) ;

		Thread chooseThread = new Thread(new Runnable() {

			public void run() {

				try {
					int velocity=sleep;
					cm.setFlingRunning(true);
					if ((cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() > 270)
							|| (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() < 90))
						cm.setDeltaAngle(0.25);
					else
						cm.setDeltaAngle(-0.25);

					while (cm.getFlingRunning()
							&& !(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= 90 && cm.getCircleMenuItemsArray()
									.get(cm.getFirstItem()).getAngle() < 91)) {

						if (cm.getFlingRunning()) {
							Thread.sleep(velocity);
//							velocity+=1;
							//
//							if (cm.getCircleMenuItemsArray().get(cm.firstItem).angle == 91
//
//							|| cm.getCircleMenuItemsArray().get(cm.firstItem).angle == 89) {
//								RotateParams.deltaAngle = 90 - cm.getCircleMenuItemsArray()
//										.get(cm.firstItem).angle;
//
//							}

							cm.getHandler()
									.sendMessage(cm.getHandler()
											.obtainMessage());

						}
					}

//					cm.isFlingRunning = false;

				} catch (Throwable t) {

				}
				if(cm.getFlingRunning())
				FinalBalancing.finalBalancing(cm);

				if (cm.getMenuItemsListeners().mOnItemSelectedListener != null)
					cm.getMenuItemsListeners().mOnItemSelectedListener.onItemSelected(
							cm.getCircleMenuItemsArray().get(cm.getFirstItem()), cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getName());

			}

		}

		);

		chooseThread.start();
		
	}

	public static void simpleFling(final CircleMenu cm, View v,final int sleep) {

		int values[] = new int[2];
		v.getLocationOnScreen(values);
		final double ang = ((GetAngle.getAngle(cm, v.getX() + v.getWidth()
				/ 2.0, v.getY() + v.getHeight() / 2.0)));

		if (ang > 0 && ang < 181) {

			cm.setDeltaAngle(-1);;

		} else {
			cm.setDeltaAngle(1);

		}
		cm.setFirstItem(BalancingCircleItems.findIndex(cm, v));

		cm.getCircleMenuItemsArray().get(cm.getFirstItem()).setAngle(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle()%360) ;

		Thread chooseThread = new Thread(new Runnable() {

			public void run() {

				try {
					int measurement = 90;
					cm.setFlingRunning(true);
					if ((cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() > 270)
							|| (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() < 90)) {
						
						cm.setDeltaAngle(1);
						measurement = 90;
					} else {
						
						cm.setDeltaAngle(-1);;
						measurement = 90;
					}

					if (cm.getDeltaAngle() > 0) {
						int velocity = sleep;
						
						measurement = 90;
						while (cm.getFlingRunning()
								&& (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() < measurement || cm.getCircleMenuItemsArray()
										.get(cm.getFirstItem()).getAngle() > 270)) {

							Thread.sleep(velocity);

							cm.getHandler()
									.sendMessage(cm.getHandler()
											.obtainMessage());

						
							
							if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= 270)
									&& cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= 60)
								velocity +=(180- cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle())*0.01;

//							if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).angle < measurement || cm.getCircleMenuItemsArray()
//									.get(cm.getFirstItem()).angle > 270)) {
//
//								velocity = 100;
//								Thread.sleep(velocity);
//							}

							if ((cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= measurement-1)&&
									(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() <= measurement+1)) {
								
								cm.setDeltaAngle(90.0-cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle());
								
								Thread.sleep(velocity);
								cm.getHandler()
								.sendMessage(cm.getHandler()
										.obtainMessage());
								
								
								break;
							}
						}
					}

					else {
						int velocity = sleep;
						measurement = 90;
						while (cm.getFlingRunning()
								&& (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() > measurement)) {

							Thread.sleep(velocity);

							cm.getHandler()
									.sendMessage(cm.getHandler()
											.obtainMessage());

							
							if (cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() <= 120)
								velocity += cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle()*0.01;


//							if (!(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).angle >= measurement)) {
//
//								velocity = 100;
//								Thread.sleep(velocity);
//							}
							
							if ((cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle() >= measurement-1)&&
									(cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle()<= measurement+1)) {
								
								cm.setDeltaAngle(90.0-cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getAngle());
								
								Thread.sleep(velocity);
								cm.getHandler()
								.sendMessage(cm.getHandler()
										.obtainMessage());
							
							
								
								break;
							}
							
						}

					}

//					cm.setFlingRunning(false) ;

				} catch (Throwable t) {
					// just end the background thread
				}

				if(cm.getFlingRunning())
				FinalBalancing.finalBalancing(cm);

				if (cm.getMenuItemsListeners().mOnItemSelectedListener != null)
					cm.getMenuItemsListeners().mOnItemSelectedListener.onItemSelected(
							cm.getCircleMenuItemsArray().get(cm.getFirstItem()), cm.getCircleMenuItemsArray().get(cm.getFirstItem()).getName());
			}

		});

		chooseThread.start();
	}
}
