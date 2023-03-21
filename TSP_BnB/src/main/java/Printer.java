import java.util.ArrayList;

public class Printer {
    public void print(int size, ArrayList<ArrayList<Integer>> graph){ //drukuje dany graf po podaniu jego rozmiaru i macierzy grafu
        for (int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                if(graph.get(i).get(j)==Integer.MAX_VALUE){
                    System.out.print("inf ");
                }
                else{System.out.print(graph.get(i).get(j) + " ");}
            }
            System.out.println();
        }
    }
}
