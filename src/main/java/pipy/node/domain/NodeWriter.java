package pipy.node.domain;

import pipy.node.domain.command.NodeCommand;

public interface NodeWriter {

    Node write(NodeCommand request);

    boolean supports(NodeCommand request);
}
