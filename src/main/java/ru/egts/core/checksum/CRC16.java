package ru.egts.core.checksum;

public class CRC16 {
    private final static byte[] CRC16Table = {
            (byte) 0x0000, (byte) 0x1021, (byte) 0x2042, (byte) 0x3063, (byte) 0x4084, (byte) 0x50A5, (byte) 0x60C6, (byte) 0x70E7, (byte)

            0x8108, (byte) 0x9129, (byte) 0xA14A, (byte) 0xB16B, (byte) 0xC18C, (byte) 0xD1AD, (byte) 0xE1CE, (byte) 0xF1EF, (byte)

            0x1231, (byte) 0x0210, (byte) 0x3273, (byte) 0x2252, (byte) 0x52B5, (byte) 0x4294, (byte) 0x72F7, (byte) 0x62D6, (byte)

            0x9339, (byte) 0x8318, (byte) 0xB37B, (byte) 0xA35A, (byte) 0xD3BD, (byte) 0XC39C, (byte) 0xF3FF, (byte) 0xE3DE, (byte)

            0x2462, (byte) 0x3443, (byte) 0x0420, (byte) 0x1401, (byte) 0x64E6, (byte) 0x74C7, (byte) 0x44A4, (byte) 0x5485, (byte)

            0xA56A, (byte) 0xB54B, (byte) 0x8528, (byte) 0x9509, (byte) 0xE5EE, (byte) 0xF5CF, (byte) 0xC5AC, (byte) 0xD58D, (byte)

            0x3653, (byte) 0x2672, (byte) 0x1611, (byte) 0x0630, (byte) 0x76D7, (byte) 0x66F6, (byte) 0x5695, (byte) 0x46B4, (byte)

            0xB75B, (byte) 0xA77A, (byte) 0x9719, (byte) 0x8738, (byte) 0xF7DF, (byte) 0xE7FE, (byte) 0xD79D, (byte) 0xC7BC, (byte)

            0x48C4, (byte) 0x58E5, (byte) 0x6886, (byte) 0x78A7, (byte) 0x0840, (byte) 0x1861, (byte) 0x2802, (byte) 0x3823, (byte)

            0xC9CC, (byte) 0xD9ED, (byte) 0xE98E, (byte) 0xF9AF, (byte) 0x8948, (byte) 0x9969, (byte) 0xA90A, (byte) 0xB92B, (byte)

            0x5AF5, (byte) 0x4AD4, (byte) 0x7AB7, (byte) 0x6A96, (byte) 0x1A71, (byte) 0x0A50, (byte) 0x3A33, (byte) 0x2A12, (byte)

            0xDBFD, (byte) 0xCBDC, (byte) 0xFBBF, (byte) 0xEB9E, (byte) 0x9B79, (byte) 0x8B58, (byte) 0xBB3B, (byte) 0xAB1A, (byte)

            0x6CA6, (byte) 0x7C87, (byte) 0x4CE4, (byte) 0x5CC5, (byte) 0x2C22, (byte) 0x3C03, (byte) 0x0C60, (byte) 0x1C41, (byte)

            0xEDAE, (byte) 0xFD8F, (byte) 0xCDEC, (byte) 0xDDCD, (byte) 0xAD2A, (byte) 0xBD0B, (byte) 0x8D68, (byte) 0x9D49, (byte)

            0x7E97, (byte) 0x6EB6, (byte) 0x5ED5, (byte) 0x4EF4, (byte) 0x3E13, (byte) 0x2E32, (byte) 0x1E51, (byte) 0x0E70, (byte)

            0xFF9F, (byte) 0xEFBE, (byte) 0xDFDD, (byte) 0xCFFC, (byte) 0xBF1B, (byte) 0xAF3A, (byte) 0x9F59, (byte) 0x8F78, (byte)

            0x9188, (byte) 0x81A9, (byte) 0xB1CA, (byte) 0xA1EB, (byte) 0xD10C, (byte) 0xC12D, (byte) 0xF14E, (byte) 0xE16F, (byte)

            0x1080, (byte) 0x00A1, (byte) 0x30C2, (byte) 0x20E3, (byte) 0x5004, (byte) 0x4025, (byte) 0x7046, (byte) 0x6067, (byte)

            0x83B9, (byte) 0x9398, (byte) 0xA3FB, (byte) 0xB3DA, (byte) 0xC33D, (byte) 0xD31C, (byte) 0xE37F, (byte) 0xF35E, (byte)

            0x02B1, (byte) 0x1290, (byte) 0x22F3, (byte) 0x32D2, (byte) 0x4235, (byte) 0x5214, (byte) 0x6277, (byte) 0x7256, (byte)

            0xB5EA, (byte) 0xA5CB, (byte) 0x95A8, (byte) 0x8589, (byte) 0xF56E, (byte) 0xE54F, (byte) 0xD52C, (byte) 0xC50D, (byte)

            0x34E2, (byte) 0x24C3, (byte) 0x14A0, (byte) 0x0481, (byte) 0x7466, (byte) 0x6447, (byte) 0x5424, (byte) 0x4405, (byte)

            0xA7DB, (byte) 0xB7FA, (byte) 0x8799, (byte) 0x97B8, (byte) 0xE75F, (byte) 0xF77E, (byte) 0xC71D, (byte) 0xD73C, (byte)

            0x26D3, (byte) 0x36F2, (byte) 0x0691, (byte) 0x16B0, (byte) 0x6657, (byte) 0x7676, (byte) 0x4615, (byte) 0x5634, (byte)

            0xD94C, (byte) 0xC96D, (byte) 0xF90E, (byte) 0xE92F, (byte) 0x99C8, (byte) 0x89E9, (byte) 0xB98A, (byte) 0xA9AB, (byte)

            0x5844, (byte) 0x4865, (byte) 0x7806, (byte) 0x6827, (byte) 0x18C0, (byte) 0x08E1, (byte) 0x3882, (byte) 0x28A3, (byte)

            0xCB7D, (byte) 0xDB5C, (byte) 0xEB3F, (byte) 0xFB1E, (byte) 0x8BF9, (byte) 0x9BD8, (byte) 0xABBB, (byte) 0xBB9A, (byte)

            0x4A75, (byte) 0x5A54, (byte) 0x6A37, (byte) 0x7A16, (byte) 0x0AF1, (byte) 0x1AD0, (byte) 0x2AB3, (byte) 0x3A92, (byte)

            0xFD2E, (byte) 0xED0F, (byte) 0xDD6C, (byte) 0xCD4D, (byte) 0xBDAA, (byte) 0xAD8B, (byte) 0x9DE8, (byte) 0x8DC9, (byte)

            0x7C26, (byte) 0x6C07, (byte) 0x5C64, (byte) 0x4C45, (byte) 0x3CA2, (byte) 0x2C83, (byte) 0x1CE0, (byte) 0x0CC1, (byte)

            0xEF1F, (byte) 0xFF3E, (byte) 0xCF5D, (byte) 0xDF7C, (byte) 0xAF9B, (byte) 0xBFBA, (byte) 0x8FD9, (byte) 0x9FF8, (byte)

            0x6E17, (byte) 0x7E36, (byte) 0x4E55, (byte) 0x5E74, (byte) 0x2E93, (byte) 0x3EB2, (byte) 0x0ED1, (byte) 0x1EF0
    };

    public static int crc16(byte[] data, int length) {
        int crc = (byte) 0xFFFF & 0xFF;
        for (int i = length; i > 0; i--) {
            crc = (crc << 8) ^ (CRC16Table[((crc >> 8) ^ data[length - i]) & 0xFF] & 0xFF);
        }
        return crc;
    }

}
