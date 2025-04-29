package pipy.node.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pipy.node.domain.Image;
import pipy.node.application.ImageSaver;

@Service
public class DummyImageSaver implements ImageSaver {

    @Value("${pipy.storage.url}")
    private String storageUrl;

    @Override
    public String save(final Image image) {
        return storageUrl + "/dummy.png";
    }
}
