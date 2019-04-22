package com.igniteexamples.createcache;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class StartServerNode2 {

    public static void main(String[] args) {
        Ignite ignite = Ignition.start("example-ignite-config.xml");
        System.out.println();
        System.out.println(">>> ServerNode started.");
        System.out.println("\n" +
                "  _   _           _        ____  \n" +
                " | \\ | | ___   __| | ___  |___ \\ \n" +
                " |  \\| |/ _ \\ / _` |/ _ \\   __) |\n" +
                " | |\\  | (_) | (_| |  __/  / __/ \n" +
                " |_| \\_|\\___/ \\__,_|\\___| |_____|\n" +
                "                                 \n");


    }
}
