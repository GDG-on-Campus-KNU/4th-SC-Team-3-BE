package pipy.image.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pipy.image.domain.Image;
import pipy.image.service.ImageSaver;

@Service
public class DummyImageSaver implements ImageSaver {

    @Value("${pipy.base-url}")
    private String baseUrl;

    @Override
    public String save(final Image image) {
        return baseUrl + "/dummy.png";
    }
}
