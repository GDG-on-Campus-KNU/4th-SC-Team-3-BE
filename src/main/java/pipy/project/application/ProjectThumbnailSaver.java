package pipy.project.application;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.webp.WebpWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pipy.node.application.StorageSaveCommand;
import pipy.node.application.StorageManager;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectUpdater;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProjectThumbnailSaver {

    private final StorageManager storageManager;
    private final ProjectUpdater projectUpdater;

    @Async
    public void save(final Project project, final byte[] thumbnail) {
        if (project.getThumbnail() != null) {
            storageManager.delete(project.getThumbnail());
        }
        final String filename = project.createThumbnail();
        final StorageSaveCommand command = new StorageSaveCommand(
            "image/webp",
            filename,
            convertToWebp(thumbnail)
        );
        final String thumbnailUrl = storageManager.save(command);
        projectUpdater.updateThumbnail(project.getId(), thumbnailUrl);
    }

    private byte[] convertToWebp(final byte[] thumbnail) {
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
