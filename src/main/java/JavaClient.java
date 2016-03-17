import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class JavaClient {

    public static void main(String... args) {
        CouchbaseEnvironment env = DefaultCouchbaseEnvironment
            .builder()
            .callbacksOnIoPool(true)
            .build();
        Cluster cluster = CouchbaseCluster.create(env);
        Bucket bucket = cluster.openBucket();

        int rounds = 100000;

        long start = System.nanoTime();
        for (int i = 0; i < rounds; i++) {
            String id = nextId();

            bucket.insert(RawJsonDocument.create(id, "{}"));
            bucket.get(id);
        }
        long end = System.nanoTime();
        System.out.println("Total Runtime: " + TimeUnit.NANOSECONDS.toSeconds(end - start));

        cluster.disconnect();
        env.shutdownAsync().toBlocking().single();
    }

    private static String nextId() {
        return UUID.randomUUID().toString();
    }

}
