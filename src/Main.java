//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public static void main(String[] args) {

    ScenarioGenerator gen = new ScenarioGenerator(20260725L);

    for (int i = 0; i < 5; i++) {
        System.out.println(gen.nextUpload(i));
    }
}