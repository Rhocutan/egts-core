package ru.egts.core.parser;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.egts.core.bean.GlonassData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class GlonassDataParserTest {

    @Test
    public void parse() throws IOException {
        GlonassDataParser parser = new GlonassDataParser();
        ClassLoader classLoader = getClass().getClassLoader();
        try (FileInputStream fis = new FileInputStream(new File(classLoader.getResource("33.bin").getFile()));
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int l;
            while ((l = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, l);
            }
            byte[] data = bos.toByteArray();

            GlonassData glonassData = parser.parse(0, data);

            assertThat(glonassData.getHeaderInfo(), Matchers.notNullValue());
        }

    }

    @Test
    public void parse2() throws IOException {
        GlonassDataParser parser = new GlonassDataParser();

        GlonassData glonassData = parser.parse(0, Base64.getDecoder().decode(
                "AQAACwAoAAEAAaUZAEEKhQPZA9loXrQQAQEBFgAD2QPZAzHUODYxNjkzMDMzMTk2MzQ2Kkw=")
        );

        assertThat(glonassData.getHeaderInfo(), Matchers.notNullValue());


    }
}
