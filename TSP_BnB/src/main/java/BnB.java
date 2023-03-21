import java.io.IOException;
import java.util.ArrayList;

public class BnB {
    ArrayList<Graphs> graphs=new ArrayList<>(); //lista obiektow grafu
    ArrayList<Integer> visited = new ArrayList<>(); //lista odwiedzonych wierzcholkow
    int index;
    int min_cost;
    int size;

    BnB(){
        Reader reader = new Reader();
        try {
            reader.ReadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphs.add(0,new Graphs(reader.size,reader.graph));
    }
    BnB(int size,ArrayList<ArrayList<Integer>> graph){
        graphs.add(0,new Graphs(size,graph));
    }

    //glowny algorytm bnb wyznaczajacy koszt i sciezke przejscia z wierzcholka 0 do 0 przez wszystkie pozostale wierzcholki
    public void Algorithm() {
        size = graphs.get(0).size;  //pobranie rozmiaru grafu
        graphs.get(0).AdjustingGraph(); //zamiana 0 na inf -> brak polaczenia
        graphs.get(0).ReducingGraph(); //redukowanie grafu dla poczatkowego wierzcholka
        for (int i = 1; i < graphs.get(0).size; i++) { //dodanie nowego grafu do tablicy grafow, ktory jest kopia zredukowanego grafu poaczatkowego
            graphs.add( new Graphs(graphs.get(0).size, graphs.get(0).graph, graphs.get(0).cost, graphs.get(0).path));
            graphs.get(i).CalculatingCost(0, i); //obliczanie sciezki dla przejscia od 0 do pozostalych wierzcholkow
            graphs.get(i).ReducingGraph(); //redukowanie grafu dla danego przejscia
        }
        graphs.remove(0); //usuniecie grafu o najnizszym koszcie przejscia
        while(visited.size()!=size) { //jesli lista odwiedzonych wierzcholkow nie zawiera wszystkich wierzcholkow
            FindNext(); //znalezienie nastepnego grafu
            visited = graphs.get(index).path; //aktualizacja listy odwiedzonych wierzcholkow
            for (int i = 0; i < graphs.get(0).size; i++) {
                if (!visited.contains(i)) { //jesli lista odwiedzonych grafow zawiera dany wierzcholek nie dodawaj nowego grafu
                    graphs.add(new Graphs(graphs.get(index).size, graphs.get(index).graph, graphs.get(index).cost, graphs.get(index).path)); //kopiowanie grafu poczatkowego do rozpatrywania jego polaczen
                    graphs.get(graphs.size() - 1).CalculatingCost(visited.get(visited.size() - 1), i); //obliczanie sciezki przejscia z danego wierzcholka do jego nieodwiedzonych polaczen
                    graphs.get(graphs.size() - 1).Traceback(i); //jesli odwiedzilismy dany wierzcholek nie chcemy do niego wracac
                    graphs.get(graphs.size() - 1).ReducingGraph(); //redukowanie grafu dla danego przejscia
                }
            }
            graphs.remove(index); //jesli dany graf nie ma innych polaczen usun go
        }
        visited.add(0); //dodanie 0 do visited (na potrzeby drukowania)
    }
    private void FindNext(){ //znajduje index nastepnego grafu (o najniższym koszcie), który bedzie przeszukiwany
        min_cost=Integer.MAX_VALUE;
        for (int i = 0; i < graphs.size(); i++) {
            if(graphs.get(i).cost<min_cost){
                min_cost=graphs.get(i).cost;
                index=i;
            }
        }
    }
}
