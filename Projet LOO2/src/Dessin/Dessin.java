package Dessin;
import processing.core.PApplet;

public class Dessin extends PApplet{

    public void settings(){
        size(1300, 800);
    }

    public void setup(){
        background(255);
    }

    public void draw(){
        fill(0,0,255);
        ellipse(100, 100, 200, 100);
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
