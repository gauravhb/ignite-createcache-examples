package com.igniteexamples.createcache;

import com.igniteexamples.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

/**
 *This class will start client node, connect to existing cluster, create cache and put record in it.
 * Make sure  server node is already started using class  com.igniteexamples.createcache.StartServerNode
 * before executing this
 * */
public class CacheCreateClient {
    /** Cache name. */
    private static final String EMP_CACHE_NAME = "Employee_Cache";

    public static void main(String[] args) {
        System.out.println(">>> Please make sure server node is started using class  " +
                "com.igniteexamples.createcache.StartServerNode before starting this..");
        //Setting this Instance as Client node and then starting it..
        Ignition.setClientMode(true);
        try(Ignite ignite = Ignition.start("example-ignite-config.xml")) {
            System.out.println();
            System.out.println(">>> CacheCreateClient example started.");
            System.out.println(">>> Is Client node : " + Ignition.isClientMode());
            System.out.println(">>> Number of nodes in cluster : " +  ignite.cluster().nodes().size());


            //Below line will create Employee Cache with default configuration
            IgniteCache<Long, Employee> employeeCache = ignite.createCache(EMP_CACHE_NAME);
            System.out.println(">>> Cache created with name : " + ignite.cache(EMP_CACHE_NAME).getName());


            System.out.println(">>> Inserting record in the cache..");
            Employee employee = new Employee();
            employee.setName("Simbaa");
            employee.setProjectId(123);
            employee.setAddress("Planet Earth");
            employee.setSalary(100000);

            employeeCache.put(1001L, employee);


            System.out.println(">>> Number of records in the cache : " + employeeCache.size());

            //Number of records on this client node should be zero, as Caches are always deployed on Server Node.
            System.out.println(">>> Number of records on the local client node : " + employeeCache.localSize());

            //Stop both Server and client node in this example.
            ignite.cluster().stopNodes();

        }

    }
}
