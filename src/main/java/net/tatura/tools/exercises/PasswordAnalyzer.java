package net.tatura.tools.exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Password Strength Analyzer.
 * @author Benjamin Keeser
 */
public final class PasswordAnalyzer {

    private static final int DIFFERENT_CHARACTERS = 10;
    private static Logger log = LoggerFactory.getLogger(PasswordAnalyzer.class);

    private PasswordAnalyzer() {
    }

    /**
     * Methode zum Pruefen der Passwort Staerke.
     * @param text Passwort
     * @return Passort sicher
     */
    public static boolean checkSecurity(final String text)  {

        return hasLowerCase(text) && hasUpperCase(text) && hasDigit(text)
                && hasSpecialCharacter(text) && checkDifferentCharacters(text);
    }

    /**
     * Methode zum Pruefen von Strings auf Kleinschreibung.
     * @param text Zu pruefender Text
     * @return Kleinschreibung enthalten
     */
    public static boolean hasLowerCase(final String text) {

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLowerCase(text.charAt(i))) {
                return true;
            }
        }
        log.info("failed hasLowerCase");
        return false;
    }

    /**
     * Methode zum Pruefen von Strings auf Grossschreibung.
     * @param text Zu pruefender Text
     * @return Grossschreibung enthalten
     */
    public static boolean hasUpperCase(final String text) {

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                return true;
            }
        }
        log.info("failed hasUpperCase");
        return false;
    }

    /**
     * Methode zur Pruefung auf Zahlen.
     * @param text  Zu pruefender Text
     * @return Zahl enthalten
     */
    public static boolean hasDigit(final String text) {

//        NumberUtils.isNumber(text);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                return true;
            }
        }
        log.info("failed hasDigit");
        return false;
    }

    /**
     * Methode zur Pruefung auf Sonderzeichen.
     * @param text Zu pruefender Text
     * @return Sonderzeichen enthalten
     */
    public static boolean hasSpecialCharacter(final String text) {

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }

    /**
     * Methode zur Pruefung auf Vorhanden sein von unterschiedlichen Zeichen.
     * @param text Zu pruefender Text
     * @return Unterschiedliche Zeichen vorhanden
     */
    public static boolean checkDifferentCharacters(final String text) {

        int count = 0;

        for (int i = 0; i < text.length(); i++) {

            String ch = CharUtils.toString(text.charAt(i));

            if (StringUtils.countMatches(text, ch) < 2) {
               count++;
            }

            if (count == DIFFERENT_CHARACTERS) {
                return true;
            }
        }
        log.info("failed checkDifferentCharacters");
        return false;
    }
}

