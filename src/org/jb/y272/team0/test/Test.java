package org.jb.y272.team0.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.jb.y272.team0.entity.SysRight;

public class Test {
	public static void main(String[] args) {
		// s();
		List<SysRight> list = getRights();
		List<SysRight> slist=getChild(list,"L01");
		Iterator<SysRight> itr = slist.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().getRightText());
		}
	}

	public static boolean isParent(String rightCode, String rightCodeTo) {
		if (rightCode.equals(rightCodeTo))
			return true;
		return false;
	}

	public static void getP(List<SysRight> list) {
		Map menuMap = new HashMap();
		List<SysRight> sonList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			SysRight o = list.get(i);
			String code=o.getRightCode();
			boolean ret = hasChild(list,code);
			System.out.println(ret);
			if (ret) {
				sonList.add(o);
				menuMap.put(o.getRightParentCode(), o);
			}
			// list.remove(i);
			// p(list);
			/*
			 * for (int j = list.size()-1; j >= 0; j--) { SysRight
			 * o1=list.get(j); String code1=o1.getRightCode(); //
			 * System.out.println(code.equals(code1)); }
			 */
		}
	}

	private static boolean hasChild(List<SysRight> list, String parentCode) {
		Iterator iterator = list.iterator();
		boolean bool = false;
		while (iterator.hasNext()) {
			SysRight t = (SysRight) iterator.next();
			if (parentCode.equals(t.getRightParentCode())) {
				bool = true;
				break;
			}
		}
		return bool;
	}
	public static List<SysRight> getChild(List<SysRight> list, String parentCode) {
		List<SysRight> newList=new ArrayList<SysRight>();
		Iterator<SysRight> iterator = list.iterator();
		boolean bool = false;
		while (iterator.hasNext()) {
			SysRight t = iterator.next();
			if (parentCode.equals(t.getRightParentCode())) {
				bool = true;
				newList.add(t);
			}
		}
		return newList;
	}

	static double Factorial(int n) {
		if (n == 1)
			return 1;
		else
			return n * Factorial(n - 1);// 调用方法本身. 进行递归.
	}

	static void s() {
		String s;
		try {
			System.out.println("Please intput a Number");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			s = br.readLine();
			int i = Integer.parseInt(s);
			System.out.println(Factorial(i));// 这里调用阶乘递归方法
		} catch (IOException e) {
		}
	}

	public static void p() {
		for (int i = 0; i < 5; i++) {
			for (int j = 5; j > i; j--) {
				System.out.print("*");
			}
			System.out.println("");
		}
	}

	public static List<SysRight> getRights() {
		List<SysRight> unsortedlist = new ArrayList<SysRight>();
		SysRight r1 = new SysRight();
		r1.setRightCode("L0101");
		r1.setRightParentCode("L01");
		r1.setRightText("rightText");
		SysRight r2 = new SysRight();
		r2.setRightCode("L0102");
		r2.setRightParentCode("L01");
		r2.setRightText("rightText111");
		SysRight r = new SysRight();
		r.setRightCode("L01");
		r.setRightParentCode("ROOT_MENU");
		r.setRightText("rightTextRRRRRR");
		unsortedlist.add(r);
		unsortedlist.add(r1);
		unsortedlist.add(r2);
		return unsortedlist;
	}

	/*
	 * public static List checkChild(String parentcode, List<SysRight> list) {
	 * List<SysRight> sonList = new ArrayList(); for (int i = 0; i <
	 * list.size(); i++) { if (list.get(i).getRightCode().equals(parentcode)) {
	 * sonList.add(list.get(i)); } } for (int j = 0; j < sonList.size(); j++) {
	 * System.out.println("-----------ready---------------"); SysRight r = new
	 * SysRight(); r = sonList.get(j); System.out.println(r.getRightCode() + ":"
	 * + r.getRightText()); } return sonList; }
	 */
}