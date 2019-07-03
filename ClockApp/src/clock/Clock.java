package clock;

import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalTime;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Clock extends JComponent {

	public Clock() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 画圆
		g.drawOval(0, 0, 250, 250);
		g.fillOval(125, 125, 6, 6);
		// 画60个小点
		for (int i = 0; i < 60; i++) {
			double x1 = 125 + 115 * Math.cos(6 * i * Math.PI / 180);
			double y1 = 125 + 115 * Math.sin(6 * i * Math.PI / 180);
			if (i == 0 || i == 15 || i == 30 || i == 45) {
				g.fillOval((int) x1, (int) y1, 6, 6);
			} else {
				g.fillOval((int) x1, (int) y1, 3, 3);
			}
		}
		// 画4个数字
		g.drawString("3", 230, 132);
		g.drawString("6", 125, 237);
		g.drawString("9", 21, 135);
		g.drawString("12", 120, 30);

		new Thread(() -> {
			Graphics g1 = getGraphics();
			LocalTime time = LocalTime.now();
			int h = time.getHour();
			if (h > 12) {
				h -= 12;
			}
			int m = time.getMinute();
			int s = time.getSecond();

			g1.drawLine(128, 128, (int) (128 + 40 * Math.sin(30 * h * Math.PI / 180)),
					(int) (128 - 40 * Math.cos(30 * h * Math.PI / 180)));
			g1.drawLine(128, 128, (int) (128 + 70 * Math.sin(6 * m * Math.PI / 180)),
					(int) (128 - 70 * Math.cos(6 * m * Math.PI / 180)));
			g1.drawLine(128, 128, (int) (128 + 90 * Math.sin(6 * s * Math.PI / 180)),
					(int) (128 - 90 * Math.cos(6 * s * Math.PI / 180)));
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time = LocalTime.now();
				g1.setColor(Color.WHITE);
				g1.drawLine(128, 128, (int) (128 + 40 * Math.sin(30 * h * Math.PI / 180)),
						(int) (128 - 40 * Math.cos(30 * h * Math.PI / 180)));
				g1.drawLine(128, 128, (int) (128 + 70 * Math.sin(6 * m * Math.PI / 180)),
						(int) (128 - 70 * Math.cos(6 * m * Math.PI / 180)));
				g1.drawLine(128, 128, (int) (128 + 90 * Math.sin(6 * s * Math.PI / 180)),
						(int) (128 - 90 * Math.cos(6 * s * Math.PI / 180)));
				g1.setColor(Color.black);

				h = time.getHour();
				if (h > 12) {
					h -= 12;
				}
				m = time.getMinute();
				s = time.getSecond();
				g1.drawLine(128, 128, (int) (128 + 40 * Math.sin(30 * h * Math.PI / 180)),
						(int) (128 - 40 * Math.cos(30 * h * Math.PI / 180)));
				g1.drawLine(128, 128, (int) (128 + 70 * Math.sin(6 * m * Math.PI / 180)),
						(int) (128 - 70 * Math.cos(6 * m * Math.PI / 180)));
				g1.drawLine(128, 128, (int) (128 + 90 * Math.sin(6 * s * Math.PI / 180)),
						(int) (128 - 90 * Math.cos(6 * s * Math.PI / 180)));

			}
		}).start();
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("test");
		f.add(new Clock());
		f.setResizable(false);
		f.setSize(300, 300);
		f.getContentPane().setBackground(Color.WHITE);
		f.setVisible(true);
		// LocalTime time = LocalTime.now();
		// System.out.println(time.getHour());
		// System.out.println(time.getMinute());
		// System.out.println(time.getSecond());
	}
}
