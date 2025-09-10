/*
 * Aadil Ahmed
 * May , 2024
 * To handle when a key is pressed
 */


package gamepackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
    private boolean wPressed, sPressed, aPressed, dPressed, spacePressed; //key boolean

    /*
     * pre: n/a
     * purpose: to check if w pressed
     * post: return true or false
     * 
     */
    public boolean isWPressed() {
        return wPressed;
    }

    /*
     * pre: n/a
     * purpose: to check if s pressed
     * post: return true or false
     * 
     */
    public boolean isSPressed() {
        return sPressed;
    }

    /*
     * pre: n/a
     * purpose: to check if a pressed
     * post: return true or false
     * 
     */
    public boolean isAPressed() {
        return aPressed;
    }

    /*
     * pre: n/a
     * purpose: to check if d pressed
     * post: return true or false
     * 
     */
    public boolean isDPressed() {
        return dPressed;
    }
    
    /*
     * pre: n/a
     * purpose: to check if space pressed
     * post: return true or false
     * 
     */
    public boolean isSpacePressed(){
    	return spacePressed;
    }

    /*
     * pre: KeyEvent e
     * purpose: to check if button is pressed
     * post: n/a
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            wPressed = true;
        } 
        else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            sPressed = true;
        } 
        else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            aPressed = true;
        } 
        else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            dPressed = true;
        }
        else if (keyCode == KeyEvent.VK_SPACE){
        	spacePressed = true;
        }
    }

    /*
     * pre: KeyEvent e
     * purpose: to check if button is released
     * post: n/a
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            wPressed = false;
        } 
        else if (keyCode == KeyEvent.VK_S|| keyCode == KeyEvent.VK_DOWN) {
            sPressed = false;
        } 
        else if (keyCode == KeyEvent.VK_A|| keyCode == KeyEvent.VK_LEFT) {
            aPressed = false;
        } 
        else if (keyCode == KeyEvent.VK_D|| keyCode == KeyEvent.VK_RIGHT) {
            dPressed = false;
        } 
        else if (keyCode == KeyEvent.VK_SPACE){
        	spacePressed = false;
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }


}
