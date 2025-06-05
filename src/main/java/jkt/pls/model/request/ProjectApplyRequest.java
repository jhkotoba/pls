package jkt.pls.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectApplyRequest {

	private List<ProjectRequest> list;
}
