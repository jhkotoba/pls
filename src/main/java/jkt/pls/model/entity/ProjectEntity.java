package jkt.pls.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Table("PROJECT")
@ToString
public class ProjectEntity implements Persistable<String> {

	@Transient
    private boolean newFlag = false;
	
	@Id
	@Column("PROJECT_ID")
	private String projectId;
	
	@Column("PROJECT_NAME")
	private String projectName;
	
	@Column("DESCRIPTION")
	private String description;

	@Override
	public String getId() {
		return this.projectId;
	}

	@Override
	public boolean isNew() {		
		return this.newFlag;
	}
	
	@PersistenceCreator
    public ProjectEntity(String projectId, String projectName, String description) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.newFlag = false;
    }
	
	@Builder
    public ProjectEntity(String projectId, String projectName, String description, boolean newFlag) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.newFlag = newFlag;
    }
	
}
