package pipy.node.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import pipy.auth.application.PipyUser;
import pipy.node.presentation.dto.request.ImageGenerationRequest;
import pipy.node.presentation.dto.request.NodeAnalyzeRequest;
import pipy.node.presentation.dto.response.ImageGenerationResponse;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;
import reactor.core.publisher.Flux;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "노드")
interface NodeCommandApiDocs {

    String TEXT_PROMPT =
        """
        {
          "projectId": 1,
          "nodes": [
            {
              "nodeId": 1,
              "content": "분홍색 머리카락을 가진 고등학생 소녀가 학교 밴드부에서 기타를 치다가 기타 줄이 끊어진 모습",
              "type": "text_prompt"
            }
          ]
        }
        """;

    String CATEGORY_PROMPT =
        """
        {
          "projectId": 1,
          "nodes": [
            {
              "nodeId": 1,
              "key": "카테고리 키",
              "value": ["카테고리 값1", "카테고리 값2"],
              "type": "category_prompt"
            }
          ]
        }
        """;

    String GROUP =
        """
        {
          "projectId": 1,
          "nodes": [
            {
              "nodeId": 1,
              "contents": [
                {
                  "nodeId": 2,
                  "key": "카테고리 키",
                  "value": ["카테고리 값1", "카테고리 값2"],
                  "type": "category_prompt"
                },
                {
                  "nodeId": 3,
                  "key": "카테고리 키",
                  "value": ["카테고리 값1", "카테고리 값2"],
                  "type": "category_prompt"
                }
              ],
              "type": "group"
            }
          ]
        }
        """;

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
            - 사진 생성이 시작되면, `{ "event": "generate_image_start" }` 이벤트가 전송됩니다.
            - 사진 생성이 완료되면, `{ "event": "generated_image", "data": { "url": "http://example.com" }`과 같이 사진 URL이 전송됩니다.
            - `{ "event": "generate_image_end" }` 이벤트가 전송됩니다.
            """
    )
    @RequestBody(
        description = "사진 생성 요청",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "텍스트 프롬프트 노드",
                    value = TEXT_PROMPT
                ),
                @ExampleObject(
                    name = "카테고리 프롬프트 노드",
                    value = CATEGORY_PROMPT
                ),
                @ExampleObject(
                    name = "그룹 노드",
                    value = GROUP
                )
            }
        ))
    Flux<ImageGenerationResponse> generateImage(PipyUser user, ImageGenerationRequest request);
}
