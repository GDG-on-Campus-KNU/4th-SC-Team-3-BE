package pipy.node.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pipy.global.ApiSuccessResponse;
import pipy.node.application.ImageGenerationService;
import pipy.node.application.NodeCommandService;
import pipy.node.presentation.dto.request.NodeAnalyzeRequest;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;
import reactor.core.publisher.Flux;

import java.util.List;

import static pipy.global.ApiSuccessResponse.ApiSuccessResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nodes")
class NodeCommandController implements NodeCommandApiDocs {

    private final NodeCommandService nodeCommandService;
    private final ImageGenerationService imageGenerationService;

    @GetMapping("/analyze")
    public ResponseEntity<ApiSuccessResult<List<NodeAnalyzeResponse>>> analyzeNode(
        @RequestBody final NodeAnalyzeRequest request
    ) {
        final String content = request.content();
        final List<NodeAnalyzeResponse> response = nodeCommandService.analyzeNode(content);
        return ApiSuccessResponse.success(HttpStatus.OK, response);
    }

    @PostMapping(value = "/generate/image", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateImage(@RequestParam final String prompt) {
        return imageGenerationService.generate(prompt);
    }
}
