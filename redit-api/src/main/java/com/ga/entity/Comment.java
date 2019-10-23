package com.ga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="comments")
public class Comment {
	

		@Id
		@Column(name = "comment_id", unique = true, nullable = false)
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long commentId;
		
		@Column(nullable = false)
		private String text;
		
}
