public class Wp2Demo {
        public static void main(String[] args){

            ScenarioGenerator generator = new ScenarioGenerator(20260725L);

            Submission student1 = generator.nextUpload(100);  //s-0101
            Submission student2 = generator.nextUpload(108);  //s-0109
            Submission student3 = generator.nextUpload(101);  //s-0102
            Submission student4 = generator.nextUpload(102);  //s-0103
            Submission student5 = generator.nextUpload(103);  //s-0104
            Submission student6 = generator.nextUpload(104);  //s-0105
            Submission student7 = generator.nextUpload(105);  //s-0106
            Submission student8 = generator.nextUpload(106);  //s-0107


            CircularUploadQueue queue = new CircularUploadQueue(5);

            System.out.println("---Wp2 Queue Demo---");

            //EnQ
            queue.enqueue(student1);
            queue.enqueue(student2);
            queue.enqueue(student3);
            queue.enqueue(student4);
            queue.enqueue(student5);

            queue.printState();

            //DeQ
            System.out.println("\nRemoved: " + queue.dequeue().getStudentId());
            System.out.println("Removed: " + queue.dequeue().getStudentId());
            queue.printState();

            //wrap around test

            System.out.println("\n---Wrap - Around TEST---");

            System.out.println("S-0106 accepted enqueue: " + queue.enqueue(student6));
            System.out.println("S-0107 accepted enqueue: " + queue.enqueue(student7));

            queue.printState();


            System.out.println("\nBuffer Check: ");

            boolean accept= queue.enqueue(student8);

            System.out.println("S-0008 accepted: " + accept);

            queue.printState();


            //DeQ order

            System.out.println("\n---DeQueue order---");

            while (queue.size() > 0){
                Submission removedId = queue.dequeue();

                System.out.println(removedId.getStudentId());
            }


            System.out.println("\n--navi vs circular Q ---");


            NaiveUploadQueue naive = new NaiveUploadQueue(5);
            CircularUploadQueue circularQueue = new CircularUploadQueue(5);

            Submission[] test = {student1, student2, student3, student4, student5};

            for (int i = 0; i < test.length; i++) {
                naive.enqueue(test[i]);
                circularQueue.enqueue(test[i]);
            }

            System.out.print("\nNaive Order: ");

            while (naive.size() > 0) {
                System.out.print(naive.dequeue().getStudentId() + " ");
            }

            System.out.println();

            System.out.print("\nCircular Order: ");

            while (circularQueue.size() > 0) {
                System.out.print(circularQueue.dequeue().getStudentId() + " ");
            }

            System.out.println();


            System.out.println("\n---10,000 UPLOAD BENCHMARK---");

            int n = 10_000;

            Submission[] uploads = new Submission[n];

            ScenarioGenerator benchmarkGen = new ScenarioGenerator(20260725L);

            for (int i = 0; i < n; i++) {

                uploads[i] = benchmarkGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);

            }

            //NAIVE BENCHMARK

            NaiveUploadQueue naiveBench = new NaiveUploadQueue(n);

            for (int i = 0; i < n; i++) {
                naiveBench.enqueue(uploads[i]);
            }

            long naiveStart = System.nanoTime();

            while (naiveBench.size() > 0) {
                naiveBench.dequeue();
            }

            long naiveEnd = System.nanoTime();

            long naiveTime = naiveEnd - naiveStart;




            CircularUploadQueue circularBench = new CircularUploadQueue(n);

            for (int i = 0; i < n; i++) {
                circularBench.enqueue(uploads[i]);
            }

            long circularStart = System.nanoTime();

            while (circularBench.size() > 0) {
                circularBench.dequeue();
            }

            long circularEnd = System.nanoTime();

            long circularTime = circularEnd - circularStart;

            System.out.println("\nNaive Queue: " + naiveTime / 1_000_000.0 + " ms");

            System.out.println("\nCircular Queue: " + circularTime / 1_000_000.0 + " ms");
        }


    }


