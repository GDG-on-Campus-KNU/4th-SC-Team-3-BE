package pipy.node.infrastructure;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pipy.node.application.StorageManager;
import pipy.node.application.StorageSaveCommand;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleCloudStorageManager implements StorageManager {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${pipy.storage.url}")
    private String storageUrl;

    @Override
    public String save(final StorageSaveCommand command) {
        final BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, command.filename())
            .setContentType(command.contentType())
            .build();
        storage.create(
            blobInfo,
            command.bytes()
        );
        final String objectName = blobInfo.getBlobId().getName();
        log.debug("File saved: {}", objectName);
        return String.format("%s/%s/%s", storageUrl, bucketName, command.filename());
    }

    @Override
    public void delete(final String filename) {
        final String prefix = String.format("%s/%s/", storageUrl, bucketName);
        final String objectName = filename.replace(prefix, "");
        log.debug("Deleting file: {}", objectName);
        final BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, objectName).build();
        storage.delete(blobInfo.getBlobId());
    }
}
