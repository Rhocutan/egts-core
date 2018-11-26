package ru.egts.core.checksum;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Base64;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class CRC16Test {

    @Test
    public void crc16() {
        byte[] data = {
                (byte) 0x19, (byte) 0x00, (byte) 0x01, (byte) 0x00,
                (byte) 0x81, (byte) 0x32, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                (byte) 0x16, (byte) 0x00, (byte) 0x32, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x42, (byte) 0x38,
                (byte) 0x36, (byte) 0x38, (byte) 0x32, (byte) 0x30,
                (byte) 0x34, (byte) 0x30, (byte) 0x30, (byte) 0x32,
                (byte) 0x32, (byte) 0x33, (byte) 0x36, (byte) 0x36,
                (byte) 0x34, (byte) 0x33, (byte) 0x00, (byte) 0x01};
        int value = CRC16.crc16(data, data.length);
        assertThat(value, Matchers.is(23923));
    }

    @Test
    public void crc16_2() {
        byte[] bytes = Base64.getDecoder().decode("AEEKhQPZA9loXrQQAQEBFgAD2QPZAzHUODYxNjkzMDMzMTk2MzQ2Kg==");

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(19498));
    }


    @Test
    public void crc16_3() {
        byte[] bytes = "123456789".getBytes();

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x29B1 & 0xFFFF));
    }


}
