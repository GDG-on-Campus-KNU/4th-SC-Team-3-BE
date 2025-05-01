package pipy.project.application;

import pipy.member.domain.Member;

public record CreateProjectCommand(
    Member owner,
    String name,
    String canvas
) {
}