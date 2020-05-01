package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ReadFile {

    private static final int RECORD = 48;

    public static void main(String[] args) {

        read();

        //search();

        //updateAge();

        delete();

    }

    public static void read () {

        String name, surname;
        int age;

        try (RandomAccessFile file = new RandomAccessFile(new File("write.txt"), "rw")) {

            long fileLength = file.length();

            file.seek(0);

            long dataNumber = fileLength/RECORD;

            for (int i = 0; i < dataNumber; i++) {

                name = file.readUTF();

                for (int j = 0; j < 20-name.length(); j++) {

                    file.readByte();
                }

                surname = file.readUTF();

                for (int j = 0; j < 20-surname.length(); j++) {

                    file.readByte();
                }
                age = file.readInt();

                System.out.println("The name is " + name + ", the surname is " + surname + " and the age is " + age);

            }

        } catch (IOException io) {

            System.out.println(io.getStackTrace());
        }
    }

    public static void search () {

        String name, surname;
        int age;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name you want to search for: ");

        String searchName = scanner.next().toLowerCase();

        boolean found = false;

        try (RandomAccessFile file = new RandomAccessFile(new File("write.txt"), "rw")) {

            long fileLength = file.length();

            file.seek(0);

            long dataNumber = fileLength/RECORD;

            for (int i = 0; i < dataNumber; i++) {

                name = file.readUTF();

                for (int j = 0; j < 20-name.length(); j++) {

                    file.readByte();
                }

                surname = file.readUTF();

                for (int j = 0; j < 20-surname.length(); j++) {

                    file.readByte();
                }

                age = file.readInt();

                if (name.equals(searchName)) {

                    System.out.println("The name is " + name + ", the surname is " + surname + " and the age is " + age);

                    found = true;
                }
            }

            if (found == false) {

                System.out.println("The name is not found.");
            }

        } catch (IOException io) {

            System.out.println(io.getStackTrace());
        }
    }

    public static void updateAge () {

        String name, surname;
        int age;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name of the data you want to change: ");

        String searchName = scanner.next().toLowerCase();

        boolean found = false;

        try (RandomAccessFile file = new RandomAccessFile(new File("write.txt"), "rw")) {

            long fileLength = file.length();

            file.seek(0);

            long dataNumber = fileLength/RECORD;

            for (int i = 0; i < dataNumber; i++) {

                name = file.readUTF();

                for (int j = 0; j < 20-name.length(); j++) {

                    file.readByte();
                }

                surname = file.readUTF();

                for (int j = 0; j < 20-surname.length(); j++) {

                    file.readByte();
                }

                age = file.readInt();

                if (name.equals(searchName)) {

                    System.out.println("The name is " + name + ", the surname is " + surname + " and the old age is " + age);

                    found = true;

                    System.out.println("Please enter the new age: ");
                    int newAge = scanner.nextInt();

                    file.seek(RECORD*i+44);

                    file.writeInt(newAge);

                    System.out.println("The name is " + name + ", the surname is " + surname + " and the new age is " + newAge);
                    System.out.println();
                    read();
                }
            }

            if (found == false) {

                System.out.println("The name is not found.");
            }

        } catch (IOException io) {

            System.out.println("IOException: " + io.getStackTrace());
        }
    }

    public static void delete () {

        String name, surname;
        int age;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name of the data you want to delete: ");

        String deleteName = scanner.next().toLowerCase();

        boolean found = false;

        int i, temp=-1;

        try (RandomAccessFile file = new RandomAccessFile(new File("write.txt"), "rw")) {

            long fileLength = file.length();

            file.seek(0);

            long dataNumber = fileLength/RECORD;

            for (i = 0; i < dataNumber; i++) {

                name = file.readUTF();

                for (int j = 0; j < 20-name.length(); j++) {

                    file.readByte();
                }

                surname = file.readUTF();

                for (int j = 0; j < 20-surname.length(); j++) {

                    file.readByte();
                }

                age = file.readInt();

                if (name.equals(deleteName)) {

                    found = true;
                    temp = i;

                }
            }

            System.out.println("i: " + i + " found: " + found + " temp: " + temp + " dataNumber: " + dataNumber);

            file.seek((temp+1)*RECORD);

            if (found == true) {

                for (int j = temp; j< dataNumber-1; j++) {

                    name = file.readUTF();

                    for (int k = 0; k < 20-name.length(); k++) {

                        file.readByte();
                    }

                    surname = file.readUTF();

                    for (int k = 0; k < 20-surname.length(); k++) {

                        file.readByte();
                    }

                    age = file.readInt();


                    file.seek(j*RECORD);

                    file.writeUTF(name);

                    for (int k = 0; k < 20-name.length(); k++) {

                        file.writeByte(20);
                    }

                    file.writeUTF(surname);

                    for (int k = 0; k < 20-surname.length(); k++) {

                        file.writeByte(20);
                    }

                    file.writeInt(age);

                    file.seek((j+2)*RECORD);

                }

                file.setLength(fileLength-48);
            }

            if (found == false) {

                System.out.println("The name is not found.");
            }

            read();

        } catch (IOException io) {

            System.out.println(io.getStackTrace());
        }
    }
}
