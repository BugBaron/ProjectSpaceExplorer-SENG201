/**
 * @author Daniel Harris
 *
 */
module SpaceExplorer {
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	requires java.desktop;
	requires org.junit.platform.suite.api;
	requires org.junit.platform.runner;
	requires junit;
	
	exports test.testingFolder;
	opens test.testingFolder;
}