package com.todo.menu;
public class Menu {

    public static void prompt()
    {
        System.out.print("\nCommand > ");
    }
    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<ToDoList> ���� ��ɾ� ����>");
        System.out.println("add - �׸� �߰�");
        System.out.println("del - �׸� ����");
        System.out.println("edit - �׸� ����");
        System.out.println("ls - ��ü ���");
        System.out.println("ls_name - ����� ����");
        System.out.println("ls_name_desc - ���񿪼� ����");
        System.out.println("ls_date - ��¥�� ����");
        System.out.println("ls_date_desc - ��¥���� ����");
        System.out.println("find <Ű����> - ����� ���뿡�� Ű���带 �����ϴ� �׸� ���");
        System.out.println("find_cate <Ű����> - ī�װ����� Ű���带 �����ϴ� �׸� ���");
        System.out.println("ls_cate - �׸� ���� �ִ� ī�װ� ���");
        System.out.println("comp <��ȣ> - ��ȣ�� �ش��ϴ� ����Ʈ �Ϸ�");
        System.out.println("ls_comp - �Ϸ�� ����Ʈ�� ���");
        System.out.println("exit - ����");
    }
}
