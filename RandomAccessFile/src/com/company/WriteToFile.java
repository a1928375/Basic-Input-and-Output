package com.company;

import java.io.*;
import java.util.Scanner;

public class WriteToFile {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name, surname;
        int age;

        System.out.println("Please Enter the Name: ");
        name = scanner.next().toLowerCase();

        System.out.println("Please Enter the Surname: ");
        surname = scanner.next().toLowerCase();

        System.out.println("Please Enter Age: ");
        age = scanner.nextInt();

        try (RandomAccessFile file = new RandomAccessFile(new File("write.txt"), "rw")) {

            long fileSize = file.length();

            file.seek(fileSize);

            file.writeUTF(name);

            for (int i = 0; i < 20-name.length(); i++) {

                file.writeByte(20);
            }

            file.writeUTF(surname);

            for (int i = 0; i < 20-surname.length(); i++) {

                file.writeByte(20);
            }

            file.writeInt(age);

        } catch (IOException io) {

            System.out.println(io.getStackTrace());
        }

    }
}
