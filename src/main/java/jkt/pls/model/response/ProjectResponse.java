package jkt.pls.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectResponse {
	
	private String projectId;
	private String projectName;
	private String description;
}
