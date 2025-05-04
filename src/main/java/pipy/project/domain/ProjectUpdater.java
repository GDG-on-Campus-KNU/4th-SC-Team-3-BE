package pipy.project.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProjectUpdater {

    private final ProjectReader projectReader;

    @Transactional
    public void updateThumbnail(final Long projectId, final String thumbnail) {
        final Project project = projectReader.readById(projectId);
        project.updateThumbnail(thumbnail);
    }
}
