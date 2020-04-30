package cn.jj.pojo;

import java.io.Serializable;

public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String expression;
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
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public Permission(Integer id, String name, String expression) {
		super();
		this.id = id;
		this.name = name;
		this.expression = expression;
	}
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
