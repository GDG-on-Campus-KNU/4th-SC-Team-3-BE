package pipy.edge.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import pipy.edge.presentation.dto.CreateEdgeRequest;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "엣지")
interface EdgeCommandApiDocs {

    @Operation(summary = "엣지 생성 요청", description = "엣지를 생성해 두 노드를 연결합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "두 노드를 연결했습니다."),
        @ApiResponse(responseCode = "404", description = "해당 노드를 찾을 수 없습니다."),
    })
    ResponseEntity<ApiSuccessResult<Void>> createEdge(CreateEdgeRequest request);
}
