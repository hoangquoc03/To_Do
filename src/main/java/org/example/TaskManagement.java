package org.example;
import java.sql.*;
public class TaskManagement {
    public void addTask(String name, String status) {
        String sql = "{ call add_task(?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, name);
            cs.setString(2, status);
            cs.execute();

            System.out.println("✔ Thêm thành công");

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }


    public void listTasks() {
        String sql = "{ call list_tasks() }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("task_name") + " | " +
                                rs.getString("status"));
            }

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }


    public void updateTaskStatus(int id, String status) {
        String sql = "{ call update_task_status(?, ?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.setString(2, status);
            cs.execute();

            System.out.println("✔ Cập nhật thành công");

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }


    public void deleteTask(int id) {
        String sql = "{ call delete_task(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.execute();

            System.out.println("Xóa thành công");

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }


    public void searchTaskByName(String name) {
        String sql = "{ call search_task_by_name(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("task_name") + " | " +
                                rs.getString("status"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void taskStatistics() {
        String sql = "{ call task_statistics() }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            if (rs.next()) {
                System.out.println("✔ Đã hoàn thành: " + rs.getInt("completed"));
                System.out.println("✔ Chưa hoàn thành: " + rs.getInt("not_completed"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
