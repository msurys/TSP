package org.mateusz;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        IO data = new IO();
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();

        System.out.println("Autor: Mateusz Surys\nPEA zad 2\nAlgorytm Symulowanego Wyzarzania ");
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
                    break;

                case 2:
                    Algorithm SA = new Algorithm(data.t_start, data.t_end, data.reduc, data.time, reader.size, reader.graph);
                    if(data.t_end != 0){
                        SA.SA();
                    }
                    else{
                        data.setTime(10);
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();
                        data.setTime(50);
                        SA = new Algorithm(data.t_start, data.t_end, data.reduc, data.time, reader.size, reader.graph);
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();
                        data.setTime(100);
                        SA = new Algorithm(data.t_start, data.t_end, data.reduc, data.time, reader.size, reader.graph);
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();
                        SA.SA_zero();

                    }
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("(Uzyj przecinka jako odddzielenie czesci ulamkowej) Podaj tempereture poczatkowa: ");
                    data.setT_start(scanner.nextDouble());
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.println("(Uzyj przecinka jako odddzielenie czesci ulamkowej) Podaj tempereture koncowa: ");
                    double temp = scanner.nextDouble();
                    if (temp > data.t_start){
                        System.out.println("Temperatura koncowa nie moze byc wyzsza od poczatkowej!");
                    }
                    else{
                        data.setT_end(temp);
                    }
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.println("(Uzyj przecinka jako odddzielenie czesci ulamkowej) Podaj wspolczynnik redukcji temperatury: ");
                    data.setReduc(scanner.nextDouble());
                    break;

                case 6:
                    scanner.nextLine();
                    System.out.println("Podaj kruterium stopu: ");
                    data.setTime(scanner.nextInt());
                    break;

                case 7:
                    data = new IO();
                    break;

                default: break;
            }
        }
	}
}
