package org.mateusz;

public class IO {

    public double wspl_krzyzowania = 0.8;
    public double wspl_mutacji = 0.01;
    public int pop_start = 50;
    public int time = 10;



    public void setWspl_krzyzowania(double wspl_krzyzowania) {
        this.wspl_krzyzowania = wspl_krzyzowania;
    }

    public void setWspl_mutacji(double wspl_mutacji) {
        this.wspl_mutacji = wspl_mutacji;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPop_start(int pop_start) {
        this.pop_start = pop_start;
    }

    void print(){
        System.out.print(
                "\n1. Wyczytaj graf podajac sciezke pliku"+
                        "\n2. Uruchom algorytm SA dla mutacji: transpozycja"+
                        "\n3. Ustaw współczynnik krzyżowania:             obecnie " + wspl_krzyzowania+
                        "\n4. Ustaw współczynnik mutacji:                 obecnie " + wspl_mutacji+
                        "\n5. Podaj wielkosc poulacji poczatkowej:        obecnie " + pop_start+
                        "\n6. Ustaw kryterium stopu:                      obecnie " + time+ " s"+
                        "\n7. Przywroc ustawienia domyslne"+
                        "\n8. Uruchom algorytm SA dla mutacji: insercja"+
                        "\n0. Wyjscie"+
                        "\nWybor: "
        );
    }
}
