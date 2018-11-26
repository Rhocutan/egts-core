package ru.egts.core.parser;

import lombok.extern.slf4j.Slf4j;
import ru.egts.core.bean.GlonassData;
import ru.egts.core.bean.Response;
import ru.egts.core.checksum.CRC16;
import ru.egts.core.checksum.CRC8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class ResponseParser implements Parser<byte[], GlonassData> {

    public static final byte EGTS_PC_OK = (byte) 0x00;
    private static final byte EGTS_PC_INC_DATAFORM = (byte) 0x84;
    private static final byte[] ERROR = new byte[]{(byte) 0x84};


    @Override
    public byte[] parse(int start, GlonassData response) {
        byte[] arr = new byte[4];
        try {
            byte[] header;

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                bos.write(intTounsignedIByte(response.getHeaderInfo().getProtocolVersion()));
                bos.write(intTounsignedIByte(response.getHeaderInfo().getSecurityKeyId()));
                // flags
                bos.write((byte) 0x00);

                // header length = 11
                bos.write((byte) 0x0B);

                // header encoding
                bos.write(intTounsignedIByte(response.getHeaderInfo().getHeaderEncoding()));

                // frame data length
                final int size = 3 * response.getRecords().size() + 3;
                bos.write(intToUnsignedShortBytes(size));

                // package id
                bos.write(intToUnsignedShortBytes(response.getHeaderInfo().getPacketIdentifier()));

                // package type
                bos.write(intTounsignedIByte(response.getHeaderInfo().getPacketType().getCode()));

                // header checksum CRC-8
                header = bos.toByteArray();
            }

            final byte crc8 = intTounsignedIByte(CRC8.crc8(header, header.length));

            byte[] sfrd;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                // sfrd
                // response packet id
                bos.write(intToUnsignedShortBytes(response.getHeaderInfo().getPacketIdentifier()));
                // processing result
                bos.write(EGTS_PC_OK);

                // records (each one is 3 bytes)
                response.getRecords().forEach(r -> {
                    final Response record = (Response) r;
                    try {
                        bos.write(intToUnsignedShortBytes(record.getResponsePacketId()));
                        bos.write((byte) 0x01);
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                });

                bos.write(EGTS_PC_OK);
                sfrd = bos.toByteArray();
            }

            // sfrcs CRC-16
            final int crc16 = CRC16.crc16(sfrd, sfrd.length);

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                bos.write(header);
                bos.write(crc8);
                bos.write(sfrd);
                bos.write(intToUnsignedShortBytes(crc16));
                return bos.toByteArray();
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ERROR;
        }
    }
}
