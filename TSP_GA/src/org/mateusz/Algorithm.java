package org.mateusz;

import java.util.*;

public class Algorithm {

    public double wspl_krzyzowania;
    public double wspl_mutacji;
    public int kryt_stopu;
    public int size;
    public int population_start;
    public ArrayList<ArrayList<Integer>> graph ;
    public int bestCost= Integer.MAX_VALUE;
    public ArrayList<Integer> bestRoute = new ArrayList<Integer>();
    Random rand = new Random();
    private final int tournamentSize = 10;

    Algorithm(double wspl_krzyzowania,double wspl_mutacji, int kryt_stopu, int size,ArrayList<ArrayList<Integer>> graph, int population_start ){
        this.wspl_krzyzowania = wspl_krzyzowania;
        this.wspl_mutacji = wspl_mutacji;
        this.kryt_stopu = kryt_stopu;
        this.size = size;
        this.graph = graph;
        this.population_start = population_start;
    }

    public void alg_gen() {
        // ustawienie startowej populacji
        ArrayList<ArrayList<Integer>> populacja = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < population_start; i++) {
            ArrayList<Integer> path = new ArrayList<Integer>();
            for(int j = 1; j<size; j++){
                path.add(j);
            }
            Collections.shuffle(path); //wymieszanie sciezki
            populacja.add(path);
        }

        long start = System.currentTimeMillis(); //obecny czas w ms
        while(System.currentTimeMillis() < start + kryt_stopu*1000) //warunek zatrzymania
        {
            // krzyzowanie rodzicow
            ArrayList<ArrayList<Integer>> nowaPopulacja = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < populacja.size(); i += 2) {
                if(Math.random() < wspl_krzyzowania) {
                    //krzyzowanie rodzicow wybranych za pomoca metody selectParents
                    ArrayList<ArrayList<Integer>> children= SinglePointCrossover(populacja.get(selectParents(populacja)[0]),populacja.get(selectParents(populacja)[1]));
                    nowaPopulacja.add(children.get(0));
                    nowaPopulacja.add(children.get(1));
                }
                else {
                    nowaPopulacja.add(populacja.get(i));
                    nowaPopulacja.add(populacja.get(i+1));
                }
            }

            // mutowanie kazdego z nowych osobnikow
            for (int i = 0; i < nowaPopulacja.size(); i++) {
                if (Math.random() < wspl_mutacji) {
                    ArrayList<Integer> mutatedPath = mutowanieTranspozycja(nowaPopulacja.get(i));
                    nowaPopulacja.set(i, mutatedPath);
                }
            }
            // ustawienie nowej populacji
            populacja = nowaPopulacja;

            // wyszukiwanie nowych najlepszych rozwiazan
            int currentBestCost = Integer.MAX_VALUE;
            ArrayList<Integer> currentBestRoute = new ArrayList<Integer>();
            for (int i = 0; i < populacja.size(); i++) {
                int cost = calculateCost(populacja.get(i));
                if (cost < currentBestCost) {
                    currentBestCost = cost;
                    currentBestRoute = populacja.get(i);
                }
            }
            if (currentBestCost < bestCost) {
                bestCost = currentBestCost;
                bestRoute = currentBestRoute;
            }
        }
        bestRoute.add(0,0);
        bestRoute.add(bestRoute.size(),0);
    }
    public void alg_gen2() {
        // ustawienie startowej populacji
        ArrayList<ArrayList<Integer>> populacja = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < population_start; i++) {
            ArrayList<Integer> path = new ArrayList<Integer>();
            for(int j = 1; j<size; j++){
                path.add(j);
            }
            Collections.shuffle(path); //wymieszanie sciezki
            populacja.add(path);
        }

        long start = System.currentTimeMillis(); //obecny czas w ms
        while(System.currentTimeMillis() < start + kryt_stopu*1000) //warunek zatrzymania
        {
            // krzyzowanie rodzicow
            ArrayList<ArrayList<Integer>> nowaPopulacja = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < populacja.size(); i += 2) {
                if(Math.random() < wspl_krzyzowania) {
                    //krzyzowanie rodzicow wybranych za pomoca metody selectParents
                    ArrayList<ArrayList<Integer>> children= SinglePointCrossover(populacja.get(selectParents(populacja)[0]),populacja.get(selectParents(populacja)[1]));
                    nowaPopulacja.add(children.get(0));
                    nowaPopulacja.add(children.get(1));
                }
                else {
                    nowaPopulacja.add(populacja.get(i));
                    nowaPopulacja.add(populacja.get(i+1));
                }
            }

            // mutowanie kazdego z nowych osobnikow
            for (int i = 0; i < nowaPopulacja.size(); i++) {
                if (Math.random() < wspl_mutacji) {
                    ArrayList<Integer> mutatedPath = mutowanieInsercja(nowaPopulacja.get(i));
                    nowaPopulacja.set(i, mutatedPath);
                }
            }
            // ustawienie nowej populacji
            populacja = nowaPopulacja;

            // wyszukiwanie nowych najlepszych rozwiazan
            int currentBestCost = Integer.MAX_VALUE;
            ArrayList<Integer> currentBestRoute = new ArrayList<Integer>();
            for (int i = 0; i < populacja.size(); i++) {
                int cost = calculateCost(populacja.get(i));
                if (cost < currentBestCost) {
                    currentBestCost = cost;
                    currentBestRoute = populacja.get(i);
                }
            }
            if (currentBestCost < bestCost) {
                bestCost = currentBestCost;
                bestRoute = currentBestRoute;
            }
        }
        bestRoute.add(0,0);
        bestRoute.add(bestRoute.size(),0);
    }

    //turniejowa selekcja rodzicow
    public int[] selectParents(ArrayList<ArrayList<Integer>> population) {
        int[] parents = new int[2];
        int[] tournament = new int[tournamentSize];

        // Losowanie pierwszego rodzica
        for (int i = 0; i < tournamentSize; i++) {tournament[i] = rand.nextInt(population.size());}
        //ustawienie pierwszego rodzica i przypisanie go do zwracanej tablicy
        int bestFitnessIndex = getBestFitnessIndex(population, tournament);
        parents[0] = tournament[bestFitnessIndex];

        // Losowanie drugiego rodzica
        for (int i = 0; i < tournamentSize; i++) {tournament[i] = rand.nextInt(population.size());}
        //ustawienie drugiego rodzica i przypisanie go do zwracanej tablicy
        bestFitnessIndex = getBestFitnessIndex(population, tournament);
        parents[1] = tournament[bestFitnessIndex];
        return parents;
    }

    // wybieranie rodzica z najmniejszym kosztem z uczestnikow turnieju
    private int getBestFitnessIndex(ArrayList<ArrayList<Integer>> population, int[] tournament) {
        int bestFitnessIndex = 0;
        int bestFitness = Integer.MAX_VALUE;
        for (int i = 0; i < tournamentSize; i++) {
            int cost = calculateCost(population.get(tournament[i]));
            if (cost < bestFitness) {
                bestFitness = cost;
                bestFitnessIndex = i;}
        }
        return bestFitnessIndex;
    }

    // mutowanie wykonane jako transpozycja
    public ArrayList<Integer> mutowanieTranspozycja(ArrayList<Integer> path) {
        Collections.swap(path, rand.nextInt(size-1),rand.nextInt(size-1));
        return path;
    }

    public ArrayList<ArrayList<Integer>> SinglePointCrossover(ArrayList<Integer> firstParent, ArrayList<Integer> secondParent) {

        ArrayList<ArrayList<Integer>> newChildren = new ArrayList<>();
        ArrayList<Integer> firstChild = new ArrayList<>();
        ArrayList<Integer> secondChild = new ArrayList<>();
        int point = rand.nextInt(size-1);

        for (int i = 0; i < size - 1; i++) { //przejscie po wszystkich elementach rodzica
            if (i < point) { //wybranie losowego punktu krzyzowania rodzicow
                firstChild.add(firstParent.get(i)); //dodanie pierwszej czesci do pierwszego dziecka
                secondChild.add(secondParent.get(i)); //dodanie pierwszej czesci do drugiego dziecka
            }
            else {
                //jesli dziecko nie zawiera elementu drugiego rodzica nalezy go dodac
                if (!firstChild.contains(secondParent.get(i))) firstChild.add(secondParent.get(i));
                    //jesli dziecko nie zawiera elementu pierwszego rodzica nalezy go dodac
                else if (!firstChild.contains(firstParent.get(i))) firstChild.add(firstParent.get(i));
                if (!secondChild.contains(firstParent.get(i))) secondChild.add(firstParent.get(i));
                else if (!secondChild.contains(secondParent.get(i))) secondChild.add(secondParent.get(i));
            }
        }
        //dzieci powinny zawierac wszyskie elementy i powinny miec taki sam rozmiar jak rodzice
        if (firstChild.size() != firstParent.size()) pathContainsAll(firstChild);
        if (secondChild.size() != secondParent.size()) pathContainsAll(secondChild);
        //dodanie dzieci do zwracanego arraylista
        newChildren.add(firstChild);
        newChildren.add(secondChild);
        return newChildren;
    }

    private ArrayList<Integer> pathContainsAll(ArrayList<Integer> path) { //dopełnianie sciezki, jesli nie zawiera ona wszystkich elementow
        for (int i = 1; i < size; i++) {
            if (!path.contains(i)) path.add(i);
        }
        return path;
    }

    public int calculateCost(ArrayList<Integer> path){ //obliczanie kosztu przejscia sciezki z uwzglednieniem wierzcholka poczatkowego i koncowego
        int cost = 0;
        cost+=graph.get(0).get(path.get(0));
        for(int i = 0; i< path.size()-1; i++){
            cost+=graph.get(path.get(i)).get(path.get(i+1));
        }
        cost+=graph.get(path.get(path.size()-1)).get(0);
        return cost;
    }

    public ArrayList<Integer> mutowanieInsercja(ArrayList<Integer> path) {
        int index = rand.nextInt(size-1);
        int tmp=path.get(index);
        path.remove(index);
        path.add(rand.nextInt(size-1),tmp);
        return path;
    }

}
