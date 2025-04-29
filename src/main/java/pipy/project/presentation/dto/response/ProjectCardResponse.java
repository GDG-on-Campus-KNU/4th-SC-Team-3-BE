package pipy.project.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import pipy.project.domain.Project;

import java.time.LocalDateTime;

@Schema(description = "프로젝트 목록 카드")
public record ProjectCardResponse(
    @Schema(description = "프로젝트 ID") Long projectId,
    @Schema(description = "프로젝트 이름") String name,
    @Schema(description = "프로젝트 썸네일") String thumbnail,
    @Schema(description = "프로젝트 생성일") LocalDateTime createdAt,
    @Schema(description = "프로젝트 최근 수정일") LocalDateTime updatedAt
) {

    public static ProjectCardResponse from(final Project project) {
        return new ProjectCardResponse(
            project.getId(),
            project.getName(),
            project.getThumbnail(),
            project.getCreatedAt(),
            project.getUpdatedAt()
        );
    }
}