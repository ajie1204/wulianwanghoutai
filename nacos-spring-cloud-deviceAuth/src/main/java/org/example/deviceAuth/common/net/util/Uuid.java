package org.example.deviceAuth.common.net.util;

import java.util.UUID;

public class Uuid {
    //得到32位的uuid
    public static String getUUID() {

        String uuid = createUUID();
        String uid = reUUID(uuid);
        return uid;

    }

    public static String reUUID(String uuid) {
        for (int i = 0; i < uuid.length() - 1; i++) {
            String s = uuid.substring(i, i + 2);
            if (s.equals("0d")) {
                uuid = getUUID();
            }

        }
        return uuid;

    }


    public static String createUUID() {
        String uid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uid;
    }





    }


