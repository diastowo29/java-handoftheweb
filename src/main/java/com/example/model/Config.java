package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config implements Serializable {

	private static final long serialVersionUID = 8814785481048416620L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "domain")
	private String domain;

	@Column(name = "config")
	private String config;
	
	@Column(name = "duration")
	private String duration;

	@Column(name = "title")
	private String title;
	
	public long getId () {
		return id;
	}
	
	public String getDomain () {
		return domain;
	}
	
	public String getConfig () {
		return config;
	}
	
	public String getDuration () {
		return duration;
	}
	
	public String getTitle () {
		return title;
	}

	protected Config() {
	}

	public Config(String domain, String config, String duration, String title) {
		this.domain = domain;
		this.config = config;
		this.duration = duration;
		this.title = title;
	}
}
