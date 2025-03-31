package pipy.project.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import pipy.project.domain.Project;

public record CreateProjectResponse(
    @Schema(description = "생성된 프로젝트 ID") Long projectId
) {

    public static CreateProjectResponse from(final Project project) {
        return new CreateProjectResponse(project.getId());
    }
}