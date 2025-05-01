package pipy.node.infrastructure;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pipy.node.application.ImageSaver;
import pipy.node.application.ImageSaveCommand;

@Component
@RequiredArgsConstructor
public class GoogleStorageImageSaver implements ImageSaver {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${pipy.storage.url}")
    private String storageUrl;

    @Override
    public String save(final ImageSaveCommand command) {
        final BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, command.filename())
            .setContentType(command.contentType())
            .build();
        storage.create(
            blobInfo,
            command.bytes()
        );
        return String.format("%s/%s/%s", storageUrl, bucketName, command.filename());
    }
}
