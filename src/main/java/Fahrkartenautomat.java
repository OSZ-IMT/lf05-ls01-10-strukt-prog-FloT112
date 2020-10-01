import java.util.Scanner;

/**
 * Beispielcode für den Fahrkartenautomaten
 *
 * @author Hafezi
 */
class Fahrkartenautomat {



    public static void main(String[] args) {
        Scanner tastatur = new Scanner(System.in);
        float zuZahlenderBetrag = 0;
        float eingezahlterGesamtbetrag = 0;
        float eingeworfeneMuenze = 0;
        float rueckgabebetrag = 0;
        float eingegebenerBetrag;
        int anzahlTickets = 0;
        float gesamterBetrag;
        float rueckgeld;
        // zuZahlenderbetrag, eingezahlterGesamtbetrag, eingeworfeneMuenze, rueckgabebetrag, eingegebenerBetrag und anzahlTickets sind die Variablen (d auch) mit Dateityp: float

        zuZahlenderBetrag = fahrkartenbestellungerfassen();
        rueckgeld = fahrkartenbezahlen(zuZahlenderBetrag);
        rueckgeldausgeben(rueckgeld);
        fahrkartenausgeben();

        System.out.println("\nVergessen Sie nicht, den/die Fahrschein/e\n" +
                "vor Fahrtantritt entwerten zu lassen!\n" +
                "Wir wünschen Ihnen eine gute Fahrt.");


    }



    public static float fahrkartenbestellungerfassen(){
        float gesamterBetrag = 0;
        float zuZahlenderBetrag;
        float anzahlTickets = 0;

        Scanner tastatur = new Scanner(System.in);
        System.out.print("Zu zahlender Betrag (Euro): ");
        zuZahlenderBetrag = tastatur.nextFloat();// Wert für "zuZahlenderBetrag" wird festgelegt durch die Eingabe
        if (zuZahlenderBetrag <= 0){
            System.out.println("Der von Ihnen eingegebene Preis ist nicht gültig, der Preis wird nun automatisch auf 1 gesetzt.");
            zuZahlenderBetrag = 1;
        }
        System.out.print("Anzahl der Tickets: ");
        anzahlTickets = tastatur.nextInt();// Wert für "anzahlTickets" wird festgelegt durch die Eingabe
        if (anzahlTickets > 10){
            System.out.println("Fehler: sie haben keine Zahl von 1-10 eingegeben, geben Sie bitte eine andere Anzahl an");
             return fahrkartenbestellungerfassen();
        }
        if (anzahlTickets < 1){
            System.out.println("Fehler: sie haben keine Zahl von 1-10 eingegeben, geben Sie bitte eine andere Anzahl an");
            return fahrkartenbestellungerfassen();
        }

        gesamterBetrag = anzahlTickets * zuZahlenderBetrag;
        return gesamterBetrag;
    }

    public static float fahrkartenbezahlen(float zuZahlenderBetrag){
        float gesamterBetrag;
        float eingezahlterGesamtbetrag = 0;
        float eingeworfeneMuenze;
        float eingegebenerBetrag;
        float rueckgeld;
        Scanner tastatur = new Scanner(System.in);
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) { // eine Schleife in der festgelegt ist, so lange der "eingezahlterGesamtbetrag" kleiner als das Produkt von "zuZahlenderBetrag" und "anzahlTickets" beginnt die Schleife immer wieder von neu
            float d = zuZahlenderBetrag - eingezahlterGesamtbetrag; // d ist auch eine Variable mit Dateityp float // von "zuZahlenderBetrag*anzahlTickets" wir der mit der Tastatur eingetippte Betrag "eingezahlterGesamtbetrag" abgezogen und das Restgeld(wenn vorhanden = eingezahlter Betrag > gesamtBetrag) wird ausgegeben und das Ticket wird gedruckt, weil die Bedingung erfüllt ist
            System.out.printf( "%.2f\n" ,     d);
            System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
            eingeworfeneMuenze = tastatur.nextFloat(); // Wert für das eingeworfene Geld kann festgelegt werden
            eingezahlterGesamtbetrag += eingeworfeneMuenze; // Der Gesamtbetrag mit 0,0 der am Anfang festgelegt wurde, wird jetzt mit dem Geld/Wert addiert, welcher eingetragen wurde
        }
        rueckgeld = eingezahlterGesamtbetrag-zuZahlenderBetrag;
        return rueckgeld;
    }



    public static void rueckgeldausgeben (float rueckgeld){
        float zuZahlenderBetrag;
        if (rueckgeld > 0.0) {
            System.out.println("Der Rückgabebetrag in Höhe von " + rueckgeld + " EURO"); // hier wird der Rückgabebetrag angezeigt, welcher davor ausgerechnet wurde
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgeld >= 2.00)
            {
                System.out.println("2 Euro");
                rueckgeld -= 2.00;
            }
            while (rueckgeld >= 1.00)
            {
                System.out.println("1 Euro");
                rueckgeld -= 1.00;
            }
            while (rueckgeld >= 0.50)
            {
                System.out.println("50 Cent");
                rueckgeld -= 0.50;
            }
            while (rueckgeld >= 0.20)
            {
                System.out.println("20 Cent");
                rueckgeld -= 0.20;
            }
            while (rueckgeld >= 0.10)
            {
                System.out.println("10 Cent");
                rueckgeld -= 0.10;
            }
            while (rueckgeld >= 0.05)
            {
                System.out.println("5 Cent");
                rueckgeld -= 0.05;
            }
        }

    }

    public static void fahrkartenausgeben(){
        System.out.println("\nFahrschein");
        for (int i = 0; i < 10; i++) {
            System.out.print("=");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");
    }
}