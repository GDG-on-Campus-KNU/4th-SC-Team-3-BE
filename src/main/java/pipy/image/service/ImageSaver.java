package pipy.image.service;

import pipy.image.domain.Image;

public interface ImageSaver {

    String save(Image image);
}
