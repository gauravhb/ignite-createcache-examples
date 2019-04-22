package com.igniteexamples.compute;

import com.igniteexamples.model.Employee;
import org.apache.ignite.*;
import org.apache.ignite.cache.CachePeekMode;

import javax.cache.Cache;
import java.util.Collection;
import java.util.Iterator;

public class SalaryCountComputeClient {
    /**
     * Cache name.
     */
    private static final String EMP_CACHE_NAME = "Employee_Cache";

    public static void main(String[] args) {
        System.setProperty(IgniteSystemProperties.IGNITE_UPDATE_NOTIFIER, String.valueOf(false));

        System.out.println(">>> Please make sure server node is started using class  " +
                "com.igniteexamples.createcache.StartServerNode before starting this..");
        //Setting this Instance as Client node and then starting it..
        Ignition.setClientMode(true);
        Ignite ignite = Ignition.start("example-ignite-config.xml");

        System.out.println(">>> WordCountComputeClient started.");
        System.out.println(">>> Number of nodes in cluster : " + ignite.cluster().nodes().size());
        System.out.println("\n" +
                "   ____ _ _            _     _   _           _      \n" +
                "  / ___| (_) ___ _ __ | |_  | \\ | | ___   __| | ___ \n" +
                " | |   | | |/ _ \\ '_ \\| __| |  \\| |/ _ \\ / _` |/ _ \\\n" +
                " | |___| | |  __/ | | | |_  | |\\  | (_) | (_| |  __/\n" +
                "  \\____|_|_|\\___|_| |_|\\__| |_| \\_|\\___/ \\__,_|\\___|\n" +
                "                                                    \n");


        //Below line will create Employee Cache with default configuration
        IgniteCache<Long, Employee> employeeCache = ignite.getOrCreateCache(EMP_CACHE_NAME);
        System.out.println(">>> Inserting record in the cache..");
        for (int i = 0; i < 10; i++) {

            Employee employee = new Employee();
            employee.setName("Emp" + i);
            employee.setProjectId(123);
            employee.setAddress("Planet Earth");
            employee.setSalary(100000 * i);

            employeeCache.put((long) (100 * i), employee);
        }

        System.out.println(">>> Number of records in the cache : " + employeeCache.size());

        IgniteCompute compute = ignite.compute(ignite.cluster().forRemotes());

        Collection<Double> res = compute.broadcast(() -> {
            int size = Ignition.ignite().cache(EMP_CACHE_NAME).localSize(CachePeekMode.PRIMARY);
            System.out.println(">>> Number of records on this node :" + size);
            IgniteCache<Long, Employee> cache = Ignition.ignite().cache(EMP_CACHE_NAME);
            Iterator<Cache.Entry<Long, Employee>> entries = cache.localEntries(CachePeekMode.PRIMARY).iterator();
            double totalSalary = 0;
            while (entries.hasNext()) {
                totalSalary = totalSalary + entries.next().getValue().getSalary();
            }

            System.out.println(">>> Addition of Salaries for employees on this node :" + totalSalary);
            return totalSalary;
        });

        double total = res.stream().mapToDouble(Double::doubleValue).sum();
        System.out.println(">>> Total salary of all employees : " + total);
    }

}

