package org.example.deviceAuth.util;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;

public class ByteUtil {

    public  byte[] objectToByteArray(Object obj)  {
        int length = Array.getLength(obj);
        byte[] byteArray = new byte[length];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) Array.get(obj, i);
        }
        return byteArray;

    }



    }
