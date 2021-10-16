package com.todo.service;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	static int count = 0;
	
	public static void deadline(TodoList l) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date time = new Date();
		String current = format.format(time);
		
		StringTokenizer st = new StringTokenizer(current, "/");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		
		//System.out.printf("����ð� : %d�� %d�� %d��\n" , year, month, day);
		
		System.out.println("���������� 5�� �̳��� ����� ����Ͽ����ϴ�.\n");
		count = 0;
		
		for(TodoItem item : l.getList(0)) {
			StringTokenizer st1 = new StringTokenizer(item.getDue_date(), "/");
			int due_year = Integer.parseInt(st1.nextToken());
			int due_month = Integer.parseInt(st1.nextToken());
			int due_day = Integer.parseInt(st1.nextToken());
			
			int diff = due_day - day;
			
			//System.out.printf("��� �ð� : %d�� %d�� %d��, ���� : %d\n" , due_year, due_month, due_day, diff);
			
			if(diff >= 0) {
				if(due_year == year && due_month == month && diff <= 5) {
					System.out.println(item.toString() + " ** ���� ���� : " + diff + "�� **");
					count++;
				}
			}
			else {
				switch(month) {
				case 1 : 
				case 3 :
				case 5 :
				case 7 :
				case 8 :
				case 10 : 
					if(day >= 28 && due_month == (month + 1) && year == due_year) {
						diff = 31 - day + 1;
						diff = diff + due_day;
						if(diff <= 5) {
							System.out.println(item.toString() + " ** ���� ���� : " + diff + "�� **");
							count++;
						}
					}
					break;
				case 12 : 
					if(day >= 28 && due_month == 1 && year == (due_year-1)) {
						diff = 31 - day + 1;
						diff = diff + due_day;
						if(diff <= 5) {
							System.out.println(item.toString() + " ** ���� ���� : " + diff + "�� **");
							count++;
						}
					}
					break;
				case 2 : 
					if(day >= 25 && due_month == (month + 1) && year == due_year) {
						diff = 28 - day + 1;
						diff = diff + due_day;
						if(diff <= 5) {
							System.out.println(item.toString() + " ** ���� ���� : " + diff + "�� **");
							count++;
						}
					}
					break;
				case 4 :
				case 6 :
				case 9 :
				case 11 :
					if(day >= 27 && due_month == (month + 1) && year == due_year) {
						diff = 30 - day + 1;
						diff = diff + due_day;
						if(diff <= 5) {
							System.out.println(item.toString() + " ** ���� ���� : " + diff + "�� **");
							count++;
						}
					}
					break;
				}
			}
		}
		System.out.printf("%d���� �����ӹ�(5�� �̳�) ����� �߰ߵǾ����ϴ�!!\n", count);
	}
	
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
		
		System.out.print("�켱���� ( �� <- 1 2 3 4 5 -> �� ) > ");
		int prior = sc.nextInt();
		
		System.out.print("���� �ҿ� �ð� (���� : ��) > ");
		int expected = sc.nextInt();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, prior, expected);
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
	
	public static void clearComp(TodoList l, int comp) {
		for (TodoItem myitem : l.getList(comp)) {
			if(l.deleteItem(myitem.getId()) > 0)
				continue;
		}
		System.out.println("�Ϸ�� �׸��� ��Ͽ��� �����Ǿ����ϴ�.");
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
		
		System.out.print("�� �켱���� (�� <- 1 2 3 4 5 -> ��) > ");
		int prior = sc.nextInt();
		
		System.out.print("�� ���� �ҿ� �ð� (���� : ��) > ");
		int expected = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due, prior, expected);
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
	public static void lsPrior(TodoList l, int prior) {
		int cnt = 0;
		for(TodoItem item : l.getListPrior(prior)) {
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
