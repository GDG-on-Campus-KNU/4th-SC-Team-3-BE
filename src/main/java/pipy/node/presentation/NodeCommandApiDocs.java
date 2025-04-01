package pipy.node.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import pipy.node.presentation.dto.request.NodeRequest;

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

    @PostMapping
    @Operation(summary = "노드 생성 요청", description = "노드를 생성합니다. 가능한 노드 유형에는 `text_prompt`, `category_prompt`, `group_prompt`가 있습니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "노드 생성 성공")
    })
    @RequestBody(
        description = "노드 생성 요청",
        required = true,
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "텍스트 프롬프트 노드 생성",
                    value = TEXT_PROMPT
                ),
                @ExampleObject(
                    name = "카테고리 프롬프트 노드 생성",
                    value = CATEGORY_PROMPT
                ),
                @ExampleObject(
                    name = "그룹 노드 생성",
                    value = GROUP
                )
            }
        ))
    ResponseEntity<ApiSuccessResult<Void>> addNodes(NodeRequest request);
}
