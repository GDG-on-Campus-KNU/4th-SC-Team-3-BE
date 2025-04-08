package pipy.edge.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pipy.edge.application.EdgeCommandService;
import pipy.edge.presentation.dto.CreateEdgeRequest;
import pipy.global.ApiSuccessResponse;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nodes")
class EdgeCommandController implements EdgeCommandApiDocs {

    private final EdgeCommandService edgeCommandService;

    @PostMapping("/{nodeId}/connect")
    public ResponseEntity<ApiSuccessResult<Void>> createEdge(
        @RequestBody final CreateEdgeRequest request
    ) {
        edgeCommandService.createEdge(request.getSourceId(), request.getTargetId());
        return ApiSuccessResponse.success(HttpStatus.CREATED);
    }
}
