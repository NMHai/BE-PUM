package v2.org.analysis.apihandle.generation;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class TemplateUtil {
	public static Template getTemplate(String name) {
		Template template = null;
		try {
			template = Velocity.getTemplate(name);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			// couldn't find the template
		} catch (ParseErrorException pee) {
			pee.printStackTrace();
			// syntax error: problem parsing the template
		} catch (MethodInvocationException mie) {
			mie.printStackTrace();
			// something invoked in the template
			// threw an exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}
}
