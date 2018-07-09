package com.myself.client;

import java.math.BigInteger;

/**
 * @Author:AwakeningCode
 * @Date: Created in 22:15 2018\3\6 0006
 */
public class CRC16myself {

    public static void main(String[] args) {
        String test = "F5690137563CC8syyyyyyyyyyyyyyyyynnnnnnn";
        System.out.println("原始内容字符串：" + test);
        String crcString = getCRC(test.getBytes());
        System.out.println("str：" + crcString);
        int crc = getCRCInt(test.getBytes());
        System.out.println("hex：" + crc);
        CRC16myself myself = new CRC16myself();
        float crc16 = myself.parseHex2Float(crcString);
        System.out.println("10进制浮点型：" + crc16);
        String crc16String = myself.parseFloat2Hex(crc16);
        System.out.println("十六进制浮点型：" + crc16String);
        System.out.println("输出字符串：" + "gz" + test + crcString + "xr");
    }

    /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static String getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return Integer.toHexString(CRC);
    }


    /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static Integer getCRCInt(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }

    /**
     * 将16进制单精度浮点型转换为10进制浮点型
     *
     * @return float
     * @since 1.0
     */
    private float parseHex2Float(String hexStr) {
        BigInteger bigInteger = new BigInteger(hexStr, 16);
        return Float.intBitsToFloat(bigInteger.intValue());
    }

    /**
     * 将十进制浮点型转换为十六进制浮点型
     *
     * @return String
     * @since 1.0
     */
    private String parseFloat2Hex(float data) {
        return Integer.toHexString(Float.floatToIntBits(data));
    }

}
