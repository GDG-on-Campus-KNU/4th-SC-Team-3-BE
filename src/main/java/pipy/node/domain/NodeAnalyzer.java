package pipy.node.domain;

import reactor.core.publisher.Flux;

public interface NodeAnalyzer {

    Flux<NodeAnalyzeResult> analyze(final String content);
}
