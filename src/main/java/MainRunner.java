/**
 * .
 *
 * @author Michael Nitschinger
 */
public class MainRunner {

    public static void main(String... args) throws Exception {
        if ("old".equals(args[0])) {
            OldCouchbaseClient.main(args);
        } else {
            JavaClient.main(args);
        }
    }
}
