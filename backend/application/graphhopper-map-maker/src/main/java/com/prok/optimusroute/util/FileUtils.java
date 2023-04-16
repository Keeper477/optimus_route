package com.prok.optimusroute.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.net.URL;

@UtilityClass
public final class FileUtils {

    public static String getResourceFolderPath(String folderName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(folderName);
        if (resource == null) {
            throw new IllegalArgumentException("Folder not found: " + folderName);
        }
        return new File(resource.getFile()).getAbsolutePath();
    }

    public static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            return resource.getFile();
        }
    }
}
