package com.xcesys.extras.framework.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.xcesys.extras.framework.dao.model.IdAuditableEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Media generated by hbm2java
 */
//@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@Setter
public class Media extends IdAuditableEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2028710753518893177L;
	private String author;
	private String dimensions;
	private String filename;
	private Long fsize;
	private Long id;
	private String legend;
	private String license;
	private String mime;
	private Long folder;

}
