package edu.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.entity.Teacher;

public class SubjectForm {

	private long id;

	@NotNull
	@Size(min = 2, max = 50)
	private String name;

	@NotNull
	@Size(min = 5, max = 5)
	private String time;

	private Long teacherId;

	private Teacher teacher;

	@NotNull
	private Integer max;

	private Integer active;

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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
