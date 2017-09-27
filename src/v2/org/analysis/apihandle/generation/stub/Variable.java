package v2.org.analysis.apihandle.generation.stub;

public class Variable {
	public Type type;
	public String name;
	public String desc;
	
	public int order;
	public String initStr;

	public Variable(Type type, String name) {
		this(type, name, null);
	}

	public Variable(Type type, String name, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
	}

	public Variable(Type type, String name, int order) {
		this.type = type;
		this.name = name;
		this.order = order;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getInitStr() {
		return initStr;
	}

	public void setInitStr(String initStr) {
		this.initStr = initStr;
	}
}
