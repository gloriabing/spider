package org.gloria.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Create on 2017/3/28 16:58.
 *
 * @author : gloria.
 */
public class FileUtil {

    private static final String FILE_NAME = "your path";


    public static void write(String line) throws IOException {
        FileWriter writer = new FileWriter(FILE_NAME, true);
        writer.write(line+"\r\n");
        writer.close();
        
    }
    
}
