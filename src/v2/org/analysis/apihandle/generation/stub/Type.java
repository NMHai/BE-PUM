package v2.org.analysis.apihandle.generation.stub;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import v2.org.analysis.apihandle.generation.TypeMap;

import com.sun.jna.Structure;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class Type {
	public String from;
	public String to;
	public String fullClassName = null;

	private Class<?> clazz = null;
	private List<Variable> childAttributeList = null;

	public Type() {
	}

	public Type(String type) {
		this(type, type, null);
	}

	public Type(String from, String to) {
		this(from, to, null);
	}

	public Type(String from, String to, String fullClassName) {
		this.from = from;
		this.to = to;
		this.fullClassName = fullClassName;
	}

	@Override
	public String toString() {
		return this.to;// + '|' + this.fullClassName;
	}

	public boolean isPrimirateType() {
		if (this.fullClassName != null) {
			return false;
		}

		if (this.to.equals("String")) {
			return false;
		}

		return true;
	}

	public synchronized Class<?> getTypeClass() {
		if (this.fullClassName == null) {
			return null;
		}

		if (this.clazz == null) {
			String fClassName = this.fullClassName;

			Class<?> clazz = null;
			try {
				clazz = Class.forName(fClassName);
			} catch (ClassNotFoundException classNotFoundException) {
				StringBuilder builder = new StringBuilder(fClassName);
				int index = fClassName.lastIndexOf('.');

				builder.setCharAt(index, '$');
				fClassName = builder.toString();

				try {
					clazz = Class.forName(fClassName);
				} catch (ClassNotFoundException ex) {
					clazz = null;
				}
			}

			this.clazz = clazz;
		}

		return this.clazz;
	}

	public synchronized List<Variable> getChildAttributeList() {
		if (this.childAttributeList != null) {
			return this.childAttributeList;
		}

		Class<?> clazz = this.getTypeClass();

		if (clazz == null) {
			return null;
		}

		if (!Structure.class.isAssignableFrom(clazz)) {
			return null;
		}

		List<Variable> result = new ArrayList<>();
		Map<String, Variable> map = new HashMap<String, Variable>();
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getModifiers() == Modifier.PUBLIC) {
				Variable v = new Variable(TypeMap.getInstance().getTypeMapping(fields[i].getType()),
						fields[i].getName(), i);
				map.put(v.getName(), v);
			}
		}

		try {
			Constructor<?> ctor = clazz.getConstructor();
			Structure object = (Structure) ctor.newInstance();

			Method retrieveItems = clazz.getDeclaredMethod("getFieldOrder");
			retrieveItems.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<String> orderList = (List<String>) retrieveItems.invoke(object);

			for (String field : orderList) {
				result.add(map.get(field));
			}

			// cache
			this.childAttributeList = result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return this.childAttributeList;
	}
}
