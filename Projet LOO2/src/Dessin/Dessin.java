package Dessin;
import processing.core.PApplet;

public class Dessin extends PApplet{

    public void settings(){
        size(300, 400);
    }

    public void setup(){
        background(255);
    }

    public void draw(){
        fill(255,0,0);
        ellipse(255, 100, 100, 100);
    }
    
    public static void main(String[] args) {
        String[] appletArgs = new String[] { "Dessin.Dessin" };
        PApplet.main(appletArgs);
    }
}
