package com.nutrisense.web.config;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PlaywrightConfig {
    private static Playwright playwright;
    private static Map<String, Browser> browsers = new HashMap<>();
    private static Map<String, BrowserContext> contexts = new HashMap<>();
    private static Map<String, Page> pages = new HashMap<>();

    public static void init(String browserName) {
        playwright = Playwright.create();

        Browser browser;
        BrowserContext context;
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext();
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext();
                break;
            case "iphone":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = createMobileContext(browser, "iPhone 12");
                break;
            case "ipad":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = createMobileContext(browser, "iPad (gen 7)");
                break;
            case "android":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = createMobileContext(browser, "Pixel 5");
                break;
            case "iphone14chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = createMobileContext(browser, "iPhone 14 Chrome");
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1280, 720)
                    .setRecordVideoDir(Paths.get("videos/" + browserName))
                    .setRecordVideoSize(1280, 720)
                );
                break;
        }

        contexts.put(browserName, context);
        Page page = context.newPage();
        pages.put(browserName, page);
    }

    private static BrowserContext createMobileContext(Browser browser, String deviceName) {
        Browser.NewContextOptions options = new Browser.NewContextOptions();

        switch (deviceName.toLowerCase()) {
            case "iphone 12":
                options
                    .setViewportSize(390, 844) // iPhone 12 viewport size
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1")
                    .setDeviceScaleFactor(3)
                    .setIsMobile(true)
                    .setHasTouch(true);
                break;
            case "ipad (gen 7)":
                options
                    .setViewportSize(810, 1080) // iPad (gen 7) viewport size
                    .setUserAgent("Mozilla/5.0 (iPad; CPU OS 13_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0 Mobile/15E148 Safari/604.1")
                    .setDeviceScaleFactor(2)
                    .setIsMobile(true)
                    .setHasTouch(true);
                break;
            case "pixel 5":
                options
                    .setViewportSize(393, 851) // Pixel 5 viewport size
                    .setUserAgent("Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Mobile Safari/537.36")
                    .setDeviceScaleFactor(3)
                    .setIsMobile(true)
                    .setHasTouch(true);
                break;
            case "iphone 14 chrome":
                options
                    .setViewportSize(390, 844) // iPhone 14 viewport size
                    .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/106.0.5249.70 Mobile/15E148 Safari/604.1") // Chrome on iPhone 14
                    .setDeviceScaleFactor(3)
                    .setIsMobile(true)
                    .setHasTouch(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown device: " + deviceName);
        }

        return browser.newContext(options);
    }

    public static Page getPage(String browserName) {
        return pages.get(browserName);
    }

    public static void enableTracing(String browserName, boolean trace) {
        if (trace) {
            contexts.get(browserName).tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
        }
    }

    public static void stopTracing(String browserName, String traceFile) {
        contexts.get(browserName).tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceFile)));
    }

    public static void close(String browserName) {
        contexts.get(browserName).close();
        if (playwright != null) {
            playwright.close();
        }
    }
}
