public class Wp4Demo {
        public static void main(String[] args) {

            SubmissionRegistry registry = new SubmissionRegistry();

            RollbackService rollbackService = new RollbackService(registry);

            //Version - 1

            Submission student = new Submission("S-0101" , "projectV1.pdf",2048,79_200_000L,1
                    ,false);

            registry.put(student);

            System.out.println("---Wp4 Version Rolback ---");

            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");

            //Upload Version - 2

            rollbackService.saveVersion("S-0101");

            registry.updateVersion("S-0101","projectV2.pdf",2100,80_200_000L);

            System.out.println("\nAfter UPLOAD Version 2: " );
            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");


            //Upload Version - 3

            rollbackService.saveVersion("S-0101");

            registry.updateVersion("S-0101","projectV3.pdf",22500,81_450_000L);

            System.out.println("\nAfter Upload Version 3: " );
            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");

            //First Rollback
            System.out.println("\n--- First Rolback" );

            rollbackService.rollback("S-0101");

            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");

            //Second Rollback
            System.out.println("\n--- Second Rolback" );

            rollbackService.rollback("S-0101");

            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");

            //Third Rollback
            System.out.println("\n--- Third Rolback" );

            rollbackService.rollback("S-0101");

            System.out.println("\nActive Version: " + registry.lookup("S-0101"));
            rollbackService.printStack("S-0101");

        }
    }

