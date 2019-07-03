package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PrintCal {
	// 根据年月返回该月的天数和第一天星期几的map集合
	private static Map<String, Integer> showCal(Integer year, Integer month) {
		// 获取当月的LocalDate对象
		LocalDate localDate = LocalDate.of(year, month, 1);
		// 获取该月天数
		Integer monthLength = localDate.lengthOfMonth();
		// 获取一号是星期几
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		// 用map存取该月信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("monthLength", monthLength);
		map.put("dayOfWeek", dayOfWeek.getValue());
		return map;
	}

	// 获取星期数组
	public static Object[] getWeek() {
		Object[] array = { "一", "二", "三", "四", "五", "六", "日" };
		return array;
	}

	// 获取内容数组
	public static Object[][] getDay(Integer year, Integer month) {

		Map<String, Integer> calMap = PrintCal.showCal(year, month);
		Integer monthLength = calMap.get("monthLength");
		Integer dayOfWeek = calMap.get("dayOfWeek");
		Integer adjust = monthLength + dayOfWeek - 1;
		Integer row = null;
		if ((adjust % 7) == 0) {
			row = adjust / 7;
		} else {
			row = adjust / 7 + 1;
		}
		Object[][] array = new Object[row][7];

		// 真实的日期值
		int realNum = 1;
		// 一共row行
		for (int i = 1; i <= row; i++) {
			// 每一行的数组
			Object[] newArray = new Object[7];
			// 给每一行添加数据
			for (int k = 0; k < 7; k++) {
				// p为调整后的排序数
				int p = 7 * i + k - 6;

				if (p < dayOfWeek) {
					newArray[k] = "";
				} else if (p > monthLength + dayOfWeek - 1) {
					newArray[k] = "";
				} else {
					newArray[k] = realNum++;
				}

			}

			array[i - 1] = newArray;
		}
		// 输出测试
		// System.out.println(row);
		// for (int p = 0; p < array.length; p++) {
		// System.out.print("\n第" + (p + 1) + "行：");
		// for (int i = 0; i < array[p].length; i++) {
		// System.out.print(" " + array[p][i]);
		// }
		// }
		return array;
	}

	// 测试日历在控制台输出
	public static void main(String[] args) {
		Map<String, Integer> calMap = PrintCal.showCal(2019, 7);
		Integer monthLength = calMap.get("monthLength");
		Integer dayOfWeek = calMap.get("dayOfWeek");
		System.out.println("\t\t2019年7月日历");
		System.out.print("Monday  Tuesday Wednesday Thursday Friday Saturday Sunday\n");
		for (int i = 1; i < dayOfWeek; i++) {
			System.out.print("\t");
		}
		for (int i = 0; i < monthLength; i++) {
			int k = i + dayOfWeek;
			if (k != 1 && k % 7 == 1) {
				System.out.print("\n");
			}
			System.out.print("   " + (i + 1) + "\t");

		}
		System.out.println("\n------分割线--------");
		getDay(2019, 7);
	}

}
