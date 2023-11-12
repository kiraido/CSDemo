package org.example.ThreeLayerCS.Client;


import org.example.ThreeLayerCS.Dao.Contact;
import org.example.ThreeLayerCS.Server.ContactService;

import java.util.Scanner;

public class ClientAPP {
    private static ContactService contactService = new ContactService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("欢迎来到个人通讯录系统！");
        String request;

        while (true) {
            System.out.println("----------------------");
            System.out.println("请输入你需要处理的业务：QUERY(1), ADD(2), DELETE(3), UPDATE(4):");
            request = scanner.nextLine();

            switch (request) {
                case "ADD":
                case "2":
                    addContact();
                    break;
                case "DELETE":
                case "3":
                    deleteContact();
                    break;
                case "QUERY":
                case "1":
                    queryContact();
                    break;
                case "UPDATE":
                case "4":
                    updateContact();
                    break;
                default:
                    System.out.println("输入错误！重新输入！");
            }
            System.out.println("正在退出当前业务...");
        }
    }

    private static void addContact() {
        System.out.println("请输入新联系人的信息：");
        System.out.print("姓名: ");
        String name = scanner.nextLine();
        System.out.print("地址: ");
        String address = scanner.nextLine();
        System.out.print("电话号码: ");
        double tel = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        if (contactService.addContact(name, address, tel)) {
            System.out.println("联系人已成功添加。");
        } else {
            System.out.println("联系人添加失败。");
        }
    }

    private static void deleteContact() {
        System.out.print("请输入要删除的联系人的姓名: ");
        String name = scanner.nextLine();

        if (contactService.deleteContact(name)) {
            System.out.println("联系人已成功删除。");
        } else {
            System.out.println("未找到联系人。");
        }
    }

    private static void queryContact() {
        System.out.print("请输入要查询的联系人的姓名: ");
        String name = scanner.nextLine();

        Contact contact = contactService.queryContact(name);
        if (contact != null) {
            System.out.println("查询结果：");
            // 格式化电话号码以避免科学记数法
            String formattedTel = String.format("%.0f", contact.getTel());
            System.out.println("姓名: " + contact.getName() + ", 地址: " + contact.getAddress() + ", 电话号码: " + formattedTel);
        } else {
            System.out.println("未找到联系人。");
        }
    }


    private static void updateContact() {
        System.out.print("请输入要更新的联系人的姓名: ");
        String nameToUpdate = scanner.nextLine();

        // 先查询现有联系人信息
        Contact contact = contactService.queryContact(nameToUpdate);
        if (contact != null) {
            System.out.println("待更新的联系人信息：");
            String formattedTel = String.format("%.0f", contact.getTel());
            System.out.println("姓名: " + contact.getName() + ", 地址: " + contact.getAddress() + ", 电话号码: " + formattedTel);

            // 接受用户输入的新信息
            System.out.print("新地址: ");
            String newAddress = scanner.nextLine();
            System.out.print("新电话号码: ");
            double newTel = scanner.nextDouble();
            scanner.nextLine(); // Consume newline left-over

            // 更新联系人信息
            if (contactService.updateContact(nameToUpdate, newAddress, newTel)) {
                System.out.println("联系人信息已成功更新。");
            } else {
                System.out.println("更新失败。");
            }
        } else {
            System.out.println("未找到联系人。");
        }
    }

}
