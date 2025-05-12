package jkt.pls.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table("SEARCH")
public class SearchEntity {

	@Id
	@Column("SEARCH_ID")
	private Long searchId;
	
	@Column("DOCUMENT_ID")
	private Long documentId; 
	
	@Column("SECTION")
	private String section; 
	
	@Column("TOKEN")
	private String token; 
	
	@Column("POSITION")	
	private String position; 
	
	@Column("TITLE")
	private String title; 
	
	@Column("SAMPLE_CONTENT")
	private String sampleContent;
	
}
