package cz.kostka.rybyjstr.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class ImageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ImageUtil.class);

    private ImageUtil() {
    }

    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];

        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        try {
            outputStream.close();
        } catch (Exception e) {
            LOG.error("Error during compression of image.");
        }

        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {

        if (data == null) {
            return new byte[0];
        }

        final Inflater inflater = new Inflater();
        inflater.setInput(data);

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
            LOG.error("Error during decompression of image.");
        }

        return outputStream.toByteArray();
    }
}
