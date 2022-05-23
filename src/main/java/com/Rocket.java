package com;

import com.birdbrain.Finch;

import java.util.concurrent.TimeUnit;

// Робот запускает ракету в космос, катается и орёт в зависимости от скорости
public class Rocket {
    public static void main(String[] args) throws InterruptedException {
        Finch myFinch = new Finch("A");
        int patternArray[] = {  0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 1, 0, 0,
                0, 1, 0, 1, 0,};
        myFinch.setDisplay(patternArray);

        for (int j = 0; j < 5; j++) {
            myFinch.playNote(60 + j,1);
            TimeUnit.MILLISECONDS.sleep(1000/(j+1));
            for (int i = 0; i < 25; i++) {
                if (patternArray[i] == 1) {
                    if (i - 5 > 0) {
                        patternArray[i - 5] = 1;
                    }
                    patternArray[i] = 0;
                }
            }
            myFinch.setDisplay(patternArray);
        }

        //myFinch.setMove("B", 100, 50);
        myFinch.setMotors(50,50);
        while(!myFinch.getButton("A")) {
            int a = myFinch.getDistance();
            myFinch.setMotors(Math.min(a, 100), Math.min(a, 100));
            myFinch.playNote(Math.min(Math.max(a,32), 135),0.5);
            if (a < 35 || a >= 250) {
                int count = 0;
                while (myFinch.getDistance() < 35 || myFinch.getDistance() >= 250) {
                    if (Math.random() % 2 == 0)
                        myFinch.setMotors(0,40);
                    else
                        myFinch.setMotors(40,0);
                    count++;
                    if (count > 50) {
                        myFinch.setMove("B", 20, 100);
                    }
                }
            }
        }
        //setMove(String direction, double distance, double speed)

    }
}
