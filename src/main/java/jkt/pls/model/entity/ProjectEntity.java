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
@Table("PROJECT")
public class ProjectEntity {

	@Id
	@Column("PROJECT_ID")
	private String projectId;
	
	@Column("PROJECT_NAME")
	private String projectName;
	
	@Column("DESCRIPTION")
	private String description; 
	
}
