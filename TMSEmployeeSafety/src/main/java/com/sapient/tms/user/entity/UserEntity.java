package com.sapient.tms.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_entity")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String userName;

	private String roleId;

	private String encryptedPassword;

	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	/*
	 * @ElementCollection(targetClass=RouteEntity.class)
	 * 
	 * @ManyToMany(targetEntity=UserEntity.class, mappedBy="id",
	 * fetch=FetchType.EAGER) private List<RouteEntity> routes; public String
	 * getRoleId() { return roleId; }
	 */
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable( name = "route_user", joinColumns = {@JoinColumn(name =
	 * "username")}, inverseJoinColumns = {@JoinColumn(name = "id")} ) private
	 * List<RouteEntity> routes = new ArrayList<>();
	 * 
	 * public UserEntity(String name, List<RouteEntity> routes) { this.name = name;
	 * this.routes = routes; }
	 */
	/*
	 * public List<RouteEntity> getRoutes() { return routes; }
	 * 
	 * public void setRoutes(List<RouteEntity> routes) { this.routes = routes; }
	 */

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
