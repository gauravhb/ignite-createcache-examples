package com.igniteexamples.createcache;

import com.igniteexamples.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class StartServerNode {

    public static void main(String[] args) {
        Ignite ignite = Ignition.start("example-ignite-config.xml");
        System.out.println();
        System.out.println(">>> ServerNode started.");
        System.out.println("\n" +
                "  _   _           _        _ \n" +
                " | \\ | | ___   __| | ___  / |\n" +
                " |  \\| |/ _ \\ / _` |/ _ \\ | |\n" +
                " | |\\  | (_) | (_| |  __/ | |\n" +
                " |_| \\_|\\___/ \\__,_|\\___| |_|\n" +
                "                             \n");


    }
}
