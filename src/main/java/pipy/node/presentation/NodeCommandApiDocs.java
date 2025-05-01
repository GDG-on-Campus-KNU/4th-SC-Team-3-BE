package pipy.node.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import pipy.node.presentation.dto.request.NodeAnalyzeRequest;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;
import reactor.core.publisher.Flux;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "노드")
interface NodeCommandApiDocs {

    @Operation(summary = "노드 분석 요청", description = "노드를 분석합니다.")
    @RequestBody(
        description = "노드 분석 요청",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = NodeAnalyzeRequest.class)
        )
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "노드 분석 결과가 배열로 주어집니다.")
    })
    ResponseEntity<ApiSuccessResult<List<NodeAnalyzeResponse>>> analyzeNode(NodeAnalyzeRequest request);

    @Operation(
        summary = "사진 생성 요청",
        description = """
            사진을 생성합니다. 사진 생성은 비동기적으로 진행되기 때문에, 다음과 같이 SSE(Server-Sent Events) 방식으로 결과가 전송됩니다.
            
            ### SSE 이벤트 흐름
            - 사진 생성이 시작되면, `data: generate_image_start` 이벤트가 전송됩니다.
            - 사진 생성이 완료되면, `data: { "url": "http://example.com" }`과 같이 사진 URL이 전송됩니다.
            - `data: generate_image_end` 이벤트가 전송됩니다.
            """
    )
    @Parameter(
        name = "prompt",
        description = "사진 생성에 사용할 프롬프트",
        required = true,
        example = "A beautiful sunset over the mountains"
    )
    Flux<String> generateImage(@RequestParam final String prompt);
}
