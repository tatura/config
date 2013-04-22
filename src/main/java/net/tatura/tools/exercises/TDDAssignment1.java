package net.tatura.tools.exercises;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;
import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit;

/**
 * Utility-Klasse zur Pruefung und Veraenderung von Text.
 * @author Benjamin Keeser
 */
public final class TDDAssignment1 {


    private TDDAssignment1() {
        // prevents instantiation
    }

    /**
     * Methode zum Testen auf Leerzeichen.
     * @param input Text
     * @return Kein Text vorhanden
     */
    public static boolean isBlank(final String input) {
        return StringUtils.isBlank(input);
    }

    /**
     * Methode zum Entfernen von Zeichen.
     * @param input Text
     * @param stripChars Zu entfernende Zeichen
     * @return Satz mit entfernten Zeichen
     */
    public static String strip(final String input, final String stripChars) {
        return StringUtils.strip(input, stripChars);
    }

    /**
     * Methode zum Zusammenfpgen von Textelementen.
     * @param elements Textelemente
     * @return Zusammengefuegte Textelemente
     */
    public static String join(final String... elements) {
        return StringUtils.join(elements);
    }

    /**
     * Methode zur Pruefung der Passwort Sicherheit.
     * @param password Passwort
     * @return Sicheres Passwort
     */
    public static boolean isSecure(final String password) {
        return PasswordAnalyzer.checkSecurity(password);
    }

    /**
     * Methode zur Pruefung auf ISBN-10 Validitaet.
     * @param isbn ISBN-Nummer
     * @return Valide NUmmer
     */
    public static boolean isValidIsbn10(final String isbn) {

        String code = isbn.replace("-", "").replace(" ", StringUtils.EMPTY);

        ISBN10CheckDigit validator = new ISBN10CheckDigit();
        return validator.isValid(code);
    }

    /**
     * Methode zur Pruefung auf ISBN-13 Validitaet.
     * @param isbn ISBN-Nummer
     * @return Valide NUmmer
     */
    public static boolean isValidIsbn13(final String isbn) {

        String code = isbn.replace("-", "");
        code = code.replace(" ", "");

        ISBNCheckDigit validator = new ISBNCheckDigit();
        return validator.isValid(code);
    }

}

