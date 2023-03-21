package org.mateusz;

public class IO {

    public double t_start = 1000;
    public double t_end = 0;
    public double reduc = 0.99;
    public int time = 10;


    public void setT_start(double t_start) {
        this.t_start = t_start;
    }

    public void setT_end(double t_end) {
        this.t_end = t_end;
    }

    public void setReduc(double reduc) {
        this.reduc = reduc;
    }

    public void setTime(int time) {
        this.time = time;
    }

    void print(){
        System.out.print(
                "\n1. Wyczytaj graf podajac sciezke pliku"+
                "\n2. Uruchom algorytm SA"+
                "\n3. Ustaw tempereture startowa:   obecnie " + t_start+
                "\n4. Ustaw tempereture koncowa:    obecnie " + t_end+
                "\n5. Ustaw redukcje temperatury:   obecnie " + reduc+
                "\n6. Ustaw kryterium stopu:        obecnie " + time+ " s"+
                "\n7. Przywroc ustawienia domyslne"+
                "\n0. Wyjscie"+
                "\nWybor: "
        );
    }
}
