package com.nutrisense.web.steps;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.nutrisense.web.config.PlaywrightConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final String BROWSER = System.getProperty("browser", "chrome"); // Default to Chromium
    public static Page page;

    @Before
    public void setUp() {
        PlaywrightConfig.init(BROWSER);
        page = PlaywrightConfig.getPage(BROWSER);
        PlaywrightConfig.enableTracing(BROWSER, true);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }
        PlaywrightConfig.stopTracing(BROWSER, "trace-" + BROWSER + ".zip");
        PlaywrightConfig.close(BROWSER);
    }

    private void captureScreenshot(Scenario scenario) {
        Path screenshotPath = Paths.get("screenshots", scenario.getName() + "-" + BROWSER + ".png");
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));
        
        // Attach the screenshot to the Cucumber report
        scenario.attach(screenshot, "image/png", scenario.getName() + " - Screenshot");
    }
}
