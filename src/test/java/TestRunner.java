package tests;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("=== Memulai eksekusi semua API tests ===");

        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();

        List<String> suites = new ArrayList<>();
        suites.add("src/test/resources/testng.xml");

        testng.setTestSuites(suites);
        testng.addListener(tla);
        testng.run();

        System.out.println("=== Eksekusi selesai ===");
    }
}
