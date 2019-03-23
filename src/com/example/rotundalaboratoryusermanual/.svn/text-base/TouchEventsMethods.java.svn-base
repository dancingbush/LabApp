package com.example.rotundalaboratoryusermanual;







import android.util.Log;
import android.widget.Toast;


public class TouchEventsMethods {

	
	
	public static void setDowning(CircleMenu cm,float x, float y) {
		
		
		cm.setS1(System.currentTimeMillis()) ;
		cm.setDeltaDistance(0);
		cm.setMoveX(x) ;
		cm.setMoveY(y) ;

		
		
//		cm.setFlingRunning(false);
		
		cm.setStartAngle(GetAngle.getAngle(cm,x, y));
	}

	public static void setUpping(CircleMenu cm,float x, float y) {
		cm.setS2( System.currentTimeMillis()) ;
		
		
		
		if (cm.getS2() - cm.getS1() >= 300) {
		
			
			if (PointToMenuItem.pointToPosition(cm,x, y) == -1) {
				if (!cm.getFlingRunning()){
					cm.setBalancingDelay(10);
					BalancingCircleItems.setBalance(cm);
				}
			} else {
				if (cm.getDeltaDistance() >= 5) {
					if (!cm.getFlingRunning()){
						cm.setBalancingDelay(10);
						BalancingCircleItems.setBalance(cm);
					}
				}
			}
		}
		
		
		

	}

	public static void setMoving(CircleMenu cm,float x, float y) {

		cm.setDeltaDistance(cm.getDeltaDistance()+1);
int moveScale=(cm.getDeltaDistance()==1)?50:5;
		


		if ((Math.sqrt((x - cm.getMoveX()) * (x - cm.getMoveX())) > moveScale)) {

			
			cm.setFlingRunning(false);

			cm.setCurrentAngle(GetAngle.getAngle(cm,x, y));

			cm.setDeltaAngle((cm.getCurrentAngle()- cm.getStartAngle())) ;

			
			setDeltaAngle(cm, x, y);
			

			
			cm.setStartAngle(cm.getCurrentAngle());

			cm.getHandler().sendMessage(cm.getHandler()
					.obtainMessage());
		
			cm.setMoveX(x) ;
			cm.setMoveY (y);

			
			

}
		}
	
	
	private static void setDeltaAngle(CircleMenu cm,float x,float y){
		if(!TouchDomain.getDomain(cm, y)){
		if (cm.getDeltaAngle() < 0) {
			
			cm.setDeltaAngle(-0.25);
		

		} else {
			
			cm.setDeltaAngle(0.25);
			
		}
		}
		else{
			
			if (cm.getDeltaAngle() < 0) {
				
				cm.setDeltaAngle(-1);
			

			} else {
				
				cm.setDeltaAngle(1);
	
				
			}
		}
	}
}
