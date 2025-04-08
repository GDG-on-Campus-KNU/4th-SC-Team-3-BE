package pipy.node.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import pipy.global.ApiErrorResponse;
import pipy.global.ApiSuccessResponse;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@Tag(name = "노드")
interface NodeCommandApiDocs {

    String TEXT_PROMPT =
        """
        {
          "nodeId": 1,
          "projectId": 1,
          "content": "사용자가 입력한 텍스트",
          "type": "text_prompt"
        }
        """;

    String CATEGORY_PROMPT =
        """
        {
          "nodeId": 1,
          "projectId": 1,
          "key": "카테고리 키",
          "value": ["카테고리 값1", "카테고리 값2"],
          "type": "category_prompt"
        }
        """;

    String GROUP =
        """
        {
          "nodeId": 1,
          "contents": [
            {
              "nodeId": 2,
              "projectId": 1,
              "key": "카테고리 키",
              "value": ["카테고리 값1", "카테고리 값2"],
              "type": "category_prompt"
            },
            {
              "nodeId": 3,
              "projectId": 1,
              "key": "카테고리 키",
              "value": ["카테고리 값1", "카테고리 값2"],
              "type": "category_prompt"
            }
          ],
          "type": "group"
        }
        """;

    @Operation(summary = "노드 저장 요청", description = "노드를 저장합니다. 가능한 노드 유형에는 `text_prompt`, `category_prompt`, `group_prompt`가 있습니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "노드 저장 성공", content = @Content(
            schema = @Schema(implementation = ApiSuccessResponse.ApiSuccessResultWithoutData.class)
        )),
    })
    @RequestBody(
        description = "노드 ID는 TSID(Time-Sorted Unique Identifier) 방식을 통해 직접 할당해주세요.",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "텍스트 프롬프트 노드 저장 요청",
                    value = TEXT_PROMPT
                ),
                @ExampleObject(
                    name = "카테고리 프롬프트 노드 저장 요청",
                    value = CATEGORY_PROMPT
                ),
                @ExampleObject(
                    name = "그룹 노드 저장 요청",
                    value = GROUP
                )
            }
        ))
    ResponseEntity<ApiSuccessResult<Void>> saveNode(NodeRequest request);

    @Operation(summary = "노드 분석 요청", description = "노드를 분석합니다. 가능한 노드 유형에는 `text_prompt`가 있습니다.")
    @Parameter(name = "nodeId", description = "분석할 노드 ID", required = true)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "노드 분석 결과가 배열로 주어집니다."),
        @ApiResponse(responseCode = "404", description = "해당 노드를 찾을 수 없습니다."),
        @ApiResponse(
            responseCode = "422",
            description = "분석할 수 없는 노드 유형입니다.",
            content = @Content(
                schema = @Schema(implementation = ApiErrorResponse.ApiErrorResult.class)
            )
        )
    })
    ResponseEntity<ApiSuccessResult<List<NodeAnalyzeResponse>>> analyzeNode(Long nodeId);
}
