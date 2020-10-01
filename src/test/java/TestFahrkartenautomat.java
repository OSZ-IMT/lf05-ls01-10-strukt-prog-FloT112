import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.Assert.*;

public class TestFahrkartenautomat {

    // backup System.out to restore it later
    private PrintStream originalOut;
    private ByteArrayOutputStream bos;

    // backup System.in to restore it later
    private InputStream sysInBackup;

    @Before
    public void InitPrintStream(){
        Locale.setDefault(Locale.GERMANY);
        //bind the system
        originalOut = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        sysInBackup = System.in; // backup System.in to restore it later
    }

    @After
    public void EndPrintStream(){
        // undo the binding in System
        System.setOut(originalOut);

        // reset System.in to its original
        System.setIn(sysInBackup);
    }

    /**
     * Helpermethod for inputs
     * @param args, string to check
     */
    private void SetTestData(String... args) {
        ByteArrayInputStream in = new ByteArrayInputStream(String.join(System.lineSeparator(), args).getBytes());
        System.setIn(in);
    }

    /**
     * Helpermethod to check outputs
     * @param check, string to check
     */
    private void CheckTestData(String check) {
        try {
            assertTrue(bos.toString().contains(check));
        } catch (Throwable t) {
            //print exact program
            assertEquals(check,bos.toString());
        }
    }

    /**
     * Helpermethod to check outputs
     * @param check, string to check
     */
    private void CheckTestDataNot(String check) {
        try {
            assertFalse(bos.toString().contains(check));
        } catch (Throwable t) {
            //print exact program
            assertEquals(check,bos.toString());
        }
    }

    @Test
    public void testFahrkartenbestellungErfassen1() {

        SetTestData("1","3");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(3,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassen2() {

        SetTestData("1","10");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(10,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassen3() {

        SetTestData("2,7","2");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(5.4,price,0.1);
    }

    @Test
    public void testFahrkartenBezahlen1() {

        SetTestData("2","2");

        // action
        double amount = Fahrkartenautomat.fahrkartenBezahlen(4);

        // assertion
        CheckTestData("Noch zu zahlen: 4,00 €");
        assertEquals(0,amount,0.1);
    }

    @Test
    public void testFahrkartenBezahlen2() {

        SetTestData("2");

        // action
        double amount = Fahrkartenautomat.fahrkartenBezahlen(1.5);

        // assertion
        CheckTestData("Noch zu zahlen: 1,50 €");
        assertEquals(0.5,amount,0.1);
    }

    @Test
    public void testFahrkartenBezahlen3() {

        SetTestData("0,05","0,05","0,05","0,05","0,05","0,05","0,05","0,05","0,05","0,05","0,05");

        // action
        double amount = Fahrkartenautomat.fahrkartenBezahlen(0.5);

        // assertion
        assertEquals(0.05,amount,0.01);
    }

    @Test
    public void testFahrkartenAusgeben() {

        // action
        Fahrkartenautomat.fahrkartenAusgeben();

        // assertion
        CheckTestData("Fahrschein wird ausgegeben");
    }

    @Test
    public void testRueckgeldAusgeben1() {

        // action
        Fahrkartenautomat.rueckgeldAusgeben(5);

        // assertion
        CheckTestData("Rückgabebetrag in Höhe von 5,00 €");
        CheckTestData("2 EURO");
        CheckTestData("1 EURO");
    }

    @Test
    public void testRueckgeldAusgeben2() {

        // action
        Fahrkartenautomat.rueckgeldAusgeben(3.35);

        // assertion
        CheckTestData("2 EURO");
        CheckTestData("1 EURO");
        CheckTestData("20 CENT");
        CheckTestData("10 CENT");
        CheckTestData("5 CENT");
    }

    @Test
    public void testRueckgeldAusgeben3() {

        // action
        Fahrkartenautomat.rueckgeldAusgeben(0.95);

        // assertion
        CheckTestData("ckgabebetrag in Höhe von 0,95 €");
        CheckTestDataNot("1 EURO");
        CheckTestData("50 CENT");
    }

    @Test
    public void testFahrkartenbestellungErfassenAnzahlIf0() {

        SetTestData("1","0");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(1,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassenAnzahl11() {

        SetTestData("1","11");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(1,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassenAnzahl555() {

        SetTestData("1","555");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(1,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassenPreis0() {

        SetTestData("0","1");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(1,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassenPreisNeg1() {

        SetTestData("-1","1");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(1,price,0.1);
    }

    @Test
    public void testFahrkartenbestellungErfassenPreis2() {

        SetTestData("2","1");

        // action
        double price = Fahrkartenautomat.fahrkartenbestellungErfassen();

        // assertion
        assertEquals(2,price,0.1);
    }

}
