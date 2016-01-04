package com.example.enrico.gestioneimmagini.ImageClasses;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PrivateTouchListener implements OnTouchListener {

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
// we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ROTATE = 2;
    private int mode = NONE;
// remember some things for zooming
    private PointF start = new PointF();
    private float lastX,lastY,screenWidth,screenHeight;
	private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
	private int count=0;
	private float precR=0;
	private float limitR=0;
	private boolean leftFlag=false,rightFlag=false,topFlag=false,bottomFlag=false,flag;
	private Rectangle imageRect;
	PointF center;
	
	public PrivateTouchListener(Rectangle imageRect,PointF center)
	{
		this.imageRect= imageRect;
        this.center= center;
       
	}
	@Override
	 public boolean onTouch(View v, MotionEvent event) {
	// handle touch events here
	        Image view = (Image) v;
	        
	        switch (event.getAction() & MotionEvent.ACTION_MASK) {
	            case MotionEvent.ACTION_DOWN:
				 if(isOnImageOneTouch(event))
	                {
	                    savedMatrix.set(matrix);
	                    start.set(event.getX(), event.getY());
	                    lastX=start.x;
						lastY=start.y;
						mode = DRAG;
	                    lastEvent = null;
						imageRect.setRectAngle();
						imageRect.setScale();
					}
					
	                else
	                    mode=NONE;
	                break;
	            case MotionEvent.ACTION_POINTER_DOWN:
	                if(isOnImageDoubleTouch(event))
	                {
						if(event.getPointerCount()==3)
	                    oldDist= spacing(event);
						
						precR=0;
						limitR=0;
						count=0;
						leftFlag=false;
						rightFlag=false;
						topFlag=false;
						bottomFlag=false;
	                    savedMatrix.set(matrix);
	                    mode = ROTATE;
	                    lastEvent = new float[4];
	                    d = rotation(event);
						imageRect.setRectAngle();
						imageRect.setScale();
	                }
	                else
	                    mode=NONE;
	                
	                break;
	            case MotionEvent.ACTION_UP:
	            case MotionEvent.ACTION_POINTER_UP:
	                mode = NONE;
	                lastEvent = null;
	                break;
	            case MotionEvent.ACTION_MOVE:    
	                if (mode == DRAG) {
	                    matrix.set(savedMatrix);
						float dx = event.getX() - start.x;
						float dy = event.getY() - start.y;
						float matrixValues[]=new float[9];
						matrix.getValues(matrixValues);
						float matrixX = matrixValues[2];
						float matrixY = matrixValues[5];
						center=imageRect.getCenterByB(new PointF(matrixX,matrixY));   
						dx = event.getX() - start.x;
						dy = event.getY() - start.y;
						PointF points[]=new PointF[4];
	                    imageRect.rectPoints(points);
					    PointF B=points[1];
						
						//if image will go outside left bound
						if (imageRect.getLeftPoint().x+center.x+ dx < 0){
							dx = -(B.x+center.x-xDistanceBetweenTwoPoints(B,imageRect.getLeftPoint()));
						}
						//if image will go outside right bound
						if(imageRect.getRightPoint().x+center.x+ dx  > view.getWidth()){
							dx = view.getWidth() - (B.x+center.x+xDistanceBetweenTwoPoints(B,imageRect.getRightPoint()));
						}
						//if image will go oustside top bound
						if (-imageRect.getTopPoint().y+center.y+ dy < 0){
							dy = -(-B.y+center.y-yDistanceBetweenTwoPoints(B,imageRect.getTopPoint()));
						}
						//if image will go outside bottom bound
						if(-imageRect.getBottomPoint().y+center.y + dy  > view.getHeight()){
							dy = view.getHeight() -(-B.y+center.y+yDistanceBetweenTwoPoints(B,imageRect.getBottomPoint()));
						}
						
					    matrix.postTranslate(dx, dy);
						float values[]=new float[9];
						matrix.getValues(values);
						float X = values[2];
						float Y = values[5];
						center=imageRect.getCenterByB(new PointF(X,Y));   
						
					} else if (mode == ROTATE) {
						if(event.getPointerCount()==2)
						{
							matrix.set(savedMatrix);
	                        newRot = rotation(event);
	                        float r;
							if(d<0)
								d=360+d;
							if(newRot<0)
								newRot=360+newRot;
							
							r=(newRot-d+360)%360;
							Rectangle tempRect = imageRect.clone();
							float angle=0;
							tempRect.setRotation((float)Math.toRadians(Math.abs(limitR)));
							
							if(tempRect.getLeftPoint().x+center.x<=0)
								leftFlag=true;
							else if(tempRect.getRightPoint().x+center.x>=view.getWidth())
								rightFlag=true;
							else if(-tempRect.getTopPoint().y+center.y<=0)
								topFlag=true;
							else if(-tempRect.getBottomPoint().y+center.y>=view.getHeight())
								bottomFlag=true;
							
							if(bottomFlag&&Math.abs(limitR)==0&&count==0)
								precR=0;
							else if(topFlag&&Math.abs(limitR)==0&&count==0)
						        precR=0;
							else if(rightFlag&&Math.abs(limitR)==0&&count==0)
						        precR=0;
							else if(leftFlag&&Math.abs(limitR)==0&&count==0)
								precR=0;
								
							if((r>=0&&r<=90)&&(precR==0||precR<360&&precR>=270)&&(leftFlag||rightFlag||topFlag||bottomFlag))
							{
								count=0;
								count++;
							}
							else if((r<360&&r>=270)&&((precR>=0&&precR<=90))&&(leftFlag||rightFlag||topFlag||bottomFlag))
							{
								count=0;
								count--;
							}
							
							precR=r;
							
							if(leftFlag)
							{
								
	                                if(tempRect.pointIsInHigherSide((tempRect.getLeftPoint())))
									{
										if(Math.abs(limitR)>=0.0&&Math.abs(limitR)<=5)
										{
											
											if(r+count*360<0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));	
												
											}
											else if(r+count*360>0)
											{
												leftFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												count=0;
											}
										}
										else if(Math.abs(limitR)>5)
										{
											if(r+count*360-limitR<0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											}
											else if(r+count*360-limitR>0)
											{
												leftFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												limitR=0;
												count=0;
											}
									    }
									}
								else if(!tempRect.pointIsInHigherSide((tempRect.getLeftPoint())))
									{
										if((limitR<360&&limitR>=355)||Math.abs(limitR)==0)
										{
											if(r+count*360>0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));	
											}
											else if(r+count*360<0)
											{
												leftFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												count=0;
											}
										}
										else if(limitR<355)
										{
											if(r+count*360-limitR>0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											}
											else if(r+count*360-limitR<0)
											{
												leftFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												limitR=0;
												count=0;
											}
										}
									}
								
							}
							
							else if(rightFlag)
							{

								if(tempRect.pointIsInHigherSide((tempRect.getRightPoint())))
								{
									if(limitR<360&&limitR>=355||Math.abs(limitR)==0)
									{
										if(r+count*360>0)
										{
											r=limitR;
											angle=(float)Math.toRadians(r);
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));	
										}
										else if(r+count*360<0)
										{
											rightFlag=false;
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											limitR=0;
											count=0;
										}
									}
									else if(limitR<355)
									{
										if(r+count*360-limitR>0)
										{
											r=limitR;
											angle=(float)Math.toRadians(r);
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
										}
										else if(r+count*360-limitR<0)
										{
											rightFlag=false;
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											limitR=0;
											count=0;
										}
									}
								}
								else if(!tempRect.pointIsInHigherSide((tempRect.getRightPoint())))
								{
									if(Math.abs(limitR)>=0.0&&Math.abs(limitR)<=5)
									{

										if(r+count*360<0)
										{
											r=limitR;
											angle=(float)Math.toRadians(r);
									}
										else if(r+count*360>0)
										{
											rightFlag=false;
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											count=0;
										}
									}
									else if(limitR>5)
									{
										if(r+count*360-limitR<0)
										{
											r=limitR;
											angle=(float)Math.toRadians(r);
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
										}
										else if(r+count*360-limitR>0)
										{
											rightFlag=false;
											tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											limitR=0;
											count=0;
										}
									}
								}

							}
							else if(topFlag)
							{

								if(tempRect.pointIsInRightSide((tempRect.getTopPoint())))
								{
										if(Math.abs(limitR)>=0.0&&Math.abs(limitR)<=5)
										{

											if(r+count*360<0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
											}
											else if(r+count*360>0)
											{
												topFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												count=0;
											}
										}
										else if(limitR>5)
										{
											if(r+count*360-limitR<0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											}
											else if(r+count*360-limitR>0)
											{
												topFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												limitR=0;
												count=0;
											}
										}
									}
									else if(!tempRect.pointIsInRightSide((tempRect.getTopPoint())))
									{
										if(limitR<360&&limitR>=355||Math.abs(limitR)==0)
										{
											if(r+count*360>0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));	
											}
											else if(r+count*360<0)
											{
												topFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												limitR=0;
												count=0;
											}
										}
										else if(limitR<355)
										{
											if(r+count*360-limitR>0)
											{
												r=limitR;
												angle=(float)Math.toRadians(r);
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
											}
											else if(r+count*360-limitR<0)
											{
												topFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
												limitR=0;
												count=0;
											}
										}
								   }

							}
							if(bottomFlag)
							{
								leftFlag=true;
								if(tempRect.pointIsInRightSide((tempRect.getBottomPoint())))
								{
									if(limitR<360&&limitR>=355||Math.abs(limitR)==0)
										 {
									 		if(r+count*360>0)
									 		{
									 			r=limitR;
									 			angle=(float)Math.toRadians(r);
									 			tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));	
									 		}
									 		else if(r+count*360<0)
									 		{
									 			bottomFlag=false;
												tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 			limitR=0;
									 			count=0;
									 		}
									 	}
									 else if(limitR<355)
									 {
									 	if(r+count*360-limitR>0)
									 	{
									 		r=limitR;
									 		angle=(float)Math.toRadians(r);
									 		tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 	}
									 	else if(r+count*360-limitR<0)
									 	{
									 		bottomFlag=false;
									 		tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 		limitR=0;
									 		count=0;
									 	}
									 }
								}
								 else if(!tempRect.pointIsInRightSide((tempRect.getBottomPoint())))
								 {
									 	if(Math.abs(limitR)>=0.0&&Math.abs(limitR)<=5)
									 	{

									 		if(r+count*360<0)
									 		{
									 			r=limitR;
									 			angle=(float)Math.toRadians(r);
									 		}
									 		else if(r+count*360>0)
									 		{
									 			bottomFlag=false;
									 			tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 			count=0;
									 		}
									 	}
									 	else if(limitR>5)
									 	{
									 		if(r+count*360-limitR<0)
									 		{
									 			r=limitR;
									 			angle=(float)Math.toRadians(r);
									 			tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 		}
									    	else if(r+count*360-limitR>0)
									 		{
									 			bottomFlag=false;
									 			tempRect.setRotation((float)Math.toRadians(-Math.abs(limitR)));
									 			limitR=0;
									 			count=0;
									 		}
							       		}
	                              }
							}
							
							angle=(float)Math.toRadians(r);
							tempRect.setRotation(angle);
							
							if (tempRect.getLeftPoint().x+center.x <0)
							{		
								float y2=0;
								leftFlag=true;
								
								if(tempRect.pointIsInHigherSide(tempRect.getLeftPoint()))
								 	y2=(float) (center.y-Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((0-center.x),2)));
								else
									y2=(float) (center.y+Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((0-center.x),2)));
								
							    tempRect.setPoint(relativePosition(new PointF(0,y2)),tempRect.getLeftPoint());
								
								if( Math.toDegrees(tempRect.getRotationAngle())<0)
									r=(360+(float)Math.toDegrees(tempRect.getRotationAngle()))%360;
								else
									r=(float)Math.toDegrees(tempRect.getRotationAngle())%360;
							    
								limitR=r;
							}
						    
							else if (tempRect.getRightPoint().x+center.x >view.getWidth())
							{		
								float y2=0;
								rightFlag=true;
	                            
								if(tempRect.pointIsInHigherSide(tempRect.getRightPoint()))
								 	y2=(float) (center.y-Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((view.getWidth()-center.x),2)));
								else
									y2=(float) (center.y+Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((view.getWidth()-center.x),2)));

							    tempRect.setPoint(relativePosition(new PointF(view.getWidth(),y2)),tempRect.getRightPoint());
								if( Math.toDegrees(tempRect.getRotationAngle())<0)
									r=(360+(float)Math.toDegrees(tempRect.getRotationAngle()))%360;
								else
									r=(float)Math.toDegrees(tempRect.getRotationAngle())%360;
	                            
								limitR=r;
							}
							if (-tempRect.getTopPoint().y+center.y <0)
							{		
								float x2=0;
								topFlag=true;
	                            
								if(tempRect.pointIsInRightSide(tempRect.getTopPoint()))
									x2=(float) (center.x+Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((0-center.y),2)));
								else
								    x2=(float) (center.x-Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((0-center.y),2)));

							    tempRect.setPoint(relativePosition(new PointF(x2,0)),tempRect.getTopPoint());
								if( Math.toDegrees(tempRect.getRotationAngle())<0)
									r=(360+(float)Math.toDegrees(tempRect.getRotationAngle()))%360;
								else
									r=(float)Math.toDegrees(tempRect.getRotationAngle())%360;

								limitR=r;
							}
							if (-tempRect.getBottomPoint().y+center.y >view.getHeight())
							{		
								float x2=0;
								bottomFlag=true;

								if(tempRect.pointIsInRightSide(tempRect.getLeftPoint()))
								 	x2=(float) (center.x+Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((view.getHeight()-center.y),2)));
								else
									x2=(float) (center.x-Math.sqrt(Math.pow(tempRect.getRadius(),2)-Math.pow((view.getHeight()-center.y),2)));

							    tempRect.setPoint(relativePosition(new PointF(x2,0)),tempRect.getBottomPoint());
								if( Math.toDegrees(tempRect.getRotationAngle())<0)
									r=360+(float)Math.toDegrees(tempRect.getRotationAngle())%360;
								else
									r=(float)Math.toDegrees(tempRect.getRotationAngle())%360;

								limitR=r;
							}
							matrix.postRotate(r,center.x,center.y);    
	                        angle=(float)Math.toRadians(r);
							imageRect.setRotation(angle);
						
						}
						else if ( lastEvent != null && event.getPointerCount() == 3) {
						    matrix.set(savedMatrix);
							float newDist=spacing(event);
	                        float scale = (newDist / oldDist);
	                        Rectangle tempRect=imageRect.clone();
							tempRect.setScaleX(scale);
							tempRect.setScaleY(scale);
							if(tempRect.getLeftPoint().x+center.x<0)
							{
								scale=center.x*(float)Math.sqrt((float)Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getLeftPoint())),2)+1)/imageRect.getRadius();
							}
							else if(tempRect.getRightPoint().x+center.x>view.getWidth())
							{
								scale=(view.getWidth()-center.x)*(float)Math.sqrt((float)Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getRightPoint())),2)+1)/imageRect.getRadius();
							}
							else if(-tempRect.getTopPoint().y+center.y<0)
							{
								scale=center.y*(float)Math.sqrt((float)(Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getTopPoint())),2)+1)/Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getTopPoint())),2))/imageRect.getRadius();
							}
							else if(-tempRect.getBottomPoint().y+center.y>view.getHeight())
							{
								scale=(view.getHeight()-center.y)*(float)Math.sqrt((float)(Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getBottomPoint())),2)+1)/Math.pow(Math.tan(tempRect.getAngleByPoint(tempRect.getBottomPoint())),2))/imageRect.getRadius();
							}
							
							matrix.postScale(scale, scale, center.x, center.y);
							
							imageRect.setScaleX(scale);
							imageRect.setScaleY(scale);
							
							
						}
						}        


	                break;
	        }
			view.setImageMatrix(matrix);
			
			 return true;

	    }
	    public boolean pointBelongToImage(MotionEvent event)
	    {
	    	PointF touch= new PointF(event.getX(),event.getY());
			touch=relativePosition(touch);
			//t.setText(imageRect.toString());
			return imageRect.contains(touch);
	    }
	    private boolean isOnImageOneTouch(MotionEvent event)
	    {
			PointF touch= new PointF(event.getX(),event.getY());
			touch=relativePosition(touch);
			//t.setText(imageRect.toString());
			return imageRect.contains(touch);
		}
	    private boolean isOnImageDoubleTouch(MotionEvent event)
	    {
			PointF touch1= new PointF(event.getX(0),event.getY(0));
			touch1=relativePosition(touch1);
			PointF touch2= new PointF(event.getX(1),event.getY(1));
			touch2=relativePosition(touch2);
			return imageRect.contains(touch1)||imageRect.contains(touch2);
		}
	    private PointF relativePosition(PointF p)
		{
			p.x=p.x-center.x;
			p.y=center.y-p.y;
			return p;
		}
		/** Determine the space between the first two fingers
	     */
	    private float spacing(MotionEvent event) {
	        float x1 = event.getX(0) - event.getX(1);
	        float y1 = event.getY(0) - event.getY(1);
			float x2 = event.getX(0) - event.getX(2);
			float y2 = event.getY(0) - event.getY(2);
			float x3 = event.getX(1) - event.getX(2);
			float y3 = event.getY(1) - event.getY(2);
			if((float)Math.sqrt(x1 * x1 + y1 * y1)>(float)Math.sqrt(x2 * x2 + y2 * y2) && (float)Math.sqrt(x1 * x1 + y1 * y1)>(float)Math.sqrt(x3 * x3 + y3 * y3))
	        	return (float)Math.sqrt(x1 * x1 + y1 * y1);
			else if((float)Math.sqrt(x2 * x2 + y2 * y2)>(float)Math.sqrt(x1 * x1 + y1 * y1)&&(float)Math.sqrt(x2 * x2 + y2* y2)>(float)Math.sqrt(x3 * x3 + y3 * y3))
				return (float)Math.sqrt(x2 * x2 + y2 * y2);
	        else 
				return (float)Math.sqrt(x3 * x3 + y3 * y3);
			}

	    /**
	     * Calculate the mid point of the first two fingers
	     */
	    private void midPoint(PointF point, MotionEvent event) {
	        float x = event.getX(0) + event.getX(1);
	        float y = event.getY(0) + event.getY(1);
	        point.set(x / 2, y / 2);
	    }
	    /**
	     * Calculate the degree to be rotated by.
	     *
	     * @param event
	     * @return Degrees
	     */
	    private float rotation(MotionEvent event) {
	        double delta_x = (event.getX(0) - event.getX(1));
	        double delta_y = (event.getY(0) - event.getY(1));
	        double radians = Math.atan2(delta_y, delta_x);
	        return (float) Math.toDegrees(radians);
	    }

	  private float xDistanceBetweenTwoPoints(PointF a,PointF b)
	  {
		  return Math.abs(a.x-b.x);
	  }
	  private float yDistanceBetweenTwoPoints(PointF a,PointF b)
	  {
	      return Math.abs(a.y-b.y);
	  }

}
