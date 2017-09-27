package v2.org.analysis.apihandle.generation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import v2.org.analysis.apihandle.generation.stub.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class TypeMap {
	private static TypeMap instance = null;
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static synchronized TypeMap getInstance() {
		if (instance == null) {
			instance = new TypeMap();
		}

		return instance;
	}

	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(boolean.class);
		ret.add(Character.class);
		ret.add(char.class);
		ret.add(Byte.class);
		ret.add(byte.class);
		ret.add(Short.class);
		ret.add(short.class);
		ret.add(Integer.class);
		ret.add(int.class);
		ret.add(Long.class);
		ret.add(long.class);
		ret.add(Float.class);
		ret.add(float.class);
		ret.add(Double.class);
		ret.add(double.class);
		ret.add(Void.class);
		ret.add(void.class);
		return ret;
	}

	private HashMap<String, Type> typeMap = new HashMap<>();

	public TypeMap() {
		String path = "/" + TypeMap.class.getPackage().getName().replace(".", "/") + "/TypeMap.json";
		InputStream stream = TypeMap.class.getResourceAsStream(path);

		Gson gson = new Gson();
		List<Type> typeList = gson.fromJson(new InputStreamReader(stream), (new TypeToken<List<Type>>() {
		}).getType());
		for (Type type : typeList) {
			typeMap.put(type.from, type);
		}
	}

	public Type getTypeMapping(String oldType) {
		Type type = this.typeMap.get(oldType);
		return type;
	}

	public Type getTypeMapping(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}

		if (WRAPPER_TYPES.contains(clazz)) {
			// Don't insert full class name if this class belongs to primitive type
			return new Type(clazz.getSimpleName(), clazz.getSimpleName());
		} else {
			return new Type(clazz.getSimpleName(), clazz.getSimpleName(), clazz.getName());
		}
	}
}
