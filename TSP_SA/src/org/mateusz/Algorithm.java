package org.mateusz;

import java.util.*;

public class Algorithm {

    public double t_start;
    public double t_end;
    public double reduc;
    public double opt = 1163;
    public int time;
    public int size;
    public int delta;
    public ArrayList<ArrayList<Integer>> graph ;
    public ArrayList<Integer> champion_path = new ArrayList<>();
    public ArrayList<Integer> current_path = new ArrayList<>();
    public ArrayList<Integer> random_path = new ArrayList<>();
    public int minimal_cost;


    Algorithm(double t_start, double t_end, double reduc, int time, int size, ArrayList<ArrayList<Integer>> graph){
        this.reduc = reduc;
        this.time = time;
        this.t_end = t_end;
        this.t_start = t_start;
        this.size = size;
        this.graph = graph;
    }

    public void CalculateStartCost(){ //wybranie losowego rozwiazania i obliczenie kosztu przejscia sciezki
        for(int i = 1; i<size; i++){
            champion_path.add(i);
        }
        Collections.shuffle(champion_path); //wymieszanie sciezki
        champion_path.add(0,0); //dodanie 0 jako punktu startowego i punktu powrotu
        champion_path.add(size,0);
        minimal_cost=CalculateCurrentCost(champion_path);
    }

    public int CalculateCurrentCost(ArrayList<Integer> path){ //obliczanie kosztu przejscia sciezki
        int cost = 0;
        for(int i = 0; i<size-1; i++){
            cost+=graph.get(path.get(i)).get(path.get(i+1));
        }
        return cost;
    }
    public void SA(){ //glowne cialo algorytmu

        Random rand = new Random(); //losowe liczby w javie
        CalculateStartCost();
        current_path = copySimpleArray(champion_path); //skopiowanie champion_path do current_path nad ktora bedziemy pracowac
        long start = System.currentTimeMillis(); //obecny czas w ms
        while(t_start > t_end && System.currentTimeMillis() < start + time*1000){ //warunki stopu dla temperatury i czasu
            for(int i = 0; i < 1000*size; i++){ //pętla która zapewnia równowagę dla danej temperatury
                random_path = copySimpleArray(current_path); //skopiowanie current do losowej sciezki
                Collections.swap(random_path,rand.nextInt(size-3)+1,rand.nextInt(size-3)+1);
                //zamiana miejscami dwoch elementow poza pierwszym i ostatnim poniewaz sa to zera
                delta = CalculateCurrentCost(random_path) - CalculateCurrentCost(current_path); //obliczanie roznicy w koszcie miedzy losowa a obecna
                if(delta <= 0){
                    current_path = copySimpleArray(random_path); //ustawienie obecnej scieki na wartosc losowej sciezki
                    if(CalculateCurrentCost(current_path) < CalculateCurrentCost(champion_path)){
                        champion_path = copySimpleArray(current_path); //jesli current jest mniejsze od najlepszej zamiania
                    }
                }
                else{ //wybranie losowej sciezki zamiast current, gdy bedzie spelniony warunek
                    double r = Math.random();
                    if(r < Math.exp(-delta/t_start)){ //warunek
                        current_path = copySimpleArray(random_path);
                    }
                }
                t_start*=reduc; //obnizenie temperatury
            }
        }
        minimal_cost = CalculateCurrentCost(champion_path); //obliczanie kosztu sciezki
        System.out.println("path: "+ champion_path); //drukowanie
        System.out.println("cost "+ minimal_cost);
    }
    public void SA_zero(){ //dokladnie ta sama funkcja jednak ze wzgledu na zaokraglenia liczb wybralem rozdzielenie funkcji gdy warunek stopu wynosi 0
        Random rand = new Random();
        CalculateStartCost();
        current_path = copySimpleArray(champion_path);
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() < start + time*1000){ //jedyna linijka która posiada zmiany
            for(int i = 0; i < 1000*size; i++){
                random_path = copySimpleArray(current_path);
                Collections.swap(random_path,rand.nextInt(size-3)+1,rand.nextInt(size-3)+1);
                delta = CalculateCurrentCost(random_path) - CalculateCurrentCost(current_path);
                if(delta <= 0){
                    current_path = copySimpleArray(random_path);
                    if(CalculateCurrentCost(current_path) < CalculateCurrentCost(champion_path)){
                        champion_path = copySimpleArray(current_path);
                    }
                }
                else{
                    double r = Math.random();
                    if(r < Math.exp(-delta/t_start)){
                        current_path = copySimpleArray(random_path);
                    }
                }
                t_start*=reduc;
            }
        }
        minimal_cost = CalculateCurrentCost(champion_path);
        //System.out.println("path: "+ champion_path);
        //System.out.println("cost "+ minimal_cost);
        System.out.println("time" + time);
        System.out.println("ebe" + (minimal_cost-opt)/opt);
    }

    //metoda pozwala na deep copy sciezki grafu
    private ArrayList<Integer> copySimpleArray(ArrayList<Integer> array){
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            newArray.add(array.get(i));
        }
        return newArray;
    }
}
