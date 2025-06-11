package jkt.pls.model.request;
// Updated indentation to use tabs

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // @Setter 테스트용
public class InsertRequest {
	
	private Long documentId;
	
	// 추후구현
	// private String[] tag;
	
	private String content;
}
