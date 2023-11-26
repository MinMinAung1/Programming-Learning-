package com.hostmdy.programming_learning.domain;

import java.time.LocalDate;
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
public class PjVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Lob
	private Byte[] videoData;

	private LocalDate createAt;
	private LocalDate updateAt;

	// join with course
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "project_id")
	private Project project;

	public PjVideo() {
	}

	public PjVideo(String name, Byte[] videoData, Project project) {
		super();
		this.name = name;
		this.videoData = videoData;
		this.project = project;
	}

	@PrePersist
	void onCreate() {
		this.createAt = LocalDate.now();

	}

	@PreUpdate
	void onUpdate() {
		this.updateAt = LocalDate.now();
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

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "PjVideo [id=" + id + ", name=" + name + ", videoData=" + Arrays.toString(videoData) + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", project=" + project + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(videoData);
		result = prime * result + Objects.hash(createAt, id, name, project, updateAt);
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
		PjVideo other = (PjVideo) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(project, other.project)
				&& Objects.equals(updateAt, other.updateAt) && Arrays.equals(videoData, other.videoData);
	}

}
