package com;

import com.birdbrain.Finch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Finch myFinch = new Finch("A");
        int whiteLine = 80;

        int ind = 0;

        while (!myFinch.getButton("A")) {
            int right = myFinch.getLine("R");
            int left = myFinch.getLine("L");

            System.out.println(ind);
            System.out.println("Правый датчик:" + right);
            System.out.println("Левый датчик:" + left);

            if (right > whiteLine && left > whiteLine) {
                // фул белый
                myFinch.setMotors(10, 10);
            } else if (right < whiteLine && left < whiteLine) {
                System.out.println("[Фул чёрный]Правый датчик:" + right);
                System.out.println("[Фул чёрный]Левый датчик:" + left);
                // фул чёрный
//                long x = Math.round(Math.random());
                //System.out.println(x);
//                if (x == 0)
//                    myFinch.setTurn("R",90,20);
//                else
//                    myFinch.setTurn("L",90,20);

                if (ind == 0) {
                    myFinch.setMotors(0, 0);
                    myFinch.setTurn("R", 45, 100);
                    myFinch.setMotors(0, 0);

                    System.out.println(myFinch.getDistance());

                    while (myFinch.getDistance() < 100) {
                        // можно добавить сигнал, что он пропускает
                        Thread.sleep(999);
                    }


                    myFinch.setTurn("L", 45, 100);

                    while (myFinch.getDistance() < 40) {
                        // можно добавить сигнал, что он пропускает
                        Thread.sleep(999);
                    }

                    myFinch.setMotors(10,10);
                    Thread.sleep(999);
//                    ind++;

                }
                else if (ind == 2)
                {
                    ind = -1;
                }
                ind++;
                Thread.sleep(999);

            } else if (left < whiteLine) {
                // левый чёрный
                myFinch.setMotors(0, 5);
            } else if (right < whiteLine){
                // правый чёрный
                myFinch.setMotors(5, 0);
            }
        }
        myFinch.stop();
    }
}
