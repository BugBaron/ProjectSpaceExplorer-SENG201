package test;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

import main.Consumable;
import main.Inventory;

@RunWith(JUnitPlatform.class)
@SelectPackages("test.testingFolder")
class AllTests {
	
}
