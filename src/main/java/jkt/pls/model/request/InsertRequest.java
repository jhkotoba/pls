package jkt.pls.model.request;

import lombok.Getter;

@Getter
public class InsertRequest {
	
	private String documentId;
	
	private String[] tag;
	
	private String content;
}
