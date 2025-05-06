package pipy.global.utils;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.webp.WebpWriter;

import java.io.IOException;

public class WebpConverter {

    public static byte[] convertToWebp(final byte[] thumbnail) {
        try {
            return ImmutableImage.loader()
                .fromBytes(thumbnail)
                .scaleToHeight(400)
                .forWriter(WebpWriter.DEFAULT.withLossless())
                .bytes();
        } catch (final IOException exception) {
            throw new IllegalArgumentException("Failed to convert image to webp", exception);
        }
    }
}
