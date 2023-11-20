package grafCraft;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Display extends PApplet {

	public ArrayList<Linear> lines = new ArrayList<Linear>();
	public ArrayList<Circle> circles = new ArrayList<Circle>();
	public ArrayList<Quadratic> quadratics = new ArrayList<Quadratic>();
	public ArrayList<Ellipse> ellipses = new ArrayList<Ellipse>();
	public ArrayList<Hyperbola> hyperbolas = new ArrayList<Hyperbola>();
	public ArrayList<Equation> equations = new ArrayList<Equation>();
	public float ox = 0;
	public float oy = 0;
	public float s = 80;
	public float freezeX;
	public float freezeY;
	float sublineO = 100;
	float rs = 80;
	float snap = 1;
	
	public void settings() {
		
		size(800, 800);
		
	}
	
	public void setup() {
		surface.setResizable(true);
	}
	
	public void draw() {
		background(255);
		translate(width/2, height/2);
		grid();
		s=rs*snap;
		mouseWheel();
		mousePressed();
		mouseDragged();
		display();
	}
	
	
	public void Linear(float m, float b) {
		//stroke(random(0, 255), random(0, 255), random(0, 255));
		for(float i=-width/2 - ox; i<width/2 - ox; i+=0.01){
			strokeWeight(2);
			//stroke((float) Math.random() * 255, (float) Math.random() * 255, (float) Math.random() * 255);
		    point(i+ox,-m*i-s*b+oy);
		    strokeWeight(1);
		}
	}
	
	public void Circle(float h, float k, float r) {
		noFill();
	    strokeWeight(2);
	    circle(s*h+ox,-s*k+oy,s*r*2);
	    strokeWeight(1);
	}
	
	public void Quadratic(float a, float b, float c) {
		for(float i=-width/2 - ox; i<width/2 - ox; i+=0.01){
		      strokeWeight(2);
		      point(i*s+ox,-s*(a*pow(-i,2)-b*(-i)+c)+oy);
		      strokeWeight(1);
		}
	}
	
	public void Ellipse(float h, float k, float a, float b) {
		noFill();
	    strokeWeight(2);
	    ellipse(s*h+ox,-(s*k)+oy,s*2*a,s*2*b);
	    strokeWeight(1);
	}
	
	public void verticalHyperbola(float k, float a, float h, float b) {
		for(float i=-width/2 - ox; i<width/2 - ox; i+=0.05){
	        strokeWeight(2);
	        stroke(0);
	        float y=pow(b,2)*(1+pow(i-h,2)/pow(a,2));
	        point(s*i+ox,s*(sqrt(y)+k)+oy);
	        point(s*i+ox,s*(-sqrt(y)-k)+oy);
	        strokeWeight(1);
	     }
	}
	
	public void horizantalHyperbola(float h, float a, float k, float b) {
		for(float i=-width/2 - ox; i<width/2 - ox; i+=0.05){
			strokeWeight(2);
		     float y=-pow(b,2)*(a+(i)-h)*(a-(i)+h)/(pow(a,2));
		     point(s*i+ox,s*(sqrt(y)+k)+oy);
		     point(s*i+ox,s*(-sqrt(y)-k)+oy);
		     strokeWeight(1);
	     }
	}
	
	public void Equation(Equation e) {
		for (int i = 1; i < e.pointsX.size(); i++) {
			strokeWeight(2);
			line(e.pointsX.get(i-1), e.pointsY.get(i-1), e.pointsX.get(i), e.pointsY.get(i));
		}
	}
	
	public void display() {
		for (int i = 0; i < lines.size(); i++) {
			Linear(lines.get(i).m, lines.get(i).b);
		}
		for (int i = 0; i < circles.size(); i++) {
			Circle(circles.get(i).h, circles.get(i).k, circles.get(i).r);
		}
		for (int i = 0; i < quadratics.size(); i++) {
			Quadratic(quadratics.get(i).a, quadratics.get(i).b, quadratics.get(i).c);
		}
		for (int i = 0; i < ellipses.size(); i++) {
			Ellipse(ellipses.get(i).h, ellipses.get(i).k, ellipses.get(i).a, ellipses.get(i).b);	
		}
		for (int i = 0; i < hyperbolas.size(); i++) {
			if (hyperbolas.get(i).vertical == true) {
				verticalHyperbola(hyperbolas.get(i).k, hyperbolas.get(i).a, hyperbolas.get(i).h, hyperbolas.get(i).b);
			}
			else {
				horizantalHyperbola(hyperbolas.get(i).h, hyperbolas.get(i).a, hyperbolas.get(i).k, hyperbolas.get(i).b);
			}
		}
		for (int i = 0; i < equations.size(); i++) {
			Equation(equations.get(i));
		}
		
	}
	
	public void run(){
		String[] processingArgs = {"grafCraft.Display"};
		PApplet.runSketch(processingArgs, this);
	}
	
	public void grid(){
		  stroke(255,0,0);
		  line(-width/2,oy,width/2,oy);
		  line(ox,-height/2,ox,height/2);
		  stroke(0);
		  
		  for(float i=ox+rs/2; i<width/2;i+=rs){
		    strokeWeight(0.5f);
		    stroke(128,128,128,sublineO);
		    line(i,-height/2,i,height/2);
		    strokeWeight(1);
		    stroke(0);
		    line(i+rs/2,-height/2,i+rs/2,height/2);
		  }
		  for(float i=ox-rs/2; i>-width/2;i-=rs){
		    strokeWeight(0.5f);
		    stroke(128,128,128,sublineO);
		    line(i,-height/2,i,height/2);
		    strokeWeight(1);
		    stroke(0);
		    line(i-rs/2,-height/2,i-rs/2,height/2);
		  }
		  for(float i=oy+rs/2; i<height/2;i+=rs){
		    strokeWeight(0.5f);
		    stroke(128,128,128,sublineO);
		    line(-width/2,i,width/2,i);
		    strokeWeight(1);
		    stroke(0);
		    line(-width/2,i+rs/2,width/2,i+rs/2);
		  }
		  for(float i=oy-rs/2; i>-height/2;i-=rs){
		    strokeWeight(0.5f);
		    stroke(128,128,128,sublineO);
		    line(-width/2,i,width/2,i);
		    strokeWeight(1);
		    stroke(0);
		    line(-width/2,i-rs/2,width/2,i-rs/2);
		  }
		//display numbers in the positive x
		  fill(0);
		  

		    for(float i=ox+rs; i<width/2;i+=rs){
		    textSize(10);
		    text(String.valueOf((float)((i/rs)/snap)-ox/s),i+rs/4,oy+rs/4);
		    }
		    for(float i=ox-rs; i>-width/2;i-=rs){
		    textSize(10);
		    text(String.valueOf((float)((i/rs)/snap)-ox/s),i+rs/4,oy+rs/4);
		    }
		    for(float i=oy+rs; i<height/2;i+=rs){
		    textSize(10);
		    text(String.valueOf(-(float)((i/rs)/snap)+oy/s),ox+rs/4,i);
		    }
		    for(float i=oy-rs; i>-height/2;i-=rs){
		    textSize(10);
		    text(String.valueOf(-(float)((i/rs)/snap)+oy/s),ox+rs/4,i-rs/8);
		    }
		  

	}
	
	public void mouseWheel(MouseEvent event){ 
		rs+=event.getCount();
		s=rs*snap;
		if(rs>100){
			rs=50;
		    snap=snap*2;
		}
		if (rs<25){
			rs=50;
		    snap=snap/2;
		}
		if(s<1){
		    s=1;
		}
	}

	public void mousePressed(){
	  freezeX=ox-(mouseX-width/2);
	  freezeY=oy-(mouseY-height/2);
	}
	
	public void mouseDragged(){
	  ox=(mouseX-width/2)+freezeX;
	  oy=(mouseY-height/2)+freezeY;
	}

	//public void 


	
}
