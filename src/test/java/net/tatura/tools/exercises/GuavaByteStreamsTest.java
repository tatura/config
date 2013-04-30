package net.tatura.tools.exercises;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import com.google.common.io.OutputSupplier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Klasse zum Testen von Guava Kopier-Funktionalität.
 * @author Benjamin Keeser
 */
public class GuavaByteStreamsTest {

    private static Logger log = LoggerFactory.getLogger(GuavaByteStreamsTest.class);
    private boolean threw = true;

    /**
     * Methode zum Testen der Kopierfunktion.
     */
    @Test
    public void testCopy() {

        // Given
        byte[] fileData = new byte[] {12, 14, 16, 18, 20, 22, 24, 26, 28};

        InputStream in = getInputStream(fileData);
        OutputSupplier<OutputStream> ous = outputSupplier();
        OutputSupplier ousWithoutGenerics = outputSupplierWithoutGenerics();

        try {

            // When
            int byteLength = (int)ByteStreams.copy(in, ous);
            int byteLengthWitoutGeneric = (int)ByteStreams.copy(in, ousWithoutGenerics);

            //Then
            assertEquals("Expected byte length", fileData.length, byteLength);
            assertNotEquals("Expected false byte length", fileData.length, byteLengthWitoutGeneric);

            threw = false;
        }
        catch (IOException exception) {
            log.error("error: " + exception.getLocalizedMessage());
        }

        finally {

//            Closeables mock = mock(Closeables.class);
//            doThrow(new IOException()).when(...);

            try {
                Closeables.close(in, threw);
            }
            catch (IOException exception) {
                log.error("finally error: " + exception.getLocalizedMessage());
            }
        }

    }

    /**
     * Methode zum Erzeugen eines OutputSupplier ohne Generics (Guava).
     * @return OutpuSupplier
     */
    public OutputSupplier outputSupplierWithoutGenerics() {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        return new OutputSupplier() {
            @Override
            public OutputStream getOutput() throws IOException {
                return out;
            }
        };
    }

    /**
     * Methode zum Erzeugen eines OutputSupplier (Guava).
     * @return OutpuSupplier
     */
    OutputSupplier<OutputStream> outputSupplier() {

        return new OutputSupplier<OutputStream>() {

            @Override
            public OutputStream getOutput() throws IOException {
                return getOutputStream();
            }
        };
    }

    /**
     * Methode zur Generierung eines Input-Streams (Guave).
     * @param data bytedata
     * @return IputStream stream
     */
    public InputStream getInputStream(final byte[] data) {

        ByteArrayInputStream in = new ByteArrayInputStream(data);
        return in;

//        ByteArrayInputStream in = mock(ByteArrayInputStream.class);
    }

    /**
     * Methode zum Erzeugen eines Outputstreams.
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        ByteArrayOutputStream out = mock(ByteArrayOutputStream.class);
        return out;
    }
}

