import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graphs{ //głowna struktura wykorzystywana w programie
    Integer size = 0; //rozmiar grafu
    Integer cost = 0; //koszt dojścia do danego ustawienia grafu
    ArrayList<ArrayList<Integer>> graph; //graf w postaci macierzy
    ArrayList<Integer> path=new ArrayList<>(); //sciezka do aktualnego ustawienia grafu

    Graphs(int size, ArrayList<ArrayList<Integer>> graph) { //konstruktor grafu, służący do tworzenia nowego grafu
        this.size = size;
        this.graph = copyArray(graph);
        path.add(0);
    }
    Graphs(int size, ArrayList<ArrayList<Integer>> graph,int cost,ArrayList<Integer> path){ //konstruktor służacy do kopiowana wczesniej utworzonego grafu
        this.size = size;
        this.graph = copyArray(graph);
        this.cost = cost;
        this.path = copySimpleArray(path);
    }

    public void AdjustingGraph() { //dostosowywanie grafu, oznaczenie zer w grafie jako infinity(int.maxvalue)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (graph.get(i).get(j) == 0) {
                    graph.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }
    }

    public void ReducingGraph() { //redukowanie macierzy
        int min=0;
        for (int i = 0; i < size; i++) { //iteracja po wierszach i znalezienie najmniejszej wartosci
            min = Collections.min(graph.get(i));
            if (min != 0 && min != Integer.MAX_VALUE) { //jesli najmniejsza wartosc nie jest 0 albo inf
                for (int j = 0; j < size; j++) {
                    if (graph.get(i).get(j) != Integer.MAX_VALUE) //jesli wartosc wynosi inf nie odejmuj
                    {
                        graph.get(i).set(j, graph.get(i).get(j) - min); //odjemij najmniejsza wartosc od elementów wiersza
                    }
                }
                cost+=min; //dodanie do kosztu przejscia wartosci minimalnej
                if(cost<0) cost=Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < size; i++) { //znajdywanie i odejmowanie najmniejszej wartosci w kolumnach (ten sam kod tylko dla kolumn -> patrz wyzej)
            min = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (graph.get(j).get(i) < min) min = graph.get(j).get(i);
            }
            if (min != 0 && min != Integer.MAX_VALUE) {
                for (int j = 0; j < size; j++) {
                    if (graph.get(j).get(i) != Integer.MAX_VALUE)
                    {
                        graph.get(j).set(i, graph.get(j).get(i) - min);
                    }
                }
                cost+=min;
                if(cost<0)cost=Integer.MAX_VALUE;
            }
        }

    }
    public void Traceback(int to){ //jesli odwiedzilismy juz dany wierzcholek nie chcemy do niego wracac
        for (int i = 0; i < size; i++) { //wiec sciezke do danego wierzcholka z odwiedzonego ustawia sie na inf
            if(path.contains(i)) graph.get(to).set(i,Integer.MAX_VALUE);
        }
    }
    //kalkulowanie kosztu grafu i aktualizacja macierzy dla przejscia z wierzchołka from do to
    public void CalculatingCost(int from, int to){
        path.add(to);
        cost+=graph.get(from).get(to);//koszt przejscia zwieksza sie o przejscie z wierzcholka from do to
        if(cost<0) cost=Integer.MAX_VALUE;
        for (int i = 0; i < size; i++)
        {
            graph.get(i).set(to,Integer.MAX_VALUE); //zmiana wartosci elementow w wierzu to na inf
            graph.get(from).set(i,Integer.MAX_VALUE);//zmiana wartosci elementow w kolumnie from na inf
        }
        graph.get(to).set(from,Integer.MAX_VALUE); //przejscie z wierzcholka from do to oznacza sie jako inf
    }
    //metoda pozwala na deep copy grafu
    private ArrayList<ArrayList<Integer>> copyArray(ArrayList<ArrayList<Integer>> array) {
        ArrayList<ArrayList<Integer>> newArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            ArrayList<Integer> tempArray = new ArrayList<>();
            for (int j = 0; j < array.get(i).size(); j++) {
                tempArray.add(array.get(i).get(j));
            }
            newArray.add(tempArray);
        }
        return newArray;
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


