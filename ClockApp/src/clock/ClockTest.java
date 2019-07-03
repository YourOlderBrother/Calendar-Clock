package clock;

import java.awt.Graphics;

import javax.swing.JComponent;

public class ClockTest extends JComponent {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(3, 3, 50, 50);
		g.drawLine(100, 1, 1, 100);
	}

	public static void main(String[] args) {
		// JFrame j = new JFrame("test");
		// j.getContentPane().add(new ClockTest());
		// j.setSize(400, 400);
		// j.setVisible(true);
		// JFrame j = new JFrame("test");
		// JPanel p = new JPanel(new BorderLayout());
		//
		// p.add(new ClockTest());
		// p.setPreferredSize(new Dimension(400, 400));
		// p.setBounds(0, 0, 400, 400);
		// p.setVisible(true);
		// j.setSize(500, 500);
		// j.setLayout(null);
		// j.getContentPane().add(p);
		// j.setVisible(true);

	}
}
