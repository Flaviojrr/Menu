package model;

public class JavaBeans {
	private String id;
	private String name;
	private String ingredients;
	private String type;
	
	public JavaBeans() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JavaBeans(String id, String name, String ingredients, String type) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.type = type;
	}

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
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

