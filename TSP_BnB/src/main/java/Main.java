import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Autor: Mateusz Surys\nPEA zad 1\nAlgorytm BnB metoda Lowest Cost ");
        int choice = 1;
        while(choice!=0) {
            System.out.print(
                    "\n1. Wyczytaj graf podajac sciezke pliku"+
                            "\n0. Wyjscie"+
                            "\nWybor: "
            );
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Podaj sciezke pliku: ");
                    String path = scanner.nextLine();
                    Reader reader = new Reader();
                    try {
                        reader.ReadFromFile(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BnB bnb = new BnB(reader.size, reader.graph);
                    printer.print(bnb.graphs.get(0).size, bnb.graphs.get(0).graph);
                    long startTime = System.nanoTime();
                    bnb.Algorithm();
                    long elapsedTime = System.nanoTime() - startTime;
                    System.out.println("Total execution time in seconds: "+ elapsedTime/1000000000 +" Avg: "+ elapsedTime/(1000000000));
                    System.out.println("Total execution time in millis: "+ elapsedTime/1000000+" Avg: "+ elapsedTime/(1000000));
                    System.out.println("Total execution time in nano: "+ elapsedTime+" Avg: "+ elapsedTime);
                    System.out.println(bnb.visited);
                    System.out.println(bnb.min_cost);
                    break;

//                case 2:
//                    System.out.println("Podaj rozmiar grafu: ");
//                    int size = scanner.nextInt();
//                    System.out.println("Podaj liczbe iteracji");
//                    int iterations = scanner.nextInt();
//                    TimingForGenerator(size, iterations);
//                    break;
                default: break;
            }
        }
    }
    public static void TimingForGenerator(int size, int iterations){ //mierzy czas wszystkich iteracji grafu
        GraphGenerator generator = new GraphGenerator(size);
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            BnB bnb = new BnB(generator.size, generator.Generator());
            if(i==0){
                Printer printer = new Printer();
                System.out.println("Example graph: ");
                printer.print(bnb.graphs.get(0).size,bnb.graphs.get(0).graph);
            }
            bnb.Algorithm();
            System.out.println(bnb.visited);
            System.out.println(bnb.min_cost);
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in seconds: "+ elapsedTime/1000000000 +" Avg: "+ elapsedTime/(1000000000*iterations));
        System.out.println("Total execution time in millis: "+ elapsedTime/1000000+" Avg: "+ elapsedTime/(1000000*iterations));
        System.out.println("Total execution time in nano: "+ elapsedTime+" Avg: "+ elapsedTime/(iterations));

    }

}
