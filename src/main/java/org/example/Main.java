package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(str);
        switch (str){
            case "123":
                System.out.println("guoguan!");
                break;
            default:
                System.out.println("shibai!");
        }
        //System.out.println("Hello world!");
    }
}