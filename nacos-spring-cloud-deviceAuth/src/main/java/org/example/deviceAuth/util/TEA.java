package org.example.deviceAuth.util;


import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

@Component
public class TEA {


    public byte[] encrypt(byte[] data, byte[] key) {
        int data_len = data.length; // 数据的长度
        if (data_len == 0) {
            return new byte[]{};
        }
        TEA t = new TEA();
        if (!t.setKey(key)) {
            return new byte[]{};
        }

        int group_len = 8;
        int residues = data_len % group_len; // 余数
        int dlen = data_len - residues;

        // 用于储存加密的密文，第一字节为余数的大小
        int result_len = data_len+1;//
        if (residues > 0) {
            result_len += group_len - residues;
        }
        byte[] result = new byte[result_len];
        if(residues ==0){
            result[0]=8;
        }
        result[0] = (byte) residues;

        byte[] plain = new byte[group_len];
        byte[] enc = new byte[group_len];

        for (int i = 0; i < dlen; i += group_len) {
            for (int j = 0; j < group_len; j++) {
                plain[j] = data[i + j];
            }
            enc = t.encrypt_group(plain);
            for (int k = 0; k < group_len; k++) {
                result[i + k + 1] = enc[k];
            }
        }
//        后面补0
        if (residues > 0) {
            for (int j = 0; j < residues; j++) {
                plain[j] = data[dlen + j];
            }
            int padding = group_len - residues;
            for (int j = 0; j < padding; j++) {
                plain[residues + j] = (byte) 0x00;
            }
            enc = t.encrypt_group(plain);
            for (int k = 0; k < group_len; k++) {
                result[dlen + k + 1] = enc[k];
            }
        }
        return result;
    }

    public byte[] decrypt(byte[] data, byte[] key) {
        int group_len = 8;
        if (data.length % group_len != 1) {
            return new byte[]{};
        }
        TEA t = new TEA();
        if (!t.setKey(key)) {
            return new byte[]{};
        }
        int data_len = data.length - 1, dlen,residues; // 数据的长度
        int in = (int) (data[0]); // 余数
        if(in==8){
            residues=0;
            System.out.println("首字节8");
        }else{
            residues=in;
        }
        if (residues >0) {
            dlen = data_len - group_len;
        } else {
            dlen = data_len;
        }
        byte[] result = new byte[dlen + residues];
        byte[] dec = new byte[group_len];
        byte[] enc = new byte[group_len];
        for (int i = 0; i < dlen; i += group_len) {
            for (int j = 0; j < group_len; j++) {
                enc[j] = data[i + j + 1];
            }
            dec = t.decrypt_group(enc);
            for (int k = 0; k < group_len; k++) {
                result[i + k] = dec[k];
            }
        }
        if (residues > 0) {
            for (int j = 0; j < group_len; j++) {
                enc[j] = data[dlen + j + 1];
            }
            dec = t.decrypt_group(enc);
            for (int k = 0; k < residues; k++) {
                result[dlen + k] = dec[k];
            }
        }
        return result;
    }

    /**
     * 设置密钥
     *
     * @param k 密钥
     * @return 密钥长度为16个byte时， 设置密钥并返回true，否则返回false
     */
    public boolean setKey(byte[] k) {
        if (k.length != 16) {
            return false;
        }
        k0 = bytes_to_uint32(new byte[]{k[0], k[1], k[2], k[3]});
        k1 = bytes_to_uint32(new byte[]{k[4], k[5], k[6], k[7]});
        k2 = bytes_to_uint32(new byte[]{k[8], k[9], k[10], k[11]});
        k3 = bytes_to_uint32(new byte[]{k[12], k[13], k[14], k[15]});
        return true;
    }

    /**
     * 设置加密的轮数，默认为32轮
     *
     * @param loops 加密轮数
     * @return 轮数为16、32、64时，返回true，否则返回false
     */
    public boolean setLoops(int loops) {
        switch (loops) {
            case 16:
            case 32:
            case 64:
                this.loops = loops;
                return true;
        }
        return false;
    }

    private long UINT32_MAX = 0xFFFFFFFFL;
    private long BYTE_1 = 0xFFL;
    private long BYTE_2 = 0xFF00L;
    private long BYTE_3 = 0xFF0000L;
    private long BYTE_4 = 0xFF000000L;

    private long delta = 0x9E3779B9L;

    private long k0, k1, k2, k3;

    private int loops = 32;

    /**
     * 加密一组明文
     *
     * @param v 需要加密的明文
     * @return 返回密文
     */
    private byte[] encrypt_group(byte[] v) {
        long v0 = bytes_to_uint32(new byte[]{v[0], v[1], v[2], v[3]});
        long v1 = bytes_to_uint32(new byte[]{v[4], v[5], v[6], v[7]});
        long sum = 0L;
        long v0_xor_1 = 0L, v0_xor_2 = 0L, v0_xor_3 = 0L;
        long v1_xor_1 = 0L, v1_xor_2 = 0L, v1_xor_3 = 0L;
        for (int i = 0; i < loops; i++) {
            sum = toUInt32(sum + delta);
            v0_xor_1 = toUInt32(toUInt32(v1 << 4) + k0);
            v0_xor_2 = toUInt32(v1 + sum);
            v0_xor_3 = toUInt32((v1 >> 5) + k1);
            v0 = toUInt32(v0 + toUInt32(v0_xor_1 ^ v0_xor_2 ^ v0_xor_3));
            v1_xor_1 = toUInt32(toUInt32(v0 << 4) + k2);
            v1_xor_2 = toUInt32(v0 + sum);
            v1_xor_3 = toUInt32((v0 >> 5) + k3);
//            System.out.printf("%08X\t%08X\t%08X\t%08X\n", i, v0, v0 >> 5, k3);
            v1 = toUInt32(v1 + toUInt32(v1_xor_1 ^ v1_xor_2 ^ v1_xor_3));
        }
        byte[] b0 = long_to_bytes(v0, 4);
        byte[] b1 = long_to_bytes(v1, 4);
        return new byte[]{b0[0], b0[1], b0[2], b0[3], b1[0], b1[1], b1[2], b1[3]};
    }

    /**
     * 解密一组密文
     *
     * @param v 要解密的密文
     * @return 返回明文
     */
    private byte[] decrypt_group(byte[] v) {
        long v0 = bytes_to_uint32(new byte[]{v[0], v[1], v[2], v[3]});
        long v1 = bytes_to_uint32(new byte[]{v[4], v[5], v[6], v[7]});
        long sum = 0xC6EF3720L, tmp = 0L;
        for (int i = 0; i < loops; i++) {
            tmp = toUInt32(toUInt32(v0 << 4) + k2);
            v1 = toUInt32(v1 - toUInt32(tmp ^ toUInt32(v0 + sum) ^ toUInt32((v0 >> 5) + k3)));
            tmp = toUInt32(toUInt32(v1 << 4) + k0);
            v0 = toUInt32(v0 - toUInt32(tmp ^ toUInt32(v1 + sum) ^ toUInt32((v1 >> 5) + k1)));
            sum = toUInt32(sum - delta);
        }
        byte[] b0 = long_to_bytes(v0, 4);
        byte[] b1 = long_to_bytes(v1, 4);
        return new byte[]{b0[0], b0[1], b0[2], b0[3], b1[0], b1[1], b1[2], b1[3]};
    }


    /**
     * 将 long 类型的 n 转为 byte 数组，如果 len 为 4，则只返回低32位的4个byte
     *
     * @param n   需要转换的long
     * @param len 若为4，则只返回低32位的4个byte，否则返回8个byte
     * @return 转换后byte数组
     */
    private byte[] long_to_bytes(long n, int len) {
        byte a = (byte) ((n & BYTE_4) >> 24);
        byte b = (byte) ((n & BYTE_3) >> 16);
        byte c = (byte) ((n & BYTE_2) >> 8);
        byte d = (byte) (n & BYTE_1);
        if (len == 4) {
            return new byte[]{a, b, c, d};
        }
        byte ha = (byte) (n >> 56);
        byte hb = (byte) ((n >> 48) & BYTE_1);
        byte hc = (byte) ((n >> 40) & BYTE_1);
        byte hd = (byte) ((n >> 32) & BYTE_1);
        return new byte[]{ha, hb, hc, hd, a, b, c, d};
    }

    /**
     * 将4个byte转为 Unsigned Integer 32，以 long 形式返回
     *
     * @param bs 需要转换的字节
     * @return 返回 long，高32位为0，低32位视为Unsigned Integer
     */
    private long bytes_to_uint32(byte[] bs) {
        return ((bs[0] << 24) & BYTE_4) +
                ((bs[1] << 16) & BYTE_3) +
                ((bs[2] << 8) & BYTE_2) +
                (bs[3] & BYTE_1);
    }
    /**
     * 将long的高32位清除，只保留低32位，低32位视为Unsigned Integer
     * @param n 需要清除的long
     * @return 返回高32位全为0的long
     */
    private long toUInt32(long n) {
        return n & UINT32_MAX;
    }

}