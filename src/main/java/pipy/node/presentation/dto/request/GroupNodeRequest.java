package pipy.node.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "그룹 노드 생성 요청, type: group")
public class GroupNodeRequest extends NodeRequest {

    @Schema(description = "그룹 내에 속할 노드 생성 요청")
    private List<NodeRequest> contents;
}