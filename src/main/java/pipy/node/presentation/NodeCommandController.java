package pipy.node.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pipy.global.ApiSuccessResponse;
import pipy.node.application.NodeCommandService;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nodes")
class NodeCommandController implements NodeCommandApiDocs {

    private final NodeCommandService nodeCommandService;

    @PostMapping
    public ResponseEntity<ApiSuccessResult<Void>> addNodes(
        @RequestBody final NodeRequest request
    ) {
        nodeCommandService.createNode(request);
        return ApiSuccessResponse.success(HttpStatus.CREATED);
    }

    @GetMapping("/{nodeId}/analyze")
    public ResponseEntity<ApiSuccessResult<List<NodeAnalyzeResponse>>> analyzeNode(
        @PathVariable final Long nodeId
    ) {
        final List<NodeAnalyzeResponse> response = nodeCommandService.analyzeNode(nodeId);
        return ApiSuccessResponse.success(HttpStatus.OK, response);
    }
}
