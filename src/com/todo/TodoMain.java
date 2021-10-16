package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
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
				TodoUtil.listAll(l, 0);
				break;

			case "ls_name":
				System.out.println("���� ������ �����Ͽ����ϴ�.\n");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("���� �������� �����Ͽ����ϴ�.\n");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("��¥ ������ �����Ͽ����ϴ�.\n");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("��¥ �������� �����Ͽ����ϴ�.\n");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate" :
				TodoUtil.lsCate(l);
				break;
				
			case "ls_prior" :
				int prior = sc.nextInt();
				System.out.println( "�켱���� " + prior + "�� �ش��ϴ� ����� �����Ͽ����ϴ�.\n");
				TodoUtil.lsPrior(l, prior);
				break;
				
			case "ls_prior_all" :
				System.out.println("�켱���� ���� ������ �����Ͽ����ϴ�.\n");
				for(int i = 1; i <= 5; i++ )
					TodoUtil.lsPrior(l, i);
				break;
				
			case "ls_deadline" :
				TodoUtil.deadline(l);
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
				
			case "clear_comp" :
				TodoUtil.clearComp(l, 1);
				break;
				
			case "comp" :
				TodoUtil.comp(l, sc.nextInt());
				break;
				
			case "ls_comp" :
				TodoUtil.listAll(l, 1);
				break;

			case "exit":
				System.out.println("����Ǿ����ϴ�.");
				quit = true;
				break;
				

			default:
				System.out.println("��Ȯ�� ��ɾ �Է��ϼ���. (���� - help)");
				break;
			}
		} while (!quit);
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
