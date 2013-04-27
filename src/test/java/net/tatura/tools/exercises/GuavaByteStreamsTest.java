package net.tatura.tools.exercises;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.OutputSupplier;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Benjamin Keeser
 */
public class GuavaByteStreamsTest {

    private static Logger log = LoggerFactory.getLogger(GuavaByteStreamsTest.class);

    @Test
    public void testCopy() {

        // Given
        File file = null;
        byte[] fileData = new byte[] { 12, 44, 16, 18, 20, 22, 24, 26, 28 };

        InputStream in = getInputStream(fileData);
        OutputSupplier<OutputStream> ous = outputSupplier();
        OutputSupplier<OutputStream> ousWithoutGeneric = outputSupplierWithoutGeneric();

        try {
            file = File.createTempFile("testFile", ".tmp");

            ByteStreams.copy(in, ous);
            Assert.assertArrayEquals("Wrong data", new byte[] { 16, 18, 20 }, ((ByteArrayOutputStream)ous.getOutput()).toByteArray());

            // When
//            when(in.read(fileData)).thenReturn(-1);
            long byteLength = ByteStreams.copy(in, ous);

            //Then
            assertEquals("Expected byte length: 8 ", fileData.length, byteLength);


            ByteStreams.copy(in, ousWithoutGeneric);
            Assert.assertArrayEquals("Wrong data", new byte[] { 16, 18, 20 }, ((ByteArrayOutputStream)ousWithoutGeneric.getOutput()).toByteArray());

            long byteLengthWitoutGeneric = ByteStreams.copy(in, ousWithoutGeneric);

            //Then - No Generics
            assertEquals("Expected byte length: 8 ", fileData.length, byteLengthWitoutGeneric);

        }
        catch (IOException exception) {
            log.error("error: " + exception.getLocalizedMessage());
        }
        finally {
            file.deleteOnExit();
        }
    }

    public OutputSupplier outputSupplierWithoutGeneric() {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        return new OutputSupplier() {
            public OutputStream getOutput() throws IOException {
                return out;
            }
        };
    }

    OutputSupplier<OutputStream> outputSupplier() {

        return new OutputSupplier<OutputStream>() {

            @Override
            public OutputStream getOutput() throws IOException {
                return getOutputStream();
            }
        };
    }

    public InputStream getInputStream(final byte[] data) {

        ByteArrayInputStream in = mock(ByteArrayInputStream.class);
        try {
            in.read(data);
        }
        catch (IOException exception) {
            log.error("error: " + exception);
        }
        return in;
    }

    public OutputStream getOutputStream() {
        ByteArrayOutputStream out = mock(ByteArrayOutputStream.class);
        return out;
    }
}

