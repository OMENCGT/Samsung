package com.company;

import java.util.Scanner;

public class Main {

    static int[][] gameBoard = new int[3][3];

    static Scanner scanner = new Scanner(System.in);

    static String playerX = "X";
    static String playerO = "O";
    static String currentPlayer = playerX;

    static boolean isWin = false;
    static boolean isDraw = false;

    static void initGameLoop() {
        while (!isWin && !isDraw) {
            int rowTurn = readCoord("строку");
            if (!checkRange(rowTurn)) {
                System.out.println("Выход за границы поля");
                continue;
            }
            int colTurn = readCoord("столбец");
            if (!checkRange(colTurn)) {
                System.out.println("Выход за границы поля");
                continue;
            }
            if (checkSpace(rowTurn, colTurn)) {
                gameBoard[rowTurn][colTurn] = getCurrentPlayer();
            } else {
                System.out.println("Ячейка уже занята!");
                continue;
            }
            changePlayer();
            drawGameBoard();
            checkWin();
        }
        while (true){
            if(isWin) break;
            System.out.println("aoaoaoao");
        }
    }

    static int readCoord(String description) {
        System.out.println("Введите " + description);
        return scanner.nextInt();
    }

    static boolean checkRange(int coord) {
        return coord >= 0 && coord <= gameBoard.length - 1;
    }

    static boolean checkSpace(int coordX, int coordY) {
        return gameBoard[coordX][coordY] == 0;
    }

    static int getCurrentPlayer() {
        return currentPlayer.equalsIgnoreCase(playerX) ? 1 : 2;
    }

    static void changePlayer() {
        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
    }

    static void initGameBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = 0;
            }
        }
    }

    static void checkWin()
    {
        int nX = 0, nO= 0;
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {

                if(gameBoard[row][col] == 1){nO = 0; nX++;}
                else if(gameBoard[row][col] == 2){nX = 0; nO++;}
            }
            if(nX == 3|| nO == 3) break;
            nO = 0;
            nX = 0;
        }
        if(nX == 3 || nO == 3){isWin = true;}
        else {
            for (int row = 0; row < gameBoard.length; row++) {
                for (int col = 0; col < gameBoard[row].length; col++) {

                    if (gameBoard[col][row] == 1) {
                        nO = 0;
                        nX++;
                    } else if (gameBoard[col][row] == 2) {
                        nX = 0;
                        nO++;
                    }
                }
                if(nX == 3|| nO == 3) break;
                nO = 0;
                nX = 0;
            }
        }
        if(nX == 3 || nO == 3){isWin = true;}
        else{
            for (int row = 0; row < gameBoard.length; row++) {
                for (int col = 0; col < gameBoard[row].length; col++) {
                    if(gameBoard[row][col] == 1 && row == col){nO = 0; nX++;}
                    else if(gameBoard[row][col] == 2 && row == col){nX = 0; nO++;}
                    if(gameBoard[row][col] == 1 && row == col + 2){nO = 0; nX++;}
                    else if(gameBoard[row][col] == 2 && row == col + 2){nX = 0; nO++;}
                }
            }
        }
        if(nX == 3 || nO == 3){
            isWin = true;
            if(nX == 3) {
                System.out.println("игрок X выиграл");
            }
            else{
                System.out.println("игрок O выиграл");
            }
        }
    }

    static void checkDraw()
    {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if(gameBoard[row][col] != 0) isDraw = true;
                else {isDraw = false; break;}
            }
            if(!isDraw) break;
        }
    }

    static void drawGameBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                switch (gameBoard[row][col]) {
                    case 0:
                        System.out.print("  ");
                        break;
                    case 1:
                        System.out.print(" X ");
                        break;
                    case 2:
                        System.out.print(" O ");
                        break;
                }
                if (col != gameBoard[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != gameBoard.length - 1) {
                System.out.println("------------");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
            while(true) {
                initGameBoard();
                initGameLoop();
                isWin = false;
                isDraw = false;
                System.out.println("Хотите продолжить заново?\n1-да\n0-нет");
                if(scanner.nextInt() == 0) break;
                for(int i = 0; i < 20; i++)
                {
                    System.out.println('\n');
                }
            }
    }

}
