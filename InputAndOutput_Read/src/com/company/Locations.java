package com.company;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    static {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + ": " + description);
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }

        } catch(IOException e) {

            e.printStackTrace();

        } finally {

            if(scanner != null) {

                scanner.close();
            }
        }

        // Now read the exits
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {

                // method 1
//                int loc = scanner.nextInt();                     //可以直接抓nextInt()，因為有scanner.skip(scanner.delimiter()) -> "," 來抓取data
//                scanner.skip(scanner.delimiter());
//
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());
//
//                String dest = scanner.nextLine();                //因為每一line的text的最後沒有comma => 不能用scanner.skip(scanner.delimiter()) -> "," 來區分 & 抓取data
//                int destination = Integer.parseInt(dest);        //所以只能用scanner.nextLine()來抓剩下的所有data => nextLine() -> String
//                                                                 //所以要Integer.parseInt(dest)
////                int destination = scanner.nextInt();           //wrong => 因為不能使用scanner.skip(scanner.delimiter())

                //method 2
                String input = scanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if(scanner != null) {
                scanner.close();
            }
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}