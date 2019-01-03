package com.igniteexamples.createcache;

import com.igniteexamples.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;

public class CacheCustomConfigCreateServer {
    /** Cache name. */
    private static final String EMP_CACHE_NAME = "Employee_Cache";

    public static void main(String[] args) {
        try(Ignite ignite = Ignition.start("example-ignite-config.xml")) {
            System.out.println();
            System.out.println(">>> CacheCustomConfigCreateServer example started.");

            //Custom Cache configuration
            CacheConfiguration<Long, Employee> cfg = new CacheConfiguration<>();
            cfg.setCacheMode(CacheMode.PARTITIONED);
            cfg.setName(EMP_CACHE_NAME);
            cfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);
            cfg.setBackups(1);

            //Below line will create Employee Cache with custom configuration
            IgniteCache<Long, Employee> employeeCache = ignite.createCache(cfg);
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
