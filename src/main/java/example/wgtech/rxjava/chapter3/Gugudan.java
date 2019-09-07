package example.wgtech.rxjava.chapter3;

import java.util.Scanner;

public class Gugudan {
    private void plainJava() {
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        for (int row = 0; row <= 9; row++) {
            System.out.println(dan + "*" + row + " = " + dan * row);
        }
    }

    public static void main(String[] args) {
        Gugudan gugu = new Gugudan();
        gugu.plainJava();

    }
}
