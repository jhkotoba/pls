package jkt.pls.model.entity;
// Updated indentation to use tabs

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
	private String searchId;
	
	@Column("DOCUMENT_ID")
	private String documentId; 
	
	@Column("SECTION")
	private String section; 
	
	@Column("TOKEN")
	private String token; 
	
}
