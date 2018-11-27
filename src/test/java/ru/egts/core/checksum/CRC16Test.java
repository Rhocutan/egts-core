package ru.egts.core.checksum;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class CRC16Test {

    @Test
    public void crc16_1() {
        byte[] data = {
                (byte)0x24,
                (byte)0x00,
                (byte)0x02,
                (byte)0x00,
                (byte)0x81,
                (byte)0x32,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x02,
                (byte)0x02,
                (byte)0x10,
                (byte)0x1A,
                (byte)0x00,
                (byte)0x3A,
                (byte)0xDF,
                (byte)0x15,
                (byte)0x0C,
                (byte)0x38,
                (byte)0x26,
                (byte)0xC2,
                (byte)0x9D,
                (byte)0x56,
                (byte)0x9D,
                (byte)0xED,
                (byte)0x5C,
                (byte)0x83,
                (byte)0x00,
                (byte)0x00,
                (byte)0xC7,
                (byte)0x06,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x24,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x11,
                (byte)0x04,
                (byte)0x00,
                (byte)0x0A,
                (byte)0x00,
                (byte)0x00,
                (byte)0x05};
        int value = CRC16.crc16(data, data.length);

        byte[] g = new byte[]{(byte)0x6C, (byte)0x78};
        short aShort = ByteBuffer.wrap(g).order(ByteOrder.LITTLE_ENDIAN).getShort();
        assertThat((short) value, Matchers.is(aShort));
    }

    @Test
    public void crc16() {
        byte[] data = {
                (byte)0x19,
                (byte)0x00,
                (byte)0x01,
                (byte)0x00,
                (byte)0x81,
                (byte)0x32,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x01,
                (byte)0x01,
                (byte)0x01,
                (byte)0x16,
                (byte)0x00,
                (byte)0x32,
                (byte)0x00,
                (byte)0x00,
                (byte)0x00,
                (byte)0x42,
                (byte)0x38,
                (byte)0x36,
                (byte)0x38,
                (byte)0x32,
                (byte)0x30,
                (byte)0x34,
                (byte)0x30,
                (byte)0x30,
                (byte)0x32,
                (byte)0x32,
                (byte)0x33,
                (byte)0x36,
                (byte)0x36,
                (byte)0x34,
                (byte)0x33,
                (byte)0x00,
                (byte)0x01};
        int value = CRC16.crc16(data, data.length);

        byte[] g = new byte[]{(byte)0x5D, (byte)0x73};
        short aShort = ByteBuffer.wrap(g).order(ByteOrder.LITTLE_ENDIAN).getShort();
        assertThat((short) value, Matchers.is(aShort));
    }


    @Test
    public void crc16_3() {
        byte[] bytes = "123456789".getBytes();

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x29B1 & 0xFFFF));
    }

    @Test
    public void crc16_4() {
        byte[] bytes = new byte[] {(byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39};

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x29B1 & 0xFFFF));
    }


    @Test
    public void crc16_5() {
        byte[] bytes = new byte[] {(byte)0x19,(byte)0x00,(byte)0x41,(byte)0x0A,(byte)0x85,(byte)0x03,(byte)0xD9,(byte)0x03,(byte)0xD9,(byte)0x68,(byte)0x5E,(byte)0xB4,(byte)0x10,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x16,(byte)0x00,(byte)0x03,(byte)0xD9,(byte)0x03,(byte)0xD9,(byte)0x03,(byte)0x31,(byte)0xD4,(byte)0x38,(byte)0x36,(byte)0x31,(byte)0x36,(byte)0x39,(byte)0x33,(byte)0x30,(byte)0x33,(byte)0x33,(byte)0x31,(byte)0x39,(byte)0x36,(byte)0x33,(byte)0x34,(byte)0x36,};

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x4C2A & 0xFFFF));
    }

    @Test
    public void crc16_6() {
        byte[] bytes = new byte[] {(byte)0x19,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x81,(byte)0x32,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x16,(byte)0x00,(byte)0x32,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x42,(byte)0x38,(byte)0x36,(byte)0x38,(byte)0x32,(byte)0x30,(byte)0x34,(byte)0x30,(byte)0x30,(byte)0x32,(byte)0x32,(byte)0x33,(byte)0x36,(byte)0x36,(byte)0x34,(byte)0x33,(byte)0x00,(byte)0x01,};

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x735D & 0xFFFF));
    }
    @Test
    public void crc16_7() {
        byte[] bytes = new byte[] {(byte)0x24,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x81,(byte)0x32,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x02,(byte)0x10,(byte)0x1A,(byte)0x00,(byte)0x3A,(byte)0xDF,(byte)0x15,(byte)0x0C,(byte)0x38,(byte)0x26,(byte)0xC2,(byte)0x9D,(byte)0x56,(byte)0x9D,(byte)0xED,(byte)0x5C,(byte)0x83,(byte)0x00,(byte)0x00,(byte)0xC7,(byte)0x06,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x24,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x11,(byte)0x04,(byte)0x00,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x05,};

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x786C & 0xFFFF));
    }

    @Test
    public void crc16_8() {
        byte[] bytes = new byte[] {(byte)0x04,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x07,(byte)0x01,(byte)0x00,(byte)0x00,};

        int value = CRC16.crc16(bytes, bytes.length);
        assertThat(value, Matchers.is(0x0CA4 & 0xFFFF));
    }


}
