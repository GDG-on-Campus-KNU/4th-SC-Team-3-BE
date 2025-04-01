package pipy.node.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pipy.global.ApiSuccessResponse;
import pipy.node.application.NodeCommandService;
import pipy.node.presentation.dto.request.NodeRequest;

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
}
