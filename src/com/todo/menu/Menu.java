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
        System.out.println("1. add - �׸� �߰�");
        System.out.println("2. del - �׸� ����");
        System.out.println("3. edit - �׸� ����");
        System.out.println("4. ls - ��ü ���");
        System.out.println("5. ls_name - ����� ����");
        System.out.println("6. ls_name_desc - ���񿪼� ����");
        System.out.println("7. ls_date - ��¥�� ����");
        System.out.println("8. ls_date_desc - ��¥���� ����");
        System.out.println("9. find <Ű����> - ����� ���뿡�� Ű���带 �����ϴ� �׸� ���");
        System.out.println("10. find_cate <Ű����> - ī�װ����� Ű���带 �����ϴ� �׸� ���");
        System.out.println("11. ls_cate - �׸� ���� �ִ� ī�װ� ���");
        System.out.println("12. comp <��ȣ> - ��ȣ�� �ش��ϴ� ����Ʈ �Ϸ�");
        System.out.println("13. ls_comp - �Ϸ�� ����Ʈ�� ���");
        System.out.println("14. ls_prior <��ȣ> - �ش� �켱���� ���");
        System.out.println("15. ls_prior_all - �켱���� ���� ������ ���");
        System.out.println("16. clear_comp - �Ϸ�� �׸� ��Ͽ��� ����");
        System.out.println("17. ls_deadline - ���������� 5�� ���� ���� ��� ���");
        System.out.println("18. exit - ����");
    }
}