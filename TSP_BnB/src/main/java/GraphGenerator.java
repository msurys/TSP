import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {
    int size;
    GraphGenerator(int size){
        this.size=size;
    }
    public ArrayList<ArrayList<Integer>> Generator(){ //generuje nowy graf pelny z losowymi wartosciami przez zastosowanie Random
        Random rand=new Random();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<Integer>());
            for (int j = 0; j < size; j++) {
                if(i==j) {
                    graph.get(i).add(0);
                }
                else{
                    graph.get(i).add(rand.nextInt((99-1)+1)+1);
                }
            }
        }
        return graph; //zwraca nowy graf z losowymi przejsciami
    }
}
