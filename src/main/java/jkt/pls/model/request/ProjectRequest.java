package jkt.pls.model.request;
// Updated indentation to use tabs

import jkt.pls.model.enumerate.RowStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

	private String projectId;	
	
	private String projectName;
	
	private String description;
	
	private RowStatusEnum _status;
}
