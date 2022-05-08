import processing.core.PApplet;
import processing.core.PVector;

public class BirdGame extends PApplet {
    int width = 500;
    int height = 500;
    int y = height / 2;
    int vel = width / 160;
    int points;
    boolean uping = false;
    boolean loser = false;
    int state = 0;
    PVector[] Towers = new PVector[50];


    @Override
    public void settings() {
        size(width,width);

    }

    public void setup() {
        for (int i = 0; i < Towers.length; i++) {
            Towers[i] = new PVector(width * (i + 1) + width, (int) random(height / 5, height * 9 / 10));///tower making
        }
    }

    public void draw() {

        switch (state) {
            case 0:
                System.out.println("press");
                break;
            case 1:


                background(100);
        if (uping == true) {
            y -= vel;
        } else {
            y += vel;
        }///flappy bird motion
        ellipse(width / 3, y, width / 10, height / 10);///drawing the bird
        if (y >= width) {
            y = width;
        }///celling detection
        if (y <= 0) {
            y = 0;
        }///floor detection

        for (PVector i : Towers) {///drawing the towers
            rect(i.x, i.y, width / 10, height);///lower tower
            rect(i.x, 0, width / 10, i.y - width / 3);//upper tower
        }
        for (int i = 0; i < Towers.length; i++) {///moving the towers to the lef
            PVector pp = Towers[i];
            Towers[i] = new PVector(pp.x - width / 160, pp.y);
            if (dist(width / 3, y, pp.x, pp.y) < width / 20 || dist(width / 3, 1, pp.x, 1) < width / 20 && y > pp.y || ///lower pillar detection
                    dist(width / 3, y, pp.x + width / 10, pp.y) < width / 20 ||
                    dist(width / 3, y, pp.x, pp.y - width / 3) < width / 20 || dist(width / 3, 1, pp.x, 1) < width / 20 && y < pp.y - width / 3 ||///upper pillar detection
                    dist(width / 3, y, pp.x + width / 10, pp.y - width / 3) < width / 20) {
                loser = true;
            }
            if (loser == true) {
                textSize(width / 15);
                text("BIG LOSER!!! points: " + points / 100, 0, height / 2);
                fill(86, 10, 0);
            } else {
                textSize(width / 20);
                text("score " + ((int) millis() / 100), width * 7 / 10, height / 10);
                points = millis();
            }


        }
                break;

            case 2:
                System.out.println("game over");
                break;

        }

    }

    public void mousePressed() {
        state = 1;
    }

    public void keyPressed() {///flappy up
        uping = true;

    }

    public void keyReleased() {///gravity (default down)
        uping = false;
    }

    public static void main(String[] args) {
        String[] appt = new String[] {"BirdGame"};
        PApplet.main(appt);
    }

}