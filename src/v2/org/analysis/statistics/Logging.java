package v2.org.analysis.statistics;

import java.io.PrintStream;

public class Logging {
	public enum Level {
		FATAL, ERROR, WARN, INFO, VERBOSE, DEBUG
	}

	private int debugLevel = Level.DEBUG.ordinal();
	private static String globalPrefix = "";
	// set debug show class = true
	private static boolean showClass = false;

	public static Logging getLogger(Class<? extends Object> c) {
		return new Logging(c, System.out);
	}

	public static void setGlobalPrefix(String prefix) {
		globalPrefix = prefix + "\t";
	}

	private PrintStream out;
	private String prefix;

	private Logging(Class<? extends Object> clazz, PrintStream outStream) {
		this.out = outStream;
		this.prefix = (showClass ? (clazz.getSimpleName() + ":\t") : "");
	}

	public void setDebugLevel(int level) {
		debugLevel = level;
	}

	private int getDebugLevel() {
		return debugLevel;
	}

	public boolean isDebugEnabled() {
		return Level.DEBUG.ordinal() <= getDebugLevel();
	}

	public boolean isVerboseEnabled() {
		return Level.VERBOSE.ordinal() <= getDebugLevel();
	}

	public boolean isInfoEnabled() {
		return Level.INFO.ordinal() <= getDebugLevel();
	}

	public void log(Level level) {
		if (level.ordinal() >= getDebugLevel())
			out.println();
	}

	public void log(Level level, Object message) {
		if (level.ordinal() <= getDebugLevel())
			out.println(globalPrefix + prefix + message);
	}

	public void log(Level level, Object message, Throwable t) {
		if (level.ordinal() <= getDebugLevel())
			out.println(globalPrefix + prefix + message + " " + t.getMessage());
	}

	public void logString(Level level, String string) {
		if (level.ordinal() <= getDebugLevel())
			out.print(globalPrefix + prefix + string);
	}

	public void debug() {
		log(Level.DEBUG);
	}

	public void debug(Object message) {
		log(Level.DEBUG, message);
	}

	public void debug(Object message, Throwable t) {
		log(Level.DEBUG, message, t);
	}

	public void debugString(String message) {
		logString(Level.DEBUG, message);
	}

	public void verbose() {
		log(Level.VERBOSE);
	}

	public void verbose(Object message) {
		log(Level.VERBOSE, message);
	}

	public void verbose(Object message, Throwable t) {
		log(Level.VERBOSE, message, t);
	}

	public void verboseString(String message) {
		logString(Level.VERBOSE, message);
	}

	public void info() {
		log(Level.INFO);
	}

	public void info(Object message) {
		log(Level.INFO, message);
	}

	public void infoString(String message) {
		logString(Level.INFO, message);
	}

	public void info(Object message, Throwable t) {
		log(Level.INFO, message, t);
	}

	public void warn(Object message) {
		log(Level.WARN, message);
	}

	public void warn(Object message, Throwable t) {
		log(Level.WARN, message, t);
	}

	public void error(Object message) {
		log(Level.ERROR, message);
	}

	public void error(Object message, Throwable t) {
		log(Level.ERROR, message, t);
	}

	public void fatal(Object message) {
		log(Level.FATAL, message);
	}

	public void fatal(Object message, Throwable t) {
		log(Level.FATAL, message, t);
	}
}
