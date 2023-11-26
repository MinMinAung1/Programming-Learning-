package com.hostmdy.programming_learning.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Lob
	private Byte[] videoData;

	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	// join with course
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "course_id")
	private Course course;
	
	public Video() {
	}

	public Video(String name, Byte[] videoData, Course course) {
		super();
		this.name = name;
		this.videoData = videoData;
		this.course = course;
	}

	@PrePersist
	void onCreate() {
		this.createAt = LocalDateTime.now();

	}

	@PreUpdate
	void onUpdate() {
		this.updateAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Byte[] getVideoData() {
		return videoData;
	}

	public void setVideoData(Byte[] videoData) {
		this.videoData = videoData;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", videoData=" + Arrays.toString(videoData) + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(videoData);
		result = prime * result + Objects.hash(createAt, id, name, updateAt);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(updateAt, other.updateAt)
				&& Arrays.equals(videoData, other.videoData);
	}

}
