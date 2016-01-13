package com.example.enrico.gestioneimmagini.ImageClasses;

import android.graphics.*;

public class Rectangle
{
	private float radius,width,height,rectAngle,rotationAngle,ABAngle,totScaleX,totScaleY,partScaleX,partScaleY;
	private PointF A,B,C,D;
    private static final int normScale=1,Ai=0,Bi=1,Ci=2,Di=3;

	public Rectangle(float width,float height)
	{
		this.width=width;
		this.height=height;
		A = new PointF(width/2,height/2);
		B = new PointF(-(width/2),height/2);
		C = new PointF(-A.x,-A.y);
		D = new PointF(-B.x,-B.y);
		radius=(float)Math.sqrt(Math.pow(width/2,2)+Math.pow(height/2,2));
	    rectAngle=(float)Math.atan2(height/2,width/2);    
	    ABAngle=(float)(Math.PI-2*rectAngle);
		totScaleY=normScale;
		totScaleX=normScale;
    }
	
    public void stable()
    {
        stabRectAngle();
        stabScale();
    }

	private void stabRectAngle()
	{
		rectAngle=(rectAngle-rotationAngle)%(float)(2*Math.PI);
		rotationAngle=0;
		
	}
	private void stabScale()
	{
		if(partScaleY!=0&&partScaleX!=0)
		{
			totScaleX*=partScaleX;
			totScaleY*=partScaleY;
	    }
		partScaleY=normScale;
		partScaleX=normScale;
		radius=(float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2));
		radius=(float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2));
	}
	private void newPointPosition()
	{
		A.x=(float)Math.cos(rectAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
	    A.y=(float)Math.sin(rectAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		B.x=(float)Math.cos(rectAngle+ABAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		B.y=(float)Math.sin(rectAngle+ABAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		C.x=(float)Math.cos(Math.PI+rectAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		C.y=(float)Math.sin(Math.PI+rectAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		D.x=(float)Math.cos(Math.PI+rectAngle+ABAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
		D.y=(float)Math.sin(Math.PI+rectAngle+ABAngle-rotationAngle)*((float)Math.sqrt(Math.pow((width*(totScaleX*partScaleX))/2,2)+Math.pow((height*(totScaleY*partScaleY))/2,2)));
	}
	private float determinate(PointF p1,PointF p2,PointF p)
	{
		return (p2.x-p.x)*(p1.y-p.y)-(p2.y-p.y)*(p1.x-p.x);
	}
	public boolean contains(PointF p)
	{
        if(invert())
		    return (determinate(A,B,p)<0&&determinate(B,C,p)<0&&determinate(C,D,p)<0&&determinate(D,A,p)<0);
        else
            return (determinate(B,A,p)<0&&determinate(A,D,p)<0&&determinate(D,C,p)<0&&determinate(C,B,p)<0);
    }

	public void rectPoints(PointF []points)
	{
		points[Ai]=A;
		points[Bi]=B;
		points[Ci]=C;
		points[Di]=D;
	}
    private boolean invert()
    {
        PointF p= new PointF(0,0);
        return (determinate(A,B,p)<0&&determinate(B,C,p)<0&&determinate(C,D,p)<0&&determinate(D,A,p)<0);
    }
	public Rectangle clone()
	{
		Rectangle tempRect=new Rectangle(this.width,this.height);
	    tempRect.setRectAngle(this.rectAngle);
		tempRect.setScaleX(this.totScaleX);
		tempRect.setScaleY(this.totScaleY);
		tempRect.stabScale();
		return tempRect;
	}
    public boolean pointIsInHigherSide(PointF point)
    {
        if(point.y>=0)
            return true;
        else
            return false;
    }
    public boolean pointIsInRightSide(PointF point)
    {
        if(point.x>=0)
            return true;
        else
            return false;
    }
    public float getScaleX()
    {
        stable();
        return this.totScaleX;
    }
    public PointF getCenterByB(PointF Brel)
    {
        PointF center=new PointF(0,0);
        if(B.x<0&&B.y>0)
        {
            center.x=Brel.x-B.x;
            center.y=Brel.y+B.y;
        }
        else if(B.x>0&&B.y>0)
        {
            center.x=Brel.x-B.x;
            center.y=Brel.y+B.y;
        }

        else if(B.x>0&&B.y<0)
        {
            center.x=Brel.x-B.x;
            center.y=Brel.y+B.y;
        }

        else if(B.x<0&&B.y<0)
        {
            center.x=Brel.x-B.x;
            center.y=Brel.y+B.y;
        }
        return center;
    }


    public float getAngleByPoint(PointF point)
    {
        if(point.equals(A))
            return rectAngle;
        else if(point.equals(B))
            return rectAngle+ABAngle;
        else if(point.equals(C))
            return rectAngle;
        else if(point.equals(D))
            return rectAngle+ABAngle;
        else
            return -1;
    }
    public PointF getRightPoint()
    {
        PointF points[]=new PointF[4];
        rectPoints(points);
        PointF currentPoint= new PointF(0,0);
        for(int i=0;i<4;i++)
        {
            if(points[i].x>currentPoint.x)
                currentPoint=points[i];

        }
        return currentPoint;
    }
    public PointF getBottomPoint()
    {
        PointF points[]=new PointF[4];
        rectPoints(points);
        PointF currentPoint= new PointF(0,0);
        for(int i=0;i<4;i++)
        {
            if(points[i].y<currentPoint.y)
                currentPoint=points[i];

        }
        return currentPoint;
    }
    public PointF getLeftPoint()
    {
        PointF points[]=new PointF[4];
        rectPoints(points);
        PointF currentPoint= new PointF(0,0);
        for(int i=0;i<4;i++)
        {
            if(points[i].x<currentPoint.x)
                currentPoint=points[i];

        }
        return currentPoint;
    }
    public PointF getTopPoint()
    {
        PointF points[]=new PointF[4];
        rectPoints(points);
        PointF currentPoint= new PointF(0,0);
        for(int i=0;i<4;i++)
        {
            if(points[i].y>currentPoint.y)
                currentPoint=points[i];

        }
        return currentPoint;
    }
    public float getRectAngle()
    {
        return rectAngle;
    }
    public float getRotationAngle()
    {
        return rotationAngle%(float)(2*Math.PI);
    }
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public float getRadius()
    {
        return radius;
    }
    public void setRotation(float angle)
    {

        rotationAngle=angle;
        newPointPosition();
    }
    public void setRectAngle(float angle)
    {
        rectAngle=angle;

    }
    public void setScaleX(float scale)
    {
        partScaleX=scale;
        newPointPosition();
    }
    public void setScaleY(float scale)
    {
        partScaleY=scale;
        newPointPosition();
    }
    public void setPoint(PointF point,PointF pointToChange)
    {
        float angle=0;
        if(pointToChange.equals(A))
        {
            if(Math.asin(point.y/radius)>0)
            {
                angle=(float)Math.acos(point.x/radius);
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)<Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)>Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            rotationAngle=rectAngle-angle;
        }
        else if(pointToChange.equals(B))
        {
            if(Math.asin(point.y/radius)>0)
            {
                angle=(float)Math.acos(point.x/radius);
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)<Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)>Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            rotationAngle=rectAngle+ABAngle-angle;
        }
        else if(pointToChange.equals(C))
        {
            if(Math.asin(point.y/radius)>0)
            {
                angle=(float)Math.acos(point.x/radius);
            }
            else if(Math.asin(point.y/radius)<0)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            rotationAngle=(float)Math.PI+rectAngle-angle;
        }
        else if(pointToChange.equals(D))
        {
            if(Math.asin(point.y/radius)>0)
            {
                angle=(float)Math.acos(point.x/radius);
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)<Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            else if(Math.asin(point.y/radius)<0&&Math.acos(point.x/radius)>Math.PI/2)
            {
                angle=(float)(2*Math.PI-Math.acos(point.x/radius));
            }
            rotationAngle=(float)Math.PI+rectAngle+ABAngle-angle;
        }
        newPointPosition();
    }
    public String toString()
    {
        return A.x+" "+A.y+"\n"+B.x+" "+B.y+"\n"+C.x+" "+C.y+"\n"+D.x+" "+D.y+"\n";
    }
}