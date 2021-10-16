package com.todo.menu;
public class Menu {

    public static void prompt()
    {
        System.out.print("\nCommand > ");
    }
    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<ToDoList> 관리 명령어 사용법>");
        System.out.println("1. add - 항목 추가");
        System.out.println("2. del - 항목 삭제");
        System.out.println("3. edit - 항목 수정");
        System.out.println("4. ls - 전체 목록");
        System.out.println("5. ls_name - 제목순 정렬");
        System.out.println("6. ls_name_desc - 제목역순 정렬");
        System.out.println("7. ls_date - 날짜순 정렬");
        System.out.println("8. ls_date_desc - 날짜역순 정렬");
        System.out.println("9. find <키워드> - 제목과 내용에서 키워드를 포함하는 항목 출력");
        System.out.println("10. find_cate <키워드> - 카테고리에서 키워드를 포함하는 항목 출력");
        System.out.println("11. ls_cate - 항목 내에 있는 카테고리 출력");
        System.out.println("12. comp <번호> - 번호에 해당하는 리스트 완료");
        System.out.println("13. ls_comp - 완료된 리스트만 출력");
        System.out.println("14. ls_prior <번호> - 해당 우선순위 출력");
        System.out.println("15. ls_prior_all - 우선순위 높은 순으로 출려");
        System.out.println("16. clear_comp - 완료된 항목 목록에서 제거");
        System.out.println("17. ls_deadline - 마감기한이 5일 내로 남은 목록 출력");
        System.out.println("18. exit - 종료");
    }
}