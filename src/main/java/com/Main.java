package com;

import com.birdbrain.Finch;

public class Main {
    static Finch lineTracker = new Finch("A");
    static int whiteLine;

    public static void calibration() throws InterruptedException {
        while (!lineTracker.getButton("B")) {
            whiteLine = (lineTracker.getLine("R") + lineTracker.getLine("L")) / 2 - 10;
        }
        System.out.println("White line color: " + whiteLine);
    }

    public static void main(String[] args) throws InterruptedException {

        int ind = 0;
        lineTracker.setMotors(0, 0);

        calibration();
        Thread.sleep(5000);

        while (!lineTracker.getButton("A")) {

            int right = lineTracker.getLine("R");
            int left = lineTracker.getLine("L");

            System.out.println(ind);
            System.out.println("Правый датчик:" + right);
            System.out.println("Левый датчик:" + left);

            if (right > whiteLine && left > whiteLine) {
                // фул белый
                lineTracker.setMotors(10, 10);
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
                    lineTracker.setMotors(0, 0);
                    lineTracker.setTurn("R", 45, 100);
                    lineTracker.setMotors(0, 0);

                    System.out.println(lineTracker.getDistance());

                    while (lineTracker.getDistance() < 100) {
                        // можно добавить сигнал, что он пропускает
                        Thread.sleep(999);
                    }


                    lineTracker.setTurn("L", 45, 100);

                    while (lineTracker.getDistance() < 40) {
                        // можно добавить сигнал, что он пропускает
                        Thread.sleep(999);
                    }

                    lineTracker.setMotors(10,10);
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
                lineTracker.setMotors(0, 5);
            } else if (right < whiteLine){
                // правый чёрный
                lineTracker.setMotors(5, 0);
            }
        }
        lineTracker.stop();
    }
}
