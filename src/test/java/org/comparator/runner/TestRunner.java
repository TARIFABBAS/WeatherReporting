package org.comparator.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/features", glue = "src/test/org/comparator/steps", plugin = {"pretty", "html:target/cucumber-reports"}, monochrome = true)
class RunCucumberTest extends AbstractTestNGCucumberTests {
    @Test
    public void test() {
        Assert.assertEquals("First Line>\nSecond Line", "Third Line\nFourth Line");
    }

}