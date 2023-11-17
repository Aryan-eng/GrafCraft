package grafCraft;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Display extends PApplet {

	public ArrayList<Linear> lines = new ArrayList<Linear>();
	public ArrayList<Circle> circles = new ArrayList<Circle>();
	public ArrayList<Quadratic> quadratics = new ArrayList<Quadratic>();
	public ArrayList<Ellipse> ellipses = new ArrayList<Ellipse>();
	public float ox = 0;
	public float oy = 0;
	public float s = 80;
	public float freezeX;
	public float freezeY;
	
	public void settings() {
		
		size(800, 800);
		
	}
	
	public void setup() {
		
		
	}
	
	public void draw() {
		background(255);
		translate(width/2, height/2);
		grid();
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
	    ellipse(s*h+ox,-(s*k)+oy,s*a,s*b);
	    strokeWeight(1);
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
		  
		for(float i = ox + s; i<width/2;i+= s){
			line(i,-height/2,i,height/2);
			// println(i+","+(ox+i));
		}
		for(float i = ox-s; i>-width/2;i-=s){
			line(i,-height/2,i,height/2);
		}
		for(float i=oy+s; i<height/2;i+=s){
			line(-width/2,i,width/2,i);
		}
		for(float i=oy-s; i>-height/2;i-=s){
			line(-width/2,i,width/2,i);
		}
	}
	
	public void mouseWheel(MouseEvent event){ 
		s+=event.getCount();
		translate(mouseX, mouseY);
		if(s<10){
			s=10;
		}
		if(s>width/2){
			s=width/2;
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
