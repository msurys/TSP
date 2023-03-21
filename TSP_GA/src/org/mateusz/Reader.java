package org.mateusz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public Integer size;
    public ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public void ReadFromFile() throws IOException { //zczytywanie z wejscia testowego
        File file = new File("C:\\Users\\mateu\\Documents\\Java\\pea1\\test11.txt");
        Scanner scanner = new Scanner(file);
        size = scanner.nextInt();
        for (int i = 0; i<size; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                graph.get(i).add(j, scanner.nextInt());
            }
        }
    }
    public void ReadFromFile(String path) throws IOException { //zczytywanie pliku testowego podajac sciezke
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                graph.get(i).add(j, scanner.nextInt());
            }
        }
    }
}

