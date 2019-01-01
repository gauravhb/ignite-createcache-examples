package com.igniteexamples.createcache;

import com.igniteexamples.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class CacheCreateConfigFile {
    /** Cache name. */
    private static final String EMP_CACHE_NAME = "Employee_Cache";

    public static void main(String[] args) {
        try(Ignite ignite = Ignition.start("example-cache-server.xml")) {
            System.out.println();
            System.out.println(">>> CacheCreateConfigFile example started.");

            //Below line will load Employee Cache created during Ignite instance startup
            IgniteCache<Long, Employee> employeeCache = ignite.cache(EMP_CACHE_NAME);
            System.out.println(">>> Cache created with name : " + ignite.cache(EMP_CACHE_NAME).getName());

            System.out.println(">>> Inserting record in the cache..");
            Employee employee = new Employee();
            employee.setName("Simbaa");
            employee.setProjectId(123);
            employee.setAddress("Planet Earth");
            employee.setSalary(100000);

            employeeCache.put(1001L, employee);

            System.out.println(">>> Number of records in the cache : " + employeeCache.size());

        }

    }
}
