package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Controler program = new Controler();
        program.startProgram();
    }
}

//(Widok) Wyświetla menu
class Viev
{
    //metody
    //Wyświetla główne menu
    void showMenu()
    {
        System.out.println("##### MENU #####");
        System.out.println("1. Wprowadź macierze wejściowe.");
        System.out.println("2. Operacje arytmetyczne.");
        System.out.println("3. Wyświetl macierze.");
        System.out.println("4. Wyjdź.");
        System.out.println("################");

    }
    //Wyświetla operacje arytmetyczne
    void showOperations()
    {
        System.out.println("##### MENU - OPERACJE ARYTMETYCZNE #####");
        System.out.println("1. Transponuj macierze wejściowe.");
        System.out.println("2. Pomnóż macierze wejściowe.");
        System.out.println("3. Transponuj macierz wynikową.");
        System.out.println("4. Wyjdź.");
        System.out.println("########################################");
    }
    //Wyświetla opcje wyświetlenia macierzy
    void showMatrixes()
    {
        System.out.println("##### MENU - WYŚWIETL MACIERZE #####");
        System.out.println("1. Wyświetl macierze wejściowe.");
        System.out.println("2. Wyświetl macierz wynikową.");
        System.out.println("3. Wyjdź.");
        System.out.println("####################################");
    }
    //wyświetla macierz
    void showMatrix(float[][] arr, int opt)
    {
        if(opt == 1)
            System.out.println("Macierz wejściowa A:");
        else if(opt == 2)
            System.out.println("Macierz wejściowa B:");
        else if(opt == 3)
            System.out.println("Macierz wynikowa:");

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print("[" + arr[i][j] + "]");
            }
            System.out.print("\n");
        }
    }
}

//(Kontroler) Pobiera informacje od użytkownika i zleca ich przetworzenie
class Controler
{
    //zmienne i obiekty
    private Viev menu;
    private Model data;
    Scanner scan;
    private int rowsA, rowsB;
    private int colsA, colsB;

    //konstruktory
    Controler()
    {
        menu = new Viev();
        data = new Model();
        scan = new Scanner(System.in);
    }

    //metody
    //wyświetlanie menu, zlecanie operacji
    void startProgram()
    {
        int operation;
        while(true) {
            operation = this.manageMenu();

            if(operation == 1)
                this.getMatrixes();

            else if(operation == 21) {
                if(this.data.matrixA == null || this.data.matrixB == null) {
                    System.err.println("Macierz nie istnieje");
                    continue;
                }
                this.data.transponseMatrix(1);
                this.data.transponseMatrix(2);
            }

            else if(operation == 22) {
                if(this.data.matrixA == null || this.data.matrixB == null) {
                    System.err.println("Macierz nie istnieje");
                    continue;
                }
                this.data.multiplyMatrixes();
            }

            else if(operation == 23) {
                if(this.data.matrixC == null) {
                    System.err.println("Macierz nie istnieje");
                    continue;
                }
                this.data.transponseMatrix(3);
            }

            else if(operation == 31) {
                if(this.data.matrixA == null || this.data.matrixB == null) {
                    System.err.println("Macierz nie istnieje");
                    continue;
                }
                this.menu.showMatrix(this.data.matrixA, 1);
                this.menu.showMatrix(this.data.matrixB, 2);
            }

            else if(operation == 32) {
                if(this.data.matrixC == null) {
                    System.err.println("Macierz nie istnieje");
                    continue;
                }
                this.menu.showMatrix(this.data.matrixC, 3);
            }

            else if(operation == 5)
                break;
            else if(operation == -1)
                System.err.println("Nieprawidłowa opcja.");
        }

    }

    //kontrola menu
    private int manageMenu()
    {
        //zmienne

        int option;

        //wybór opcji
        menu.showMenu();
        option = scan.nextInt();

        if(option == 1)
            return 1;

        else if(option == 2) {
            menu.showOperations();
            option = scan.nextInt();
            if(option == 1)
                return 21;
            else if(option == 2)
                return 22;
            else if(option == 3)
                return 23;
            else if(option == 4)
                return 5;
            else
                return -1;
        }

        else if(option == 3) {
            menu.showMatrixes();
            option = scan.nextInt();
            if(option == 1)
                return 31;
            else if(option == 2)
                return 32;
            else if(option == 3)
                return 5;
            else
                return -1;
        }

        else if(option == 4)
            return 5;

        else
            return -1;
    }

    //pobieranie macierzy
    void getMatrixes()
    {
        //pobranie wymiarów
        System.out.println("Podaj po 2 wymiary (wiersze, kolumny) macierzy A i B:");
        this.rowsA = scan.nextInt();
        this.colsA = scan.nextInt();
        this.rowsB = scan.nextInt();
        this.colsB = scan.nextInt();

        if(this.colsA != this.rowsB){
            System.err.println("Nieprawidłowe wymiary macierzy.");
            return;
        }

        this.data.makeMatrixes(this.rowsA, this.colsA, this.rowsB, this.colsB);

        for(int i=0; i<rowsA; i++) {
            for(int j=0; j<colsA; j++){
                System.out.println("Podaj element [" + i + "][" + j + "] macierzy A:");
                this.data.matrixA[i][j] = scan.nextFloat();
            }
        }

        for(int i=0; i<rowsB; i++) {
            for(int j=0; j<colsB; j++){
                System.out.println("Podaj element [" + i + "][" + j + "] macierzy B:");
                this.data.matrixB[i][j] = scan.nextFloat();
            }
        }
    }
}

//(Model) Przechowuje dane i przeprowadza na nich oepracje
class Model
{
    //macierze
    float[][] matrixA;
    float[][] matrixB;
    float[][] matrixC;

    //metody
    //inicjuje macierze
    void makeMatrixes(int rowsA, int colsA, int rowsB, int colsB)
    {
        matrixA = new float[rowsA][colsA];
        matrixB = new float[rowsB][colsB];
    }

    //mnoży macierze
    void multiplyMatrixes()
    {
        matrixC = new float[this.matrixA.length][this.matrixB[0].length];
        for(int k=0; k<this.matrixB[0].length; k++) {
            for (int i = 0; i < this.matrixA.length; i++) {
                for (int j = 0; j < this.matrixB.length; j++) {
                    this.matrixC[i][k] += this.matrixA[i][j] * this.matrixB[j][k];
                }
            }
        }
    }

    //transponuje macierz
    void transponseMatrix(int opt)
    {
        float[][] arr;
        float[][] temp;

        if(opt == 1)
            arr = this.matrixA;
        else if(opt == 2)
            arr = this.matrixB;
        else
            arr = this.matrixC;

        temp = new float[arr.length][arr[0].length];

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                temp[j][i] = arr[i][j];
            }
        }

        if(opt == 1)
            this.matrixA = temp;
        else if(opt == 2)
            this.matrixB = temp;
        else if(opt == 3)
            this.matrixC = temp;
    }

}