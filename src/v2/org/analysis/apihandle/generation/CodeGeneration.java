/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.generation
 * File name: CodeGeneration.java
 * Created date: Oct 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.generation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import v2.org.analysis.apihandle.generation.stub.Function;
import v2.org.analysis.apihandle.generation.stub.Variable;
import v2.org.analysis.apihandle.winapi.structures.GeneratedStruct;
import v2.org.analysis.util.SystemUtil;

import com.sun.jna.IntegerType;
import com.sun.jna.PointerType;

/**
 * @author Yen Nguyen
 *
 */
public class CodeGeneration {

	private static final String POINTER = "com.sun.jna.Pointer";

	/**
	 * Generate source code for a new structure
	 * 
	 * @param structure
	 *            Structure declaration
	 * 
	 * @return Source code for structure input
	 */
	public static String GenerateStruct(Function structure) {
		String newStruct = null;
		StringWriter w = new StringWriter();
		Template template = null;

		/* lets make a Context and put data into it */

		VelocityContext context = new VelocityContext();
		context.put("func", structure);

		/* lets render a template */
		template = TemplateUtil.getTemplate("StructDeclaration.vm");
		template.merge(context, w);

		// Get the declaration string for current structure
		newStruct = w.toString();

		// Reset buffer
		w.getBuffer().setLength(0);

		context.put("newStruct", newStruct);

		String path = "src/" + GeneratedStruct.class.getPackage().getName().replace(".", "/") + "/GeneratedStruct.java";
		File source = new File(path);

		try {
			SystemUtil.copyFileUsingStream(source, new File(Velocity.getProperty("file.resource.loader.path")
					.toString() + "GeneratedStruct.vm"));

			Velocity.mergeTemplate("GeneratedStruct.vm", "UTF-8", context, w);

			FileWriter fileWriter = new FileWriter(source.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(w.toString());
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return w.toString();
	}

	static String GenAPI(Function func) {
		StringWriter w = new StringWriter();
		Template template = null;

		/* lets make a Context and put data into it */

		VelocityContext context = new VelocityContext();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		Date now = new Date();
		context.put("date", dateFormat.format(now));

		func.importList = new Vector<String>();
		for (Variable v : func.paramsList) {
			v.initStr = GenerateInitizlize(v, func);
			func.importList.add(v.type.fullClassName);
		}

		context.put("func", func);

		/* lets render a template */

		template = TemplateUtil.getTemplate("NewAPIClass.vm");
		template.merge(context, w);

		return w.toString();
	}

	private static String GenerateInitizlize(Variable variable, Function func) {
		if (variable.type == null) {
			return null;
		}

		Template template = null;
		VelocityContext context = new VelocityContext();
		context.put("v", variable);

		if (variable.type.isPrimirateType()) {
			template = TemplateUtil.getTemplate("initialize/CastValue.vm");
		} else {
			Class<?> clazz = variable.type.getTypeClass();

			if (clazz != null) {
				if (CharSequence.class.isAssignableFrom(clazz)) {
					// System.out.println("CharSequence");
				} else if (PointerType.class.isAssignableFrom(clazz)) {
					// System.out.println("Pointer");
					// Import pointer if not exist
					if (!func.importList.contains(POINTER)) {
						func.importList.add("com.sun.jna.Pointer");
					}
					template = TemplateUtil.getTemplate("initialize/PointerInside.vm");
				} else if (IntegerType.class.isAssignableFrom(clazz)) {
					// System.out.println("Number");
					template = TemplateUtil.getTemplate("initialize/NumberInside.vm");
				}
			}
		}

		if (template != null) {
			StringWriter w = new StringWriter();
			template.merge(context, w);
			return w.toString();
		}

		return null;
	}
}
