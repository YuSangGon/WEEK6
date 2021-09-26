package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		TodoUtil.loadList(l, "todolist.txt");
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목 순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜 순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_cate" :
				TodoUtil.lsCate(l);
				break;
				
			case "help" :
				Menu.displaymenu();
				break;
				
			case "find" :
				TodoUtil.findList(l, sc.next());
				break;
				
			case "find_cate" :
				TodoUtil.findCate(l, sc.next());
				break;

			case "exit":
				System.out.println("종료되었습니다.");
				quit = true;
				break;
				

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
