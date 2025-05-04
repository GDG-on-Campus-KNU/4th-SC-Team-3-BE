package pipy.project.application;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.webp.WebpWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pipy.node.application.ImageSaveCommand;
import pipy.node.application.ImageSaver;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectUpdater;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProjectThumbnailSaver {

    private final ImageSaver imageSaver;
    private final ProjectUpdater projectUpdater;

    @Async
    public void save(final Project project, final byte[] thumbnail) {
        final String filename = project.createThumbnail();
        final ImageSaveCommand command = new ImageSaveCommand(
            "image/webp",
            filename,
            convertToWebp(thumbnail)
        );
        final String thumbnailUrl = imageSaver.save(command);
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
