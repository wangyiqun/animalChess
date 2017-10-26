package model;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class MoNiKey {
	public static void main(String[] args) {
		try {
			Robot robot=new Robot();
			robot.delay(5000);
			/*robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);*/
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_D);
			robot.keyRelease(KeyEvent.VK_D);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_F);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_E);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.delay(700);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_SPACE);
		} catch (Exception e) {
		}	
	}
}
