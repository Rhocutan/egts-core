package ru.egts.core.checksum;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class CRC8Test {

    @Test
    public void crc8() {
        int value = CRC8.crc8(new byte[]{(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x0B, (byte) 0x00,
                (byte) 0x10, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00}, 10);
        assertThat(value, Matchers.is(0x2E & 0xFF));
    }

    @Test
    public void crc8_2() {
        int value = CRC8.crc8(new byte[]{(byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x0B, (byte) 0x00, (byte) 0x24,
                (byte) 0x00, (byte) 0x01, (byte) 0x00,
                (byte) 0x01}, 10);
        assertThat(value, Matchers.is(0x84 & 0xFF));
    }

    @Test
    public void crc8_3() {
        byte[] bytes = "123456789".getBytes();
        int value = CRC8.crc8(bytes, bytes.length);

        assertThat(value, Matchers.is(0xF7 & 0xFF));
    }

}
