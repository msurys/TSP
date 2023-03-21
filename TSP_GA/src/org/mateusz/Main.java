package org.mateusz;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IO data = new IO();
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        Printer printer = new Printer();

        System.out.println("Autor: Mateusz Surys\nPEA zad 3\nAlgorytm Genetyczny ");
        int choice = 1;
        while(choice!=0) {
            data.print();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Podaj sciezke pliku: ");
                    String path = scanner.nextLine();
                    try {
                        reader.ReadFromFile(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    printer.print(reader.size, reader.graph);
                    break;

                case 2:
//                    System.out.println("wspl_krzyzowania "+data.wspl_krzyzowania);
//                    System.out.println("wspl_mutacji "+data.wspl_mutacji);
//                    System.out.println("kryt_stopu "+data.time);
//                    System.out.println("population_start "+data.pop_start);
//                    System.out.println("size "+reader.size);
//                    System.out.println("graph "+reader.graph);

                    Algorithm alg = new Algorithm(data.wspl_krzyzowania,data.wspl_mutacji,data.time, reader.size, reader.graph, data.pop_start);
                    alg.alg_gen();
                    System.out.println("path: "+ alg.bestRoute);
                    System.out.println("cost: "+ alg.bestCost);

                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("(Uzyj przecinka jako odddzielenie czesci ulamkowej) Podaj wspolczynnik krzyzowania: ");
                    data.setWspl_krzyzowania(scanner.nextDouble());
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.println("(Uzyj przecinka jako odddzielenie czesci ulamkowej) Wspolczynnik mutacji: ");
                    data.setWspl_mutacji(scanner.nextDouble());
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.println("Podaj wielkosc populacji poczatkowej: ");
                    data.setPop_start(scanner.nextInt());
                    break;

                case 6:
                    scanner.nextLine();
                    System.out.println("Podaj kruterium stopu: ");
                    data.setTime(scanner.nextInt());
                    break;

                case 7:
                    data = new IO();
                    break;

                case 8:
                    Algorithm alg2 = new Algorithm(data.wspl_krzyzowania,data.wspl_mutacji,data.time, reader.size, reader.graph, data.pop_start);
                    alg2.alg_gen2();
                    System.out.println("path: "+ alg2.bestRoute);
                    System.out.println("cost: "+ alg2.bestCost);


                default: break;
            }
        }
    }
}
