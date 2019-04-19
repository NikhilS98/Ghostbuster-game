package ghostCave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GameFrame extends JPanel implements ActionListener {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final long serialVersionUID = 1L;
	private MenuScreen menuScreen;
	private Timer gameLoopTimer;
	private Player player;
	private static Ghost[] ghosts;
	private int restartTimer;

	public GameFrame() {
		restartTimer = 200;

		menuScreen = new MenuScreen();
		addMouseListener(menuScreen);

		createPlayer();

		ghosts = new Ghost[5];

		createGhosts();

		gameLoopTimer = new Timer(16, this);
		gameLoopTimer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (!menuScreen.isPlayClicked()) {
			menuScreen.drawMenu(g2d);
		} else {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, 1920, 1080);
			
			//Color current = g.getColor();
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 45));
			g.drawString(player.getKilled() + "", 1650, 70);
			//g.setColor(current);
			
			if(player.isDead()) {
				g2d.drawImage(FilePath.readImage("\\src\\Others\\game over.jpg"), 500, 250, null);
				if (restartTimer-- == 0) {
					menuScreen.setPlay(false);
					player.setDead(false);
					
					createPlayer();
					
					createGhosts();
					
					restartTimer = 200;
				}
			}
			else {
				addMouseMotionListener(player);
				player.move();
				
				player.getGun().drawBattery(g, 1750, 40);
				player.drawPlayerImage(g2d);
				for (int i = 0; i < ghosts.length; i++) {
					if(ghosts[i].isDead()) {
						
					}
					else {
						if (ghosts[i].move(player.getX() + player.getCentreX(), 
								player.getY() + player.getCentreY())) {
							player.setDead(true);
						}
						ghosts[i].drawGhostImage(g2d);
					}
					
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();

	}
	
	private void createPlayer() {
		player = new Player(900, 550, 10);
		addMouseListener(player);
	}
	
	private void createGhosts() {
		int x, y, speed;
		for (int i = 0; i < ghosts.length; i++) {
			do {
				x = (int) (Math.random() * screenSize.getWidth());
				y = (int) (Math.random() * screenSize.getHeight());
			} while ((x > player.getX() - 100 && x < player.getX() + 50) || 
					(y > player.getY() - 100 && y < player.getY() + 50));

			speed = (int) (1 + Math.random() * 2);
			ghosts[i] = new Ghost(x, y, speed, true, 3);
		}
	}

	public static Ghost[] getGhosts() {
		return ghosts;
	}
}
