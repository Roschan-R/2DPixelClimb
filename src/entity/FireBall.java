/*
 * Roschan Ravindraraj
 * June , 2024
 * To make a fireball
 */

package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FireBall {
    double x, y; //x and y value for fireball
    double xspeed, yspeed; //x and y speed
    int radius; //radius of the ball
    BufferedImage bleft, bright, bleft2, bleft3, bright2, bright3; //ball sprite image
    String direction; //direction of the fireball
    public int spriteCounter = 0; //counter for sprite
    public int spriteNum = 1; //number of sprite used

    int[][] waypoints = { 
            {1009, 265}, {1009, 362}, {227, 391}, {227, 495}, {1009, 506}, {1009, 615},
            {227, 630}, {227, 730}, {1009, 745}, {1009, 857}, {253, 876}}; //all the crucial points where the ball goes to
    int currentWaypoint = 0; //the waypoint used

    public Rectangle firehitbox;
    //constructor for fireball
    public FireBall(double x, double y, double xspeed, double yspeed, int radius) { 
    	//setting all values for the fireball
        this.x = x;
        this.y = y;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.radius = radius;

        direction = "right";
        getBallImage();
    }

    
    /*
     * pre: n/a
     * purpose: to get all the sprites
     * post: n/a
     */
    public void getBallImage() {
        try { //try and catch statement to get all images
            bleft = ImageIO.read(getClass().getResourceAsStream("/player/ballleft.png"));
            bright = ImageIO.read(getClass().getResourceAsStream("/player/ballright.png"));
            bleft2 = ImageIO.read(getClass().getResourceAsStream("/player/bleft2.png"));
            bright2 = ImageIO.read(getClass().getResourceAsStream("/player/bright2.png"));
            bleft3 = ImageIO.read(getClass().getResourceAsStream("/player/bleft3.png"));
            bright3 = ImageIO.read(getClass().getResourceAsStream("/player/bright3.png"));
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
    }

    /*
     * pre: n/a
     * purpose: to update the fireballs location
     * post: n/a
     */
    public void update() {
        
        int x2 = waypoints[currentWaypoint][0]; //gets the x value from coordinate
        int y2 = waypoints[currentWaypoint][1]; //gets y value from coordinate

        if (x < x2) { //if the x value is less than the wanted coordinate, move the fireball to the right 
            x += xspeed;
            direction = "right";
            if (x > x2) x = x2; //when x gets larger then the wanteed coordinate, make the x value the wanted coordinate
        }
        else if (x > x2) { //if the x value is greater then the wanted coordinate, move the fireball to the left
            x -= xspeed;
            direction = "left";
            if (x < x2) x = x2; // if the x value is less than the wanted cooridnate, make the x value the wanted coordinate
        }

        if (y < y2) { //if the y value is less than the wanted coordinate, move the fireball down (add down speed)
            y += yspeed;
            if (y > y2) y = y2; //if the y value is greater than the wanted coordinate, make the y value the wanted coordinate
        }
        else if (y > y2) { //if the y value is greater than the wanted coordinate, move the fireball up
            y -= yspeed;
            if (y < y2) y = y2; //if the y value is less than the wanted coordinate, make the y value the wanted coordinate
        }
        
        
        
       
        if (x == x2 && y == y2) { //if the x and y value of the fireball goes to the wanted location, it moves to the next location
            currentWaypoint++;
        }

        //sprite changer used for dk and mario and peach
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    
    /*
     * pre: Graphics2D g2
     * purpose: to draw the sprite image
     * post: n/a
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //switch statement to switch the sprite based on the direction used
	    switch (direction){
		    case "right":
		    if (spriteNum == 1) {
	                image = bright;
	            } 
	       	 if (spriteNum == 2) {
	                image = bright2;
	            }
	       	 if (spriteNum == 3){
	       		 image = bright3;
	       	 }
		      	break;
		    case "left":
		    if (spriteNum == 1) {
	                image = bleft;
	            } 
	       	 if (spriteNum == 2) {
	                image = bleft2;
	            }
	       	 if (spriteNum == 3){
	       		 image = bleft3;
	       	 }
		    	break;
	    }

        g2.drawImage(image, (int) x - radius, (int) y - radius, 2 * radius, 2 * radius, null); //draws image
        
        
    }

    /*
     * pre: n/a
     * purpose: to get the x value of fireball
     * post: return x
     */
    public double getX() {
        return x;
    }
    
    /*
     * pre: n/a
     * purpose: to get the y value of fireball
     * post: return y
     */
    public double getY() {
        return y;
    }

    /*
     * pre: n/a
     * purpose: to get the radius of fireball
     * post: return radius
     */
    public int getRadius() {
        return radius;
    }
    
    public Rectangle getHitbox() {
    	return firehitbox = new Rectangle((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);

    }
    
}
