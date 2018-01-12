package com.lucas.wittip.kafka.util;

import java.io.File;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public class KafkaExampleFileUtil {

    public static String getValidDirectoryPath(String filePath) {
        if (filePath.lastIndexOf('/') == (filePath.length() - 1)) {
            return filePath;
        } else {
            return filePath + "/";
        }
    }

    public static boolean isValidDirectory(String filePath) {
        if (filePath == null) {
            return false;
        }

        File temp = null;
        if (filePath.indexOf('/') == (filePath.length() - 1)) {
            temp = new File(filePath);
        } else {
            temp = new File(filePath + "/");
        }

        if (temp.isDirectory()) {
            return true;
        }

        return false;
    }
}
