package com.froad.comon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对账分组算法
 * 
 * @author Administrator
 * 
 */
public class GroupUtil {

	public static List<Map<String, Object>> sort(String[] a) {

		Set<String> set = new HashSet<String>();
		group(a, set);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (String group : set) {
			int index = group.split("[,]").length;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("index", index);
			map.put("name", group);
			list.add(map);
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer a = (Integer) o1.get("index");
				Integer b = (Integer) o2.get("index");
				return b - a;
			}

		});

		return list;
	}

	/**交集组合个数*/
	public static List<String[]> sortArray(String[] a) {

		Set<String> set = new HashSet<String>();
		group(a, set);

		List<String[]> list = new ArrayList<String[]>();
		for (String group : set) {
			String[] array = group.split("[,]");
			list.add(array);
		}

		Collections.sort(list, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				Integer a = o1.length;
				Integer b = o2.length;
				return b - a;
			}

		});

		return list;
	}

	public static void group(String[] n, Set<String> set) {
		int length = n.length;
		String str = "";
		for (int i = 0; i < length; i++) {
			str += "," + n[i];
			String[] temp = new String[length - 1];
			if (temp.length >= 1) {
				int index = 0;
				for (int j = 0; j < length; j++) {

					if (n[i] != n[j]) {
						temp[index++] = n[j];
					}

				}
				group(temp, set);
			}
		}
		set.add(str.substring(1));
	}

	public static void main(String[] args) {
		String[] str = { "aa", "bb", "cc", "dd", "ee" };
		List<String[]> list = sortArray(str);
		for (int i = 0; i < list.size(); i++) {
			String[] item=list.get(i);
			System.out.print("index:"+(i+1)+"   ");
			for (int j = 0; j < item.length; j++) {
				System.out.print(item[j]+",");
			}
			System.out.println("");
			
		}
	}

}
