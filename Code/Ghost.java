package ghostCave;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Ghost implements ActionListener{

	private BufferedImage ghostImage;
	private int x, y, speed;
	private boolean dead, respawnable;
	private Timer respawnTimer;
	
	public Ghost(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		readGhostImage();
	}
	
	public Ghost(int x, int y, int speed, boolean respawnable, int respawnTime) {
		this(x, y, speed);
		this.respawnable = respawnable;
		respawnTimer = new Timer(respawnTime * 1000, this);
	}

	public boolean move(int targetX, int targetY) {
		double diffX = targetX - x;
		double diffY = targetY - y;
		double abs_dx = Math.abs(diffX);
		double abs_dy = Math.abs(diffY);
	
		if (diffX > 30)
			x = moveInLine(x, speed, abs_dx, abs_dy);
		else if (diffX < -30)
			x = moveInLine(x, -speed, abs_dx, abs_dy);
		if (diffY > 30)
			y = moveInLine(y, speed, abs_dy, abs_dx);
		else if (diffY < -30)
			y = moveInLine(y, -speed, abs_dy, abs_dx);
		
		return diffX <= 30 && diffX >= -30 && diffY <= 30 && diffY >= -30;
	}
	
	public void setDead(boolean b) {
		dead = b;
		if(respawnable) {
			respawnTimer.start();
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return ghostImage.getWidth();
	}
	
	public int getHeight() {
		return ghostImage.getHeight();
	}
	
	public int getCentreX() {
		return ghostImage.getWidth() / 2;
	}
	
	public int getCentreY() {
		return ghostImage.getHeight() / 2;
	}
	
	private int moveInLine( int coord, int speed, double diff1, double diff2 ) {
		if (diff2 == 0 || diff1 >= diff2)
			coord += speed;
		else if (diff1 < diff2)
			coord += (int) (Math.round(speed * (diff1 / diff2)));
		return coord;
	}
	
	private void readGhostImage() {
		ghostImage = FilePath.readImage("\\src\\GhostImage\\ghost3.png");
		
	}
	
	public void drawGhostImage(Graphics2D g2d) {
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.scale(0.3, 0.3);
		g2d.drawImage(ghostImage, at, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setDead(false);
		respawnTimer.stop();
	}

}
