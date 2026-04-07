package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManagement tm = new TaskManagement();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm");
            System.out.println("2. Danh sách");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thống kê");
            System.out.println("0. Thoát");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Tên: ");
                        String name = sc.nextLine();

                        System.out.print("Trạng thái: ");
                        String status = sc.nextLine();

                        if (name.isBlank() || status.isBlank())
                            throw new Exception("Không được để trống");

                        tm.addTask(name, status);
                        break;

                    case 2:
                        tm.listTasks();
                        break;

                    case 3:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Trạng thái: ");
                        status = sc.nextLine();

                        tm.updateTaskStatus(id, status);
                        break;

                    case 4:
                        System.out.print("ID: ");
                        id = sc.nextInt();
                        tm.deleteTask(id);
                        break;

                    case 5:
                        System.out.print("Tên cần tìm: ");
                        name = sc.nextLine();
                        tm.searchTaskByName(name);
                        break;

                    case 6:
                        tm.taskStatistics();
                        break;

                    case 0:
                        return;
                }

            } catch (InputMismatchException e) {
                System.out.println(" Sai kiểu dữ liệu!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println( e.getMessage());
            }
        }
    }
}