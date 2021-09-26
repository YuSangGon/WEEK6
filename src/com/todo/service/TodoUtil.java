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
				+ "카테고리 > ");
		category = sc.next();
		
		System.out.print("제목 > ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 존재하는 제목입니다.");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sc.nextLine();
		desc = sc.nextLine();
		
		System.out.print("마감일자 > ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		Setcount(Getcount()+1);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		
		int num = sc.nextInt();
		boolean del = false;
		
		int cnt = 1;
		for (TodoItem item : l.getList()) {
			if (cnt == num) {
				System.out.println(num + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
			}
			cnt++;
		}
		System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
		
		cnt = 1;
			for (TodoItem item : l.getList()) {
				if (num == cnt) {
					l.deleteItem(item);
					System.out.println("해당 항목이 삭제 되었습니다.");
					del = true;
					Setcount(Getcount()-1);
					break;
				}
				cnt++;
			}
			if(!del)
				System.out.println("해당 항목이 존재 하지 않습니다.");
		
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		int num = sc.nextInt();
		if(num > Getcount() || num < 1) {
			System.out.println("올바른 번호를 입력하세요. ");
			return;
		}
		
		int cnt = 1;
		for (TodoItem item : l.getList()) {
			if (cnt == num) {
				System.out.println(num + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
			}
			cnt++;
		}

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다. 중복되지 않은 제목을 입력해주세요.");
			return;
		}
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.next().trim();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due = sc.next().trim();
		
		cnt = 1;
		for (TodoItem item : l.getList()) {
			if (cnt == num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
			cnt++;
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[ 전체 목록, 총 " + Getcount() + "개]");
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(i++ + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item : l.getList()) {
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
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(item.getTitle().contains(key)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
				cnt++;
			}
			else if(item.getDesc().contains(key)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
				cnt++;
			}
			i++;
		}
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}
	public static void findCate(TodoList l, String key) {
		int cnt = 0;
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(key)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " = " + item.getTime());
				cnt++;
			}
			i++;
		}
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}
	public static void lsCate(TodoList l) {
		String cate = "";
		int cnt = 0;
		for(TodoItem item : l.getList()) {
			if(!cate.contains(item.getCategory())) {
				if(cate.equals("")) 
					cate = item.getCategory();
				else
					cate = cate + " / " + item.getCategory();
				cnt++;
			}
		}
		System.out.println(cate);
		System.out.println("총 " + cnt + "개의 카테고리가 등록되어 있습니다.");
	}
	public static void Setcount(int cnt) {
		count = cnt;
	}
	public  static int Getcount() {
		return count;
	}
}
