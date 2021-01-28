package edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.form.SubjectForm;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subject_id")
	private long id;

	@Column(name = "name")
	@NotNull(message = "*Please provide a name")
	private String name;

	@Column(name = "time")
	@NotNull(message = "*Please provide a time")
	private String time;

	@ManyToOne
	private Teacher teacher;

	@Column(name = "max")
	private Integer max;

	@Column(name = "active")
	private int active;

	public Subject() {

	}

	public Subject(SubjectForm form) {
		this.id = form.getId();
		this.name = form.getName();
		this.teacher = form.getTeacher();
		this.active = form.getActive();
		this.max = form.getMax();
		this.time = form.getTime();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
