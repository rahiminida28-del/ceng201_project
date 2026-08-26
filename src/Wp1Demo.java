import java.util.Random;
public class Wp1Demo {

    public static void main(String[] args) {

        SubmissionRegistry registry = new SubmissionRegistry();

        System.out.println("--- Wp1 Submission Demo---");

        // Forced collision before resize
        System.out.println("\n---  wp1  Collision Detection Demo ---");
        System.out.println("S-0101 bucket: " + registry.bucketIndex("S-0101"));
        System.out.println("S-0109 bucket: " + registry.bucketIndex("S-0109"));

        Submission s1 = new Submission(
                "S-0101", "project1.pdf", 2048,
                79_500_000L, 1, false);

        Submission s2 = new Submission(
                "S-0109", "project9.pdf", 3120,
                79_500_500L, 1, false);

        Submission s3 = new Submission(
                "S-0102", "project2.pdf", 1024,
                79_501_003L, 1, false);

        Submission s4 = new Submission(
                "S-0103", "project3.pdf", 4096,
                79_502_000L, 1, true);

        Submission s5 = new Submission(
                "S-0104", "project4.pdf", 2560,
                79_503_000L, 1, false);

        Submission s6 = new Submission(
                "S-0105", "project5.pdf", 1850,
                79_504_000L, 1, false);

        Submission s7 = new Submission(
                "S-0106", "project6.pdf", 3400,
                79_505_000L, 1, false);

        Submission s8 = new Submission(
                "S-0107", "project7.pdf", 4500,
                79_506_000L, 1, false);

        registry.put(s1);
        registry.put(s2);
        registry.put(s3);
        registry.put(s4);
        registry.put(s5);
        registry.put(s6);
        registry.put(s7);
        registry.put(s8);

        System.out.println("\n--- After 8 Inserts ---");
        System.out.println("Registry size: " + registry.size());
        System.out.println("Capacity: " + registry.capacity());

        System.out.println("\n--- Lookup All Students ---");
        System.out.println(registry.lookup("S-0101"));
        System.out.println(registry.lookup("S-0109"));
        System.out.println(registry.lookup("S-0102"));
        System.out.println(registry.lookup("S-0103"));
        System.out.println(registry.lookup("S-0104"));
        System.out.println(registry.lookup("S-0105"));
        System.out.println(registry.lookup("S-0106"));
        System.out.println(registry.lookup("S-0107"));

        System.out.println("\n--- update version ---");

        registry.updateVersion(
                "S-0001",
                "project1_final.pdf",
                1300,
                81_000_000L
        );

        registry.updateVersion(
                "S-0101",
                "project1_really_final.pdf",
                2100,
                80_000_000L
        );

        System.out.println(registry.lookup("S-0101"));

        System.out.println("\n--- unknown student ---");
        System.out.println(registry.lookup("S-8888"));

        System.out.println("\n--- 100,000 Seeded Lookups ---");

        String[] ids = {
                "S-0101", "S-0109", "S-0102", "S-0103",
                "S-0104", "S-0105", "S-0106", "S-0107"
        };

        Random rng = new Random(20260725L);

        long start = System.nanoTime();

        for (int i = 0; i < 100_000; i++) {
            String id = ids[rng.nextInt(ids.length)];
            registry.lookup(id);
        }

        long end = System.nanoTime();

        System.out.println("Execution : compeleted (100,000 queries)");
        System.out.println(" total Execution time: " + (end - start) + " ns");
    }
}


