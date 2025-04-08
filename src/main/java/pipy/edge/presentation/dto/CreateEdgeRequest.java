package pipy.edge.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "엣지 생성 요청")
public class CreateEdgeRequest {

    @Schema(description = "출발 노드 ID")
    private Long sourceId;

    @Schema(description = "도착 노드 ID")
    private Long targetId;
}