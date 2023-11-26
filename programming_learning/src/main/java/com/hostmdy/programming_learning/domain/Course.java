package com.hostmdy.programming_learning.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Lob
	private String description;

	private Double review;

	@Lob
	private Byte[] image;

	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Video> videos = new ArrayList<>();

	public Course() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getReview() {
		return review;
	}

	public void setReview(Double review) {
		this.review = review;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
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

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", review=" + review
				+ ", image=" + Arrays.toString(image) + ", createAt=" + createAt + ", updateAt=" + updateAt
				+ ", videos=" + videos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(createAt, description, id, name, review, updateAt, videos);
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
		Course other = (Course) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Arrays.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(review, other.review) && Objects.equals(updateAt, other.updateAt)
				&& Objects.equals(videos, other.videos);
	}

}
