package ghostCave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class Gun implements ActionListener{
	private File fireSound;
	private int battery, maxBattery;
	private Timer reloadTimer;
	private boolean reloading;

	public Gun(int battery, int reloadRate) {
		this.battery = battery;
		maxBattery = battery;
		reloadTimer = new Timer(reloadRate * 1000, this);
		fireSound = FilePath.readFile("\\src\\Sound\\firesound.wav");
	}

	public void shoot() {
		if(!reloading) {
			reloadTimer.start();
			reloading = true;
		}
		if (battery != 0) {
			playFireSound();
			battery--;
		}
	}

	public void setBatteryLife(int battery) {
		this.battery = battery;
	}

	public int getBatteryLife() {
		return battery;
	}

	public void drawBattery(Graphics g, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 30);
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 100 * battery / maxBattery, 30);
	}

	private void playFireSound() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(fireSound));
			clip.start();
			// Thread.sleep((long) 1);
		} catch (Exception e) {

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(battery < maxBattery)
			battery++;
		else {
			reloading = false;
			reloadTimer.stop();
		}
		
	}
}
