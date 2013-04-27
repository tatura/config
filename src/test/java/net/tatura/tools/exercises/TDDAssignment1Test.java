package net.tatura.tools.exercises;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testklasse Pratikumsaufgabe 2 (TDD).
 * @author Benjamin Keeser
 */
public class TDDAssignment1Test {

    /**
     * Test-Methode fuer leere Eingaben.
     */
    @Test
    public void testIsBlank() {

        assertTrue("Null als Eingabe", TDDAssignment1.isBlank(null));
        assertTrue("Leerzeichen als Eingabe", TDDAssignment1.isBlank(" "));
        assertFalse("Buchstaben als Eingabe", TDDAssignment1.isBlank("abc"));
    }

    /**
     * Test-Methode zum Entfernen von Zeichen.
     */
    @Test
    public void testStrip() {

        assertEquals("Entfernen von vorhandenen Zeichen", "mein ", TDDAssignment1.strip("mein WE", "WE"));
        assertEquals("Entfernen von vorhandenen Zeichen", "-", TDDAssignment1.strip("Hallo-ollaH", "Halo"));

    }

    /**
     * Test-Methode zum Zusammenfuegen von Zeichen.
     */
    @Test
    public void testJoin() {

        String[] zahlen = {"eins", "zwei", null, "fünf"};

        assertEquals("Null als Eingabe", "einszweifünf", TDDAssignment1.join(zahlen));
        assertEquals("2 x Null als Eingabe", "", TDDAssignment1.join(null, null));
        assertEquals("String Array als Eingabe",  "einszweidrei", TDDAssignment1.join("eins", "zwei", "drei"));
    }

    /**
     * Test-Methode zur Passwort-Sicherheit.
     */
    @Test
    public void testIsSecure() {

        assertTrue("lowerCase", PasswordAnalyzer.hasLowerCase("dflb49P234nm∆øÜgklöOOOdf"));
        assertFalse("noLowerCase", PasswordAnalyzer.hasLowerCase("OOOGT"));
        assertTrue("upperCase", PasswordAnalyzer.hasUpperCase("dflb49234Pnm∆øÜgklöOOOdf"));
        assertFalse("noUpperCase", PasswordAnalyzer.hasUpperCase("dflb49234nm∆øügklödf"));
        assertTrue("specialCharacter", PasswordAnalyzer.hasSpecialCharacter("öOOOdf"));
        assertFalse("noSpecialCharacter", PasswordAnalyzer.hasSpecialCharacter("OOOdf"));
        assertTrue("differentCharacter", PasswordAnalyzer.checkDifferentCharacters("dflb49P234nm∆øÜgklöOOOdf"));
        assertFalse("noDifferentCharacter", PasswordAnalyzer.checkDifferentCharacters("abcdefghiiii"));
        assertTrue("hasDigit", PasswordAnalyzer.hasDigit("dflb∆5øÜgklöOOOdf"));
        assertFalse("hasNoDigit", PasswordAnalyzer.hasDigit("dflb∆øÜgklöOOOdf"));

        assertTrue("Sicheres Password", PasswordAnalyzer.checkSecurity("dflb49P234nm∆øÜgklöOOOdf"));
        assertFalse("unsicheres Password", PasswordAnalyzer.checkSecurity("abc"));
    }

    /**
     * Test-Methode zur Validitaet von ISBN 10.
     */
    @Test
    public void testIsValidIsbn10() {

        assertTrue("isbn10", TDDAssignment1.isValidIsbn10("3866801920"));
        assertTrue("isbn10-delimiter", TDDAssignment1.isValidIsbn10("3-86680-192-0"));
        assertTrue("isbn10-whitespace", TDDAssignment1.isValidIsbn10("386 6801920"));
        assertFalse("isbn10", TDDAssignment1.isValidIsbn10("38668019"));
    }

    /**
     * Test-Methode zur Validitaet von ISBN 13.
     */
    @Test
    public void testIsValidIsbn13() {

        assertTrue("isbn13",  TDDAssignment1.isValidIsbn13("9783866801929"));
        assertFalse("isbn13-false", TDDAssignment1.isValidIsbn13("ascgzhjop0dfg"));
        assertTrue("isbn13-delimiter", TDDAssignment1.isValidIsbn13("978-3-86680-192-9"));
        assertTrue("isbn13-whitespace", TDDAssignment1.isValidIsbn13("978 3 86680 192 9"));
        assertFalse("isbn13", TDDAssignment1.isValidIsbn13("38668019"));
    }
}

