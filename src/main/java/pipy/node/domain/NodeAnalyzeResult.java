package pipy.node.domain;

import java.util.List;

public record NodeAnalyzeResult(
    String key,
    List<String> value
) {
}