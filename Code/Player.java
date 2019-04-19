package ghostCave;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.event.MouseInputListener;

public class Player implements MouseInputListener {

	private BufferedImage player;
	private int x, y, newX, newY, speed, killed;
	private double angle;
	private boolean dead;
	private Gun gun;

	public Player(int x, int y, int speed) {
		gun = new Gun(5, 4);
		this.x = x; 
		this.y = y;
		newX = x;
		newY = y;
		this.speed = speed;
		readPlayerImage();
	}
	
	public void move() {
		double diffX = newX - (x + getCentreX());
		double diffY = newY - (y + getCentreY());
		double abs_dx = Math.abs(diffX);
		double abs_dy = Math.abs(diffY);
	
		if (diffX > 5)
			x = moveInLine(x, speed, abs_dx, abs_dy);
		else if (diffX < -5)
			x = moveInLine(x, -speed, abs_dx, abs_dy);
		if (diffY > 5)
			y = moveInLine(y, speed, abs_dy, abs_dx);
		else if (diffY < -5)
			y = moveInLine(y, -speed, abs_dy, abs_dx);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDead(boolean b) {
		dead = b;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getKilled() {
		return killed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCentreX() {
		return player.getWidth() / 2;
	}
	
	public int getCentreY() {
		return player.getHeight() / 2;
	}
	
	public Gun getGun() {
		return gun;
	}
	
	private int moveInLine(int coord, int speed, double diff1, double diff2) {
		if (diff2 == 0 || diff1 >= diff2)
			coord += speed;
		else if (diff1 < diff2)
			coord += (int) (Math.round(speed * (diff1 / diff2)));
		return coord;
	}
	
	private void readPlayerImage() {
		player = FilePath.readImage("\\src\\PlayerImage\\player.png");
	}

	public void drawPlayerImage(Graphics2D g2d) {
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.scale(0.9, 0.9);
		at.rotate(angle, getCentreX(), getCentreY());
		g2d.drawImage(player, at, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(), my = e.getY();
		if (e.getButton() == 1) {
			if(my > 50) {
				newX = mx;
				newY = my;
			}
		}
		if (e.getButton() == 3) {
			int batteryLife = gun.getBatteryLife();
			if (batteryLife > 0) {
				gun.shoot();
				Ghost[] ghosts = GameFrame.getGhosts();
				for (int i = 0; i < ghosts.length; i++) {
					Ghost g = ghosts[i];
					if (mx >= g.getX() - 5 && mx <= g.getX() + g.getWidth() + 5 && 
							my >= g.getY() - 5 && my <= g.getY() + g.getHeight() + 5) {
						g.setDead(true);
						killed++;
					}
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		angle = Math.atan2(e.getY() - (y + getCentreY()), e.getX() - (x + getCentreX())) + Math.PI/2;
	}

}