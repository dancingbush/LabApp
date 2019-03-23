package com.example.rotundalaboratoryusermanual;



import android.os.Handler;

public class InitCircleMenuItemsPositions  {

	
	
	public static void initPositions(final CircleMenu cm){
		
	Thread t=new Thread(new Runnable() {
			
			public void run() {
	
				
				for(int i=0;i<20;i++){
					cm.getHandler().sendMessage(cm.getHandler().obtainMessage());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					
			}
		});
		t.start();
	}

	
	
	
	
	
	
	
	
}
