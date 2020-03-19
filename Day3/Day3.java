import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files
import java.lang.Math;

public class Day3 {

    public static void main(String[] args) {

        String[] wires = new String[2];
        try {
            File myObj = new File("Day3.txt");
            Scanner myReader = new Scanner(myObj);
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                wires[index] = data;
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] firstWire = wires[0].split(",");
        String[] secondWire = wires[1].split(",");

        int size = 20000;

        char[][] panel = new char[size][size];
        fillArray(panel, size);

        panel[size/2][size/2] = 'o';

        placeWire(panel, firstWire, size/2, size/2, false);
        placeWire(panel, secondWire, size/2, size/2, true);

        int closestDistance = getClosestDistance(panel, size);

        int[][] firstDistances = trackWire(panel, firstWire, size, size/2, size/2);
        int[][] secondDistances = trackWire(panel, secondWire, size, size/2, size/2);

        int closestDistanceOnWire = getClosestDistanceOnWire(firstDistances, secondDistances, size);

//        printPanel(panel, size);
        System.out.println(closestDistance);
        System.out.println(closestDistanceOnWire);
    }

    private static void fillArray(char[][] panel, int size) {
        for (int i = 0; i < size; i++) {
            Arrays.fill(panel[i], '.');
        }
    }

    private static void fillArrayWithInts(int[][] panel, int size) {
        for (int i = 0; i < size; i++) {
            Arrays.fill(panel[i], -1);
        }
    }
    
    private static void printPanel(char[][] panel, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (panel[j][i] == 'X') {
//                    System.out.println(j + " " + i);
                }
                System.out.print(panel[j][i]);
            }
            System.out.print("\n");
        }
    }

    private static int getClosestDistance(char[][] panel, int size) {
        int closestDistance = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (panel[j][i] == 'X') {
                    int xDistance = Math.abs((size/2) - i);
                    int yDistance = Math.abs((size/2) - j);
                    int totalDistance = xDistance + yDistance;
                    if (totalDistance < closestDistance) {
                        closestDistance = totalDistance;
//                        System.out.println(closestDistance);
                    }
                }
            }
        }
        return closestDistance;
    }

    private static int getClosestDistanceOnWire(int[][] firstDistances, int[][] secondDistances, int size) {
        int closestDistanceOnWire = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (firstDistances[j][i] > -1 && secondDistances[j][i] > -1) {
                    int distanceOnBothWires = firstDistances[j][i] + secondDistances[j][i];
                    if (distanceOnBothWires < closestDistanceOnWire) {
                        closestDistanceOnWire = distanceOnBothWires;
//                        System.out.println(closestDistanceOnWire);
                    }
                }
            }
        }
        return closestDistanceOnWire;
    }

    private static void placeWire(char[][] panel, String[] wire, int x, int y, boolean isSecond) {
        for (int i = 0; i < wire.length; i++) {
            String direction = wire[i].substring(0, 1);
            int distance = Integer.parseInt(wire[i].substring(1));

            for (int j = 0; j < distance; j++) {
                if (direction.equals("U")) {
                    x--;
                    if (panel[y][x] == '|' && isSecond) {
                        panel[y][x] = 'X';
                    } else if (!isSecond){
                        panel[y][x] = '|';
                    } else {
                        panel[y][x] = '-';
                    }
                }
                if (direction.equals("D")) {
                    x++;
                    if (panel[y][x] == '|' && isSecond) {
                        panel[y][x] = 'X';
                    } else if (!isSecond){
                        panel[y][x] = '|';
                    } else {
                        panel[y][x] = '-';
                    }
                }
                if (direction.equals("L")) {
                    y--;
                    if (panel[y][x] == '|' && isSecond) {
                        panel[y][x] = 'X';
                    } else if (!isSecond){
                        panel[y][x] = '|';
                    } else {
                        panel[y][x] = '-';
                    }
                }
                if (direction.equals("R")) {
                    y++;
                    if (panel[y][x] == '|' && isSecond) {
                        panel[y][x] = 'X';
                    } else if (!isSecond){
                        panel[y][x] = '|';
                    } else {
                        panel[y][x] = '-';
                    }
                }
            }
        }
        return;
    }

    private static int[][] trackWire(char[][] panel, String[] wire, int size, int x, int y) {
        int[][] distances = new int[size][size];
        fillArrayWithInts(distances, size);
        int distanceOnWire = 0;
        for (int i = 0; i < wire.length; i++) {
            String direction = wire[i].substring(0, 1);
            int distance = Integer.parseInt(wire[i].substring(1));

            for (int j = 0; j < distance; j++) {
                if (direction.equals("U")) {
                    x--;
                    distanceOnWire++;
                    if (panel[y][x] == 'X') {
                        distances[y][x] = distanceOnWire;
                    }
                }
                if (direction.equals("D")) {
                    x++;
                    distanceOnWire++;
                    if (panel[y][x] == 'X') {
                        distances[y][x] = distanceOnWire;
                    }
                }
                if (direction.equals("L")) {
                    y--;
                    distanceOnWire++;
                    if (panel[y][x] == 'X') {
                        distances[y][x] = distanceOnWire;
                    }
                }
                if (direction.equals("R")) {
                    y++;
                    distanceOnWire++;
                    if (panel[y][x] == 'X') {
                        distances[y][x] = distanceOnWire;
                    }
                }
            }
        }
        return distances;
    }
}
