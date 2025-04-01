package pipy.node.domain;

import java.util.List;

public interface NodeAnalyzer {

    List<NodeAnalyzeResult> analyze(final Analyzable analyzable);
}
