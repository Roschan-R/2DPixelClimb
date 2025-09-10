/*
 * Aadil Ahmed
 * May , 2024
 * Parent entity class
 */

package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {
	
	public int x, y; //x y coordinate for entity
	public int speed; //speed value
	
	public BufferedImage left1, left2, left3, right1, right2, right3, //all of mario images
	fallleft, fallright, jumpleft, jumpright, climbleft, climbright, idle;
	 
	public String direction; //direction for entity
	public int spriteCounter = 0; //sprite counter
	public int spriteNum = 1;
	public Rectangle hitbox; //hitbox for character 
	//public boolean collisionOn = false;
	
}
