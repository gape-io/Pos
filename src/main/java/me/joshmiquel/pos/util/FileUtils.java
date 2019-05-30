package me.joshmiquel.pos.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    /**
     * Copies a file.
     *
     * @param in   The file to copy.
     * @param file Destination.
     */
    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];

            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);

            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
