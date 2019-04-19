package ghostCave;

import javax.swing.JFrame;

public class MainGhostCave {

	public static void main(String[] args) {
        JFrame f = new JFrame("Ghost Cave");
        f.setFocusable(true);
        f.setSize(1920,1080);
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameFrame g = new GameFrame();
        f.add(g);
        f.setVisible(true);

	}

}
