package com.xcesys.extras.framework.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.xcesys.extras.framework.dao.model.IdTreeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Category generated by hbm2java
 */
//@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@Setter
public class Category extends IdTreeEntity<Category> implements java.io.Serializable {

	private static final long serialVersionUID = 8893384987419159896L;
	private String code;
	private String description;
	private Boolean editable = Boolean.TRUE;
}