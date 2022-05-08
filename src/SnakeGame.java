import processing.core.PApplet;

import java.util.ArrayList;

public class SnakeGame extends PApplet{

    ArrayList<Point> coords = new ArrayList<>();
    int width = 500;
    int x = width/2;
    int y = width/2;
    int speed = width/20;
    int ydi = 0;
    int xdi = speed;
    int state = 0;
    Point apple = (new Point(random(width),random(width)));
    int snakesiz = 10;

    @Override
    public void settings() {
        size(width,width);

    }

    public void setup() {

        coords.add(new Point(width/2,width/2));

    }

    public void draw(){

        switch (state) {
            case 0:
                System.out.println("press");
                break;
            case 1:

                double headx = coords.get(coords.size() - 1).getX() + xdi;
                double heady = coords.get(coords.size() - 1).getY() + ydi;

                if (headx > width) {headx = 0;}
                if (headx < 0) {headx = width;}
                if (heady > width) {heady = 0;}
                if (heady < 0) {heady = width;}

                if (dist((float) headx,(float)heady,(float)apple.getX(),(float)apple.getY()) <= width/20) {
                    snakesiz += 1;
                    apple = (new Point(random(width),random(width)));
                }



                background(96);

                ellipse((float)apple.getX(),(float)apple.getY(),width/20,width/20);

                for (Point i : coords) {
                    fill(255, 255, 255);
                    ellipse((float) i.getX(), (float) i.getY(), width / 20, width / 20);
                }
                fill(0, 0, 0);
                ellipse((float) coords.get(coords.size() - 1).getX(), (float) coords.get(coords.size() - 1).getY(), width / 20, width / 20);

                for (int i = 0; i < coords.size() - 2; i++) {

                    if (dist((float) headx, (float) heady, (float) coords.get(i).getX(), (float) coords.get(i).getY()) <= 9) {
                        state = 2;
                    }
                }

                if (frameCount % 8 == 0) {
                    coords.add(new Point(headx, heady));

                    if (coords.size() > snakesiz) {
                        coords.remove(0);
                    }

                }

                break;

            case 2:
                System.out.println("game over");
                break;

        }



    }

    public void mousePressed() {
        coords.clear();
        coords.add(new Point(width/2,width/2));
        state = 1;
        int snakesiz = 10;
    }

    public void keyPressed() {
        if (keyPressed) {
            if (keyCode==UP){xdi = 0; ydi = speed*-1;}
            if (keyCode==LEFT){xdi = speed*-1; ydi = 0;}
            if (keyCode==DOWN){xdi = 0; ydi = speed;}
            if (keyCode==RIGHT){xdi = speed; ydi=0;}

        }

    }


    public static void main(String[] args) {
        String[] appt = new String[] {"SnakeGame"};
        PApplet.main(appt);

    }
}
