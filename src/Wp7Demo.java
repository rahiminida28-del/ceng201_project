public class Wp7Demo {


        public static void main(String[] args) {

            ScenarioGenerator generator = new ScenarioGenerator(20260725L);

            ExamGateEngine engine = new ExamGateEngine(850);


            // BURST 1
            Submission[] burst1 = generator.generateBurst1();

            for (int i = 0; i < burst1.length; i++) {
                engine.acceptUpload(burst1[i]);
            }

            // FIRST CHECKPOINT
            engine.printCheckpoint("AFTER BURST 1");

            // Queue -> Dispatcher -> Registry -> Timeline
            engine.moveIntakeToDispatcher();
            engine.processDispatcher();

            System.out.println("\nAfter processing Burst 1:");
            System.out.println("Registry Size: " + engine.getRegistrySize());
            System.out.println("Timeline Records: " + engine.getTimelineCount());


            // BURST 2
            Submission[] burst2 = generator.generateBurst2();

            for (int i = 0; i < burst2.length; i++) {
                engine.acceptUpload(burst2[i]);
            }

            engine.printCheckpoint("AFTER BURST 2");

            engine.moveIntakeToDispatcher();
            engine.processDispatcher();

            System.out.println("\nAfter processing Burst 2:");
            System.out.println("Registry Size: " + engine.getRegistrySize());
            System.out.println("Re-uploads: " + engine.getReuploads());
            System.out.println("Timeline Records: " + engine.getTimelineCount());
            System.out.println("S-0001 Version: " + engine.getStudentVersion("S-0101"));

            // BURST 3
            Submission[] burst3 = generator.generateBurst3();

            int i = 0;



            while (i < burst3.length && burst3[i].getTimestampMs() <= Submission.DEADLINE_MS) {
                engine.acceptUpload(burst3[i]);

                i++;
            }

            engine.printCheckpoint("AT 23:59");

            while (i < burst3.length) {

                engine.acceptUpload(burst3[i]);

                i++;
            }

            engine.printCheckpoint("AFTER BURST 3");

            engine.moveIntakeToDispatcher();
            engine.processDispatcher();


            System.out.println("\nAfter processing Burst 3:");
            System.out.println("Registry Size: " + engine.getRegistrySize());
            System.out.println("Re-uploads: " + engine.getReuploads());
            System.out.println("Timeline Records: " + engine.getTimelineCount());

            // ROLLBACKS

            engine.rollbackStudent("S-0101");
            engine.rollbackStudent("S-0102");
            engine.rollbackStudent("S-0103");

            System.out.println("\n---ROLLBACK CONTROL---");

            System.out.println("S-0101 Version: " + engine.getStudentVersion("S-0101"));
            System.out.println("S-0102 Version: " + engine.getStudentVersion("S-0102"));
            System.out.println("S-0103 Version: " + engine.getStudentVersion("S-0103"));
            engine.printCheckpoint("FINAL");

            engine.printFinalReport();
        }
    }

