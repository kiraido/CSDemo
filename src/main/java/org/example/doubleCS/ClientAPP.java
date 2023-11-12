package org.example.doubleCS;

import org.example.doubleCS.Dao.Impl.contactDaoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class ClientAPP {
    private static final contactDaoImpl contactDao = new contactDaoImpl();

    public static void main(String[] args) {
        System.out.println("欢迎来到个人通讯录系统！");
        String request;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------");
            System.out.println("请输入你需要处理的业务：QUERY(1), ADD(2), DELETE(3), UPDATE(4):");
            request = sc.nextLine();
            //根据用户请求执行不同的操作
            switch (request) {
                case "ADD":
                case "2":
                    addContact(sc);
                    break;
                case "DELETE":
                case "3":
                    deleteContact(sc);
                    break;
                case "QUERY":
                case "1":
                    queryContacts(sc);
                    break;
                case "UPDATE":
                case "4":
                    updateContact(sc);
                    break;
                default:
                    System.out.println("输入错误！请重新输入！");
            }
            System.out.println("正在退出当前业务...");
        }
    }

    private static void addContact(Scanner sc) {
        System.out.println("请输入新联系人的信息：");
        System.out.print("姓名: ");
        String name = sc.nextLine();
        System.out.print("地址: ");
        String address = sc.nextLine();
        System.out.print("电话号码: ");
        double tel = Double.parseDouble(sc.nextLine());

        if (contactDao.addContact(name, address, tel)) {
            System.out.println("联系人已成功添加到数据库。");
        } else {
            System.out.println("添加联系人失败。");
        }
    }

    private static void deleteContact(Scanner sc) {
        System.out.print("请输入要删除的联系人的姓名: ");
        String name = sc.nextLine();

        if (contactDao.deleteContactByName(name)) {
            System.out.println("联系人已成功删除。");
        } else {
            System.out.println("删除联系人失败。");
        }
    }

    private static void queryContacts(Scanner sc) {
        System.out.print("请输入查询条件（例如姓名关键字）: ");
        String queryCondition = sc.nextLine();
        ResultSet rs = null;

        try {
            // 调用DAO方法执行查询
            rs = contactDao.queryContact(queryCondition);
            // 检查是否有查询结果
            if (!rs.isBeforeFirst()) {
                System.out.println("没有找到匹配的联系人。");
            } else {
                // 遍历查询结果
                while (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    BigDecimal tel = rs.getBigDecimal("tel"); // 使用BigDecimal来避免科学记数法
                    System.out.println("姓名: " + name + ", 地址: " + address + ", 电话号码: " + tel.toPlainString());
                }
            }
        } catch (SQLException e) {
            System.out.println("查询过程中出现错误。");
            e.printStackTrace();
        } finally {
            // 关闭ResultSet
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private static void updateContact(Scanner sc) {
        System.out.print("请输入要更新的联系人的姓名: ");
        String name = sc.nextLine();

        // 先展示当前联系人信息
        ResultSet rs = null;
        try {
            rs = contactDao.queryContact(name);
            if (rs != null && rs.next()) {
                String currentAddress = rs.getString("address");
                BigDecimal currentTel = rs.getBigDecimal("tel");
                System.out.println("当前信息：姓名: " + name + ", 地址: " + currentAddress + ", 电话号码: " + currentTel.toPlainString());

                // 提示用户输入新的联系人信息
                System.out.print("新地址: ");
                String newAddress = sc.nextLine();
                System.out.print("新电话号码: ");
                double newTel = Double.parseDouble(sc.nextLine());

                // 调用DAO层更新联系人信息
                if (contactDao.updateContact(name, newAddress, newTel)) {
                    System.out.println("联系人信息已成功更新。");
                } else {
                    System.out.println("更新联系人信息失败。");
                }
            } else {
                System.out.println("没有找到姓名为 " + name + " 的联系人。");
            }
        } catch (SQLException e) {
            System.out.println("查询联系人信息出错。");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
