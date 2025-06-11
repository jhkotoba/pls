package jkt.pls.model.request;
// Updated indentation to use tabs

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectApplyRequest {

	private List<ProjectRequest> list;
}
