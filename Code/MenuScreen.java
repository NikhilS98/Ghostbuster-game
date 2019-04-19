package ghostCave;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuScreen implements MouseInputListener {

	private BufferedImage menu, playbutton;
	private File playSoundFile;
	private int x, y;
	private boolean play, start;

	public MenuScreen() {
		x = 840;
		y = 630;
		readMenuImage();
		readPlayButtonImage();
		playSoundFile = FilePath.readFile("\\src\\Sound\\GhostBustersTheme.wav");
	}

	public void drawMenu(Graphics2D g2d) {
		g2d.drawImage(menu, 0, 0, null);
		g2d.drawImage(playbutton, x, y, null);
	}
	
	public void setPlay(boolean b) {
		play = b;
	}
	
	public void setStart(boolean b) {
		start = b;
	}
	
	public boolean isPlayClicked() {
		return play;
	}
	
	public boolean isStarted() {
		return start;
	}

	private void playSound() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(playSoundFile));
			clip.start();
			Thread.sleep((long) 1000);
		} catch (Exception e) {

		}
	}

	private void readMenuImage() {
		menu = FilePath.readImage("\\src\\Others\\menu.png");
	}

	private void readPlayButtonImage() {
		playbutton = FilePath.readImage("\\src\\Others\\playbutton.jpg");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!play) {
			int x = e.getX(), y = e.getY();
			if (x >= this.x && x <= this.x + 222 && y >= this.y && y <= this.y + 66) {
				playSound();
				play = true;
				start = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		e.consume();

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (x >= this.x && x <= this.x + 222 && y >= this.y && y <= this.y + 66) {
			this.y = 620;
		} else {
			this.y = 630;
		}
	}
}
