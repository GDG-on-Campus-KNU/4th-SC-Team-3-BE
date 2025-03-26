package pipy.image.domain;

import lombok.Getter;

@Getter
public class Image {

    private final byte[] image;

    public Image(final byte[] image) {
        this.image = image;
    }
}
