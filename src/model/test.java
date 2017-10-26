package model;

import java.util.Iterator;
import java.util.TreeMap;

public class test{
	static int[] p={1,2};
	public static void main(String[] args) {
		int[] p1=new int[2];
		p1[0]=p[0];
		p1[1]=p[1];
		System.out.println(p1[0]);
		p1[0]=3;
		System.out.println(p1[0]);
		System.out.println(p[0]);
		TreeMap<Integer, String> treeMap=new TreeMap<Integer,String>(); 
		treeMap.put(5, "girl5");
		treeMap.put(7, "girl7");
		treeMap.put(3, "girl3");
		treeMap.put(1, "girl1");
		treeMap.put(6, "girl6");
		System.out.println(treeMap.get(1));
	}
}