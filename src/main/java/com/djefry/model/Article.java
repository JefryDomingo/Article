package com.djefry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "article", catalog = "articles")
public class Article {

	Integer id;
	String name;
	String type;
	String numpage;
	
	public Article() {
		
	}
	@Id
	@Column(name = "id", unique = true, nullable =false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable =false, length=25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable =false, length=25)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "numpage", nullable =false, length=25)
	public String getNumpage() {
		return numpage;
	}

	public void setNumpage(String numpage) {
		this.numpage = numpage;
	}
	
	
}
