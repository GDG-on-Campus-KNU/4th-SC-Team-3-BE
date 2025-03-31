package pipy.node.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.global.ApiSuccessResponse;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.application.NodeCommandService;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "노드")
@RestController
@RequiredArgsConstructor
@RequestMapping("/nodes")
public class NodeCommandController {

    private final NodeCommandService nodeCommandService;

    @PostMapping
    @Operation(summary = "노드 생성 요청", description = "노드를 생성합니다. 가능한 노드 유형에는 `text_prompt`, `category_prompt`, `group_prompt`가 있습니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "노드 생성 성공")
    })
    public ResponseEntity<ApiSuccessResult<Void>> addNodes(
        @RequestBody final NodeRequest request
    ) {
        nodeCommandService.createNode(request);
        return ApiSuccessResponse.success(HttpStatus.CREATED);
    }
}
