public class Wp3Demo {
    public static void main(String[] args){

            Submission student1 = new Submission("S-0101","projectFirst.pdf",
                    2048,79_505_000L,1,false);

            Submission student2 = new Submission("S-0109","projectSecond.pdf",
                    3120,79_506_000L,1,true);

            Submission student3 = new Submission("S-0102","projectThird.pdf",
                    1024,79_502_000L,1,false);

            Submission student4 = new Submission("S-0103","projectFourth.pdf",
                    4096,79_507_000L,1,false);

            Submission student5 = new Submission("S-0104","projectFifth.pdf",
                    2560,79_503_000L,1,true);

            Submission student6 = new Submission("S-0105","projectSixth.pdf",
                    1850,79_504_000L,1,false);

            Submission student7 = new Submission("S-0106","projectSeventh.pdf",
                    3400,79_501_000L,1,false);

            Submission student8 = new Submission("S-0107","projectEight.pdf",
                    4500,79_508_000L,1,false);


            NaiveDispatcher naive= new NaiveDispatcher(8);

            naive.submit(student1);
            naive.submit(student2);
            naive.submit(student3);
            naive.submit(student4);
            naive.submit(student5);
            naive.submit(student6);
            naive.submit(student7);
            naive.submit(student8);


            System.out.println("---Wp3 Naive Dispatcher Execution order");

            while (naive.size()>0){

                Submission next = naive.next();

                System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

            }

            System.out.println("\n---WP-3 Heap Dispatcher");

            HeapDispatcher heap = new HeapDispatcher(8);
             //heap test
            heap.submit(student1);
            heap.submit(student2);
            heap.submit(student3);
            heap.submit(student4);
            heap.submit(student5);
            heap.submit(student6);
            heap.submit(student7);
            heap.submit(student8);

            while (heap.size()>0){

                Submission next = heap.next();

                System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

            }


            System.out.println("\n---WP-3 Load Burst");

            Submission[] burst = {student1,student2,student3,student4,student5,student6,student7,student8};

            HeapDispatcher heapBurst = new HeapDispatcher(8);

            heapBurst.loadBurst(burst);

            while (heapBurst.size()>0){

                Submission next = heapBurst.next();

                System.out.println(next.getStudentId() + " |" + " Accomodation Flag: " + next.hasAccommodation() + " |" + " Timestamp MS: " + next.getTimestampMs());

            }

            //WARM-UP

            int warmN = 1000;

            Submission[] warmUploads = new Submission[warmN];

            ScenarioGenerator warmGen =
                    new ScenarioGenerator(20260725L);

            for (int i = 0; i < warmN; i++) {
                warmUploads[i] = warmGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
            }

            NaiveDispatcher warmNaive = new NaiveDispatcher(warmN);

            HeapDispatcher warmHeap = new HeapDispatcher(warmN);

            for (int i = 0; i < warmN; i++) {
                warmNaive.submit(warmUploads[i]);
                warmHeap.submit(warmUploads[i]);
            }

            while (warmNaive.size() > 0) {
                warmNaive.next();
            }

            while (warmHeap.size() > 0) {
                warmHeap.next();
            }

            System.out.println("\n---1,000 DISPATCHER BENCHMARK---");

            int n = 1_000;

            Submission[] uploads = new Submission[n];

            ScenarioGenerator benchmarkGen =
                    new ScenarioGenerator(20260725L);

            for (int i = 0; i < n; i++) {

                uploads[i] =
                        benchmarkGen.nextUpload(
                                i % ScenarioGenerator.STUDENT_COUNT);
            }


            //NAIVE BENCHMARK

            NaiveDispatcher naiveBenchmark = new NaiveDispatcher(n);

            long naiveStart = System.nanoTime();

            for (int i = 0; i < n; i++) {
                naiveBenchmark.submit(uploads[i]);
            }

            while (naiveBenchmark.size() > 0) {
                naiveBenchmark.next();
            }

            long naiveEnd = System.nanoTime();

            long naiveTime = naiveEnd - naiveStart;


            //HEAP BENCHMARK

            HeapDispatcher heapBenchmark = new HeapDispatcher(n);

            long heapStart = System.nanoTime();

            for (int i = 0; i < n; i++) {
                heapBenchmark.submit(uploads[i]);
            }

            while (heapBenchmark.size() > 0) {
                heapBenchmark.next();
            }

            long heapEnd = System.nanoTime();

            long heapTime = heapEnd - heapStart;


            System.out.println("\nNaive Dispatcher: " + naiveTime / 1_000_000.0 + " ms");

            System.out.println("\nHeap Dispatcher: " + heapTime / 1_000_000.0 + " ms");


            System.out.println("\n---10,000 DISPATCHER BENCHMARK---");

            n = 10_000;

            uploads = new Submission[n];

            benchmarkGen = new ScenarioGenerator(20260725L);

            for (int i = 0; i < n; i++) {

                uploads[i] = benchmarkGen.nextUpload(i % ScenarioGenerator.STUDENT_COUNT);
            }


            //NAIVE BENCHMARK

            naiveBenchmark = new NaiveDispatcher(n);

            naiveStart = System.nanoTime();

            for (int i = 0; i < n; i++) {
                naiveBenchmark.submit(uploads[i]);
            }

            while (naiveBenchmark.size() > 0) {
                naiveBenchmark.next();
            }

            naiveEnd = System.nanoTime();

            naiveTime = naiveEnd - naiveStart;

            heapBenchmark = new HeapDispatcher(n);

            heapStart = System.nanoTime();

            for (int i = 0; i < n; i++) {
                heapBenchmark.submit(uploads[i]);
            }

            while (heapBenchmark.size() > 0) {
                heapBenchmark.next();
            }

            heapEnd = System.nanoTime();

            heapTime = heapEnd - heapStart;


            System.out.println("\nNaive Dispatcher: " + naiveTime / 1_000_000.0 + " ms");

            System.out.println("\nHeap Dispatcher: " + heapTime / 1_000_000.0 + " ms");
        }


    }


