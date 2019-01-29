package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Printer {

    private static final int MAX_NUMBER_OF_PRINTERS = 10;
    private static List<Printer> instances = new ArrayList<>();
    private static int nextId = 0;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss");

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;

    public Printer(int id) {
        this.id = id;
    }

    public static Printer getInstance() {
       if(instances.size() > 0){
           // try find an available printer
           for (Printer p : instances) {
               if(p.isAvailable()){
                   return p;
               }
           }
           return getPrinter();

       }else{
           return getPrinter();
       }
    }

    private static Printer getPrinter() {
        if (instances.size() == MAX_NUMBER_OF_PRINTERS) {
            //return a random printer
            return getRandomPrinter();

        } else {
            // create new printer
            Printer newPrinter = new Printer(nextId++);
            instances.add(newPrinter);
            return newPrinter;
        }
    }

    private static Printer getRandomPrinter(){
        return instances.get(new Random().nextInt(instances.size()));
    }

    // Prints out the given String
    public void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + id + " is printing.");
        busyEndTime = LocalTime.now().plusSeconds(5);
    }

    // Returns true if the printer is ready to print now.
    public boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }
}
