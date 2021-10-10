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
		
		System.out.print("[항목 추가]\n"
				+ "제목 > ");
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		System.out.print("카테고리 > ");
		category = sc.next();
//		if (list.isDuplicate(title)) {
//			System.out.printf("이미 존재하는 제목입니다.");
//			return;
//		}
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다.");
		Setcount(Getcount()+1);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		
		int num = sc.nextInt();
		if (l.deleteItem(num) > 0)
			System.out.println("삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		int num = sc.nextInt();
		

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.next();
		sc.nextLine();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due);
		t.setId(num);
		if(l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}
	
	public static void comp(TodoList l, int id) {
		if(l.completeItem(id) > 0)
			System.out.println("완료 체크하였습니다.");
	}
	
	public static void listAll(TodoList l, int comp) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem myitem : l.getList(comp)) {
			System.out.println(myitem.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[ 전체 목록, 총 " + l.getCount() + "개]");
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
			
			System.out.println("모든 데이터가 저장되었습니다.");
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
			System.out.printf("%d개의 항목을 읽었습니다.\n", cnt);
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
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}
	public static void findCate(TodoList l, String key) {
		int cnt = 0;
		for (TodoItem item : l.getListCategory(key)) {	
			System.out.println(item.toString());
			cnt++;
		}
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}
	public static void lsCate(TodoList l) {
		int cnt = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			cnt++;
		}
		System.out.println("\n총 " + cnt + "개의 카테고리가 등록되어 있습니다.");
	}
	public static void Setcount(int cnt) {
		count = cnt;
	}
	public  static int Getcount() {
		return count;
	}
}
