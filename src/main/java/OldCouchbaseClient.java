import com.couchbase.client.CouchbaseClient;
import java.net.URI;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author Michael Nitschinger
 */
public class OldCouchbaseClient {

    public static void main(String... args) throws Exception {
        CouchbaseClient client = new CouchbaseClient(
            Arrays.asList(URI.create("http://"+args[1]+":8091/pools")
        ), "default", "");

        int rounds = 100000;

        long start = System.nanoTime();
        for (int i = 0; i < rounds; i++) {
            String id = nextId();

            client.add(id, "{}").get();
            client.get(id);
        }
        long end = System.nanoTime();
        System.out.println("Total Runtime: " + TimeUnit.NANOSECONDS.toSeconds(end - start));

        client.shutdown();
    }

    private static String nextId() {
        return UUID.randomUUID().toString();
    }
}
