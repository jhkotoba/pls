package jkt.pls.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectApplyRequest {

	private String projectId;	
	
	private String projectName;
	
	private String description;
}
