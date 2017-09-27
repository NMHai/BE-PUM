/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.generation
 * File name: GenTest.java
 * Created date: Oct 19, 2015
 * Description:
 */
package v2.org.analysis.apihandle.generation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Properties;

import org.apache.velocity.app.Velocity;

import v2.org.analysis.apihandle.generation.stub.Function;
import v2.org.analysis.apihandle.generation.stub.ParamsList;
import v2.org.analysis.apihandle.generation.stub.Type;
import v2.org.analysis.apihandle.generation.stub.Variable;

import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * @author Yen Nguyen
 *
 */
public class GenTest {

	static <T> void inspect(Class<T> klazz) {
		Field[] fields = klazz.getDeclaredFields();
		System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			if (field.getModifiers() == Modifier.PUBLIC) {
				System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getName(),
						field.getName());
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		/* first, we init the runtime engine. */
		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", "data/templates/");
		Velocity.init(p);
		
		HANDLE a;
		
		 System.out.println(GenAPI());

		// System.out.println(TypeMap.getInstance().getTypeMapping("WORD"));
	}

	static String GenAPI() {
		Function func = new Function();
		func.funcName = "IsValidAcl";
		func.libName = "kernel32";
		func.description = "The IsValidAcl function validates an access control list (ACL).";
		ParamsList paramsList = new ParamsList();

		Variable v1 = new Variable(
				TypeMap.getInstance().getTypeMapping("LPVOID"),
				"nBufferLength",
				"The length of the buffer for the current directory string, in TCHARs. The buffer length must include room for a terminating null character.");
		paramsList.add(v1);

		paramsList
				.add(new Variable(
						TypeMap.getInstance().getTypeMapping("HANDLE"),
						"lpBuffer",
						"A pointer to the buffer that receives the current directory string. This null-terminated string specifies the absolute path to the current directory. To determine the required buffer size, set this parameter to NULL and the nBufferLength parameter to 0."));
		func.paramsList = paramsList;

		func.ret = new Variable(
				new Type("DWORD"),
				null,
				"If the function succeeds, the return value specifies the number of characters that are written to the buffer, not including the terminating null character.");


		return CodeGeneration.GenAPI(func);
	}

	static String GenStruct() {
		Function struct = new Function();
		struct.funcName = "IsValidAcl";

		ParamsList paramsList = new ParamsList();
		paramsList.add(new Variable(new Type("int"), "nBufferLength"));
		paramsList.add(new Variable(new Type("char[]"), "lpBuffer"));
		struct.paramsList = paramsList;

		return CodeGeneration.GenerateStruct(struct);
	}
}
