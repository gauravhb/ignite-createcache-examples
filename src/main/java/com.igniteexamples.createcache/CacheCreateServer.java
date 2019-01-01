package com.igniteexamples.createcache;

import com.igniteexamples.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

public class CacheCreateServer {
    /** Cache name. */
    private static final String EMP_CACHE_NAME = "Employee_Cache";

    public static void main(String[] args) {
        try(Ignite ignite = Ignition.start("example-server.xml")) {
            System.out.println();
            System.out.println(">>> CacheCreateServer example started.");

            //Below line will create Employee Cache with default configuration
            IgniteCache<Long, Employee> employeeCache = ignite.getOrCreateCache(EMP_CACHE_NAME);
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
