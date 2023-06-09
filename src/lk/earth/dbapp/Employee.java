package lk.earth.dbapp;

import java.beans.DesignMode;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import javafx.animation.Animation.Status;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Patern(regexp = "^[A-Z][a-z]*$")
	public String name;

	@Patern(regexp = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$")
	@Column(name = "nic", columnDefinition = "char")
	public String nic;

	@Patern(regexp = "^[a-z]*@[a-z]*.[a-z]*$")
	public String email;

	@Patern(regexp = "^0[0-9]{9}$")
	@Column(name = "mobile", columnDefinition = "char")
	public String mobile;

	@Column(name = "dob", columnDefinition = "date")
	private LocalDate dob;

	@JoinColumn(name = "gender_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Gender gender;

	@JoinColumn(name = "designation_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Designation designation;

	@JoinColumn(name = "statusemployee_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Statusemployee statusemployee;

	protected Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Statusemployee getStatusemployee() {
		return statusemployee;
	}

	public void setStatusemployee(Statusemployee statusemployee) {
		this.statusemployee = statusemployee;
	}
}
