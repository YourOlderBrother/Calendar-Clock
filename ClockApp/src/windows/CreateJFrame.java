package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import calendar.PrintCal;

public class CreateJFrame {
	// 生成日历窗口JFrame
	// public static JFrame mainWindows(Integer year, Integer month) {
	// JFrame jf = new JFrame("日历 by zhh");
	// JPanel jp = new JPanel(new BorderLayout());
	// JTable jt = createTable(year, month);
	// jt.getTableHeader().setReorderingAllowed(false);
	// jt.getTableHeader().setResizingAllowed(false);
	// jp.add(jt.getTableHeader(), BorderLayout.NORTH);
	// jp.add(jt, BorderLayout.CENTER);
	// jp.setPreferredSize(new Dimension(350, 400));
	// jp.setBounds(0, 130, 270, 270);
	// jf.setLayout(null);
	// jf.add(jp);
	// // jf.setContentPane(jp);
	// jf.setResizable(false);
	// jf.setSize(600, 400);
	// return jf;
	//
	// }

	public static JPanel createJPanel(Integer year, Integer month) {
		JPanel jp = new JPanel(new BorderLayout());
		JTable jt = CreateJFrame.createTable(year, month);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		jp.add(jt.getTableHeader(), BorderLayout.NORTH);
		jp.add(jt, BorderLayout.CENTER);
		jp.setPreferredSize(new Dimension(350, 400));
		jp.setBounds(0, 130, 270, 270);
		return jp;
	}
	// 获取JTable数据内容

	public static JTable createTable(Integer year, Integer month) {
		// 根据该月份的内容数组创建JTable
		JTable t = new JTable(PrintCal.getDay(year, month), PrintCal.getWeek());
		// 设置内容居中
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(SwingConstants.CENTER);
		t.setDefaultRenderer(Object.class, dtc);
		// 设置无法选中行
		t.setRowSelectionAllowed(false);
		// 设置表内容无网格线
		t.setShowGrid(false);
		// 设置格子行高
		t.setRowHeight(35);
		// 设置表头行高
		t.getTableHeader().setPreferredSize(new Dimension(t.getWidth(), 30));
		t.getTableHeader().setBackground(Color.WHITE);
		t.setVisible(true);
		return t;
	}

	// 创造一个查询月份的组合框
	public static Container createQuery(Integer year, Integer month) {
		// 创建文本标签
		JLabel label1 = new JLabel("年");
		JLabel label2 = new JLabel("月");
		// 创建年月输入框
		JTextField yearText = new JTextField(4);
		JTextField monthText = new JTextField(2);
		// 设置边框格式
		MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(180, 205, 230));
		yearText.setBorder(border);
		monthText.setBorder(border);
		// 设置正则
		yearText.setDocument(new MyRegExp("^[0-9]{0,4}$"));
		monthText.setDocument(new MyRegExp("^(0?[1-9]|1[0-2])$"));
		// 设置文本居中
		yearText.setHorizontalAlignment(JTextField.CENTER);
		monthText.setHorizontalAlignment(JTextField.CENTER);
		// 添加查询按钮
		JButton jb = new JButton("查询");
		jb.setPreferredSize(new Dimension(50, 25));
		jb.setMargin(new Insets(0, 0, 0, 0));
		jb.setBackground(new Color(180, 205, 230));
		// 添加前一个月和后一个月按钮
		JButton jb1 = new JButton("◀");
		JButton jb2 = new JButton("▶");
		jb1.setMargin(new Insets(0, 0, 0, 0));
		jb2.setMargin(new Insets(0, 0, 0, 0));
		jb1.setBackground(null);
		jb2.setBackground(null);
		jb1.setBorder(null);
		jb2.setBorder(null);
		jb1.setPreferredSize(new Dimension(30, 25));
		jb2.setPreferredSize(new Dimension(30, 25));
		// 按顺序添加没款
		Container c = new Container();
		c.setLayout(new FlowLayout());
		c.add(jb1);
		c.add(yearText);
		yearText.setText(year.toString());
		c.add(label1);
		c.add(monthText);
		monthText.setText(month.toString());
		c.add(label2);
		c.add(jb);
		c.add(jb2);
		return c;
	}

	public static void main(String[] args) {
		// Pattern p1 = Pattern.compile("^[0-9]{4}$");
		// Matcher m1 = p1.matcher("你好");
		// System.out.println(m1.matches());
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		ldt = LocalDateTime.of(2019, 7, 1, 3, 4);
		System.out.println(ldt);
		LocalDateTime newLdt = LocalDateTime.now();
		String newStr = newLdt.format(DateTimeFormatter.ofPattern("YYYY年"));
		System.out.println(newStr);
	}
}
