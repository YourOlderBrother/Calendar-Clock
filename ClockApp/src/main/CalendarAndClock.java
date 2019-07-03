package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calendar.PrintCal;
import clock.Clock;
import windows.CreateJFrame;

public class CalendarAndClock {
	public static void main(String[] args) {
		// 定义当前的年月，在定时器中取值
		LocalDateTime ldt = LocalDateTime.now();
		Integer year = ldt.getYear();

		Integer month = ldt.getMonthValue();
		Integer day = ldt.getDayOfMonth();
		Integer dayOfWeek = ldt.getDayOfWeek().getValue();
		// 获取主窗口
		JFrame jf = new JFrame("日历 by zhh");
		// 生成JPanel
		JPanel jp = CreateJFrame.createJPanel(year, month);
		jf.setLayout(null);
		jf.add(jp);
		jf.setResizable(false);
		jf.setSize(600, 400);
		// 将星期几转化为中文
		Object[] weekArray = PrintCal.getWeek();
		String str = year + "年" + month + "月" + day + "日  星期" + weekArray[dayOfWeek - 1] + "  "
				+ ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

		JLabel label1 = new JLabel(str);
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label1.setBounds(150, 10, 250, 60);
		jf.getContentPane().setBackground(Color.WHITE);
		jf.add(label1);
		// 定时器
		new Thread(() -> {
			while (true) {
				// 获取当前日期时间
				LocalDateTime newLdt = LocalDateTime.now();
				String newStr1 = newLdt.format(DateTimeFormatter.ofPattern("YYYY年M月d日 星期"));
				String newStr2 = newLdt.format(DateTimeFormatter.ofPattern("  HH:mm:ss"));
				Integer newDayOfWeek = newLdt.getDayOfWeek().getValue();
				String newStr = newStr1 + weekArray[newDayOfWeek - 1] + newStr2;
				label1.setText(newStr);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		// 获取查询框
		Container queryCon = CreateJFrame.createQuery(year, month);
		// 给查询按钮添加点击事件(Lambda表达式)
		JButton button = (JButton) queryCon.getComponent(5);
		JTextField textYear = (JTextField) queryCon.getComponent(1);
		JTextField textMonth = (JTextField) queryCon.getComponent(3);
		button.addActionListener((e) -> {
			Integer queryYear = Integer.parseInt(textYear.getText());
			Integer queryMonth = Integer.parseInt(textMonth.getText());
			jf.remove(jp);
			JPanel newJp = CreateJFrame.createJPanel(queryYear, queryMonth);
			jf.add(newJp);
			jf.setVisible(true);
		});
		// 向前向后按钮添加事件
		JButton button1 = (JButton) queryCon.getComponent(0);
		button1.addActionListener((e) -> {
			Integer queryYear = Integer.parseInt(textYear.getText());
			Integer queryMonth = Integer.parseInt(textMonth.getText());
			jf.remove(jp);
			JPanel newJp = null;
			if (queryMonth == 1) {
				newJp = CreateJFrame.createJPanel(queryYear - 1, 12);
				textYear.setText("" + (queryYear - 1));
				textMonth.setText("12");
			} else {
				newJp = CreateJFrame.createJPanel(queryYear, queryMonth - 1);
				textMonth.setText("" + (queryMonth - 1));
			}
			jf.add(newJp);
			jf.setVisible(true);
		});
		JButton button2 = (JButton) queryCon.getComponent(6);
		button2.addActionListener((e) -> {
			Integer queryYear = Integer.parseInt(textYear.getText());
			Integer queryMonth = Integer.parseInt(textMonth.getText());
			jf.remove(jp);
			JPanel newJp = null;
			if (queryMonth == 12) {
				newJp = CreateJFrame.createJPanel(queryYear + 1, 1);
				textYear.setText("" + (queryYear + 1));
				textMonth.setText("1");
			} else {
				newJp = CreateJFrame.createJPanel(queryYear, queryMonth + 1);
				textMonth.setText("" + (queryMonth + 1));
			}
			jf.add(newJp);
			jf.setVisible(true);
		});
		queryCon.setBounds(10, 80, 250, 50);
		jf.add(queryCon);
		// 添加时钟窗口
		Container clock = new Clock();
		clock.setBounds(310, 90, 300, 300);
		clock.setVisible(true);
		jf.add(clock);
		// 关闭窗口结束进程
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
