package pipy.node.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.project.domain.Project;

@Getter
@Entity
@Table(name = "text_prompt_node")
@DiscriminatorValue("text_prompt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextPromptNode extends Node implements Analyzable {

    private String content;

    @Builder
    public TextPromptNode(
        final Long id,
        final String content,
        final Project project
    ) {
        super(id, project);
        this.content = content;
    }
}