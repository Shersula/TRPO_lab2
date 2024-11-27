package com.trpo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        KeyTracker k = new KeyTracker();
        Thread t = new Thread(k);

        List<Writer> writers = new ArrayList<>();
        writers.add(new FileWriter("Test.txt")); // Writer to file
        writers.add(new ConsoleWriter()); // Writer to console

        writers.forEach(w -> k.getSubject().subscribe(w)); //Subscribe writer

        System.out.println("Thread started. Press Ctrl+Z to stop and get output");
        t.start();
    }
}