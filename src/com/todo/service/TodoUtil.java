package com.todo.service;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	static int count = 0;
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� �߰�]\n"
				+ "���� > ");
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.print("ī�װ� > ");
		category = sc.next();
//		if (list.isDuplicate(title)) {
//			System.out.printf("�̹� �����ϴ� �����Դϴ�.");
//			return;
//		}
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.");
		Setcount(Getcount()+1);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		
		int num = sc.nextInt();
		if (l.deleteItem(num) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int num = sc.nextInt();
		

		System.out.print("�� ���� > ");
		String new_title = sc.next().trim();
		
		System.out.print("�� ī�װ� > ");
		String new_category = sc.next();
		sc.nextLine();
		
		System.out.print("�� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� �������� > ");
		String new_due = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due);
		t.setId(num);
		if(l.updateItem(t) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}
	
	public static void comp(TodoList l, int id) {
		if(l.completeItem(id) > 0)
			System.out.println("�Ϸ� üũ�Ͽ����ϴ�.");
	}
	
	public static void listAll(TodoList l, int comp) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem myitem : l.getList(comp)) {
			System.out.println(myitem.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[ ��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item : l.getList(0)) {
				w.write(item.toSaveString());
			}
			
			System.out.println("��� �����Ͱ� ����Ǿ����ϴ�.");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
		
			String str;
			int cnt = 0;
			while((str = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due = st.nextToken();
				String time = st.nextToken();
				TodoItem t = new TodoItem(title, desc, time, category, due);
				l.addItem(t);
				cnt++;
			}
			System.out.printf("%d���� �׸��� �о����ϴ�.\n", cnt);
			Setcount(cnt);
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void findList(TodoList l, String key) {
		int cnt = 0;

		for (TodoItem item : l.getList(key)) {
			System.out.println(item.toString());
			cnt++;
		}
		System.out.println("�� " + cnt + "���� �׸��� ã�ҽ��ϴ�.");
	}
	public static void findCate(TodoList l, String key) {
		int cnt = 0;
		for (TodoItem item : l.getListCategory(key)) {	
			System.out.println(item.toString());
			cnt++;
		}
		System.out.println("�� " + cnt + "���� �׸��� ã�ҽ��ϴ�.");
	}
	public static void lsCate(TodoList l) {
		int cnt = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			cnt++;
		}
		System.out.println("\n�� " + cnt + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
	public static void Setcount(int cnt) {
		count = cnt;
	}
	public  static int Getcount() {
		return count;
	}
}
