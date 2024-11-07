package com.nutrisense.web.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        page.navigate("https://members-staging.nutrisense.io/video-calls");
        page.waitForLoadState();
    }

    public void clickButtonByName(String name) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(name)).click();
    }

    public void assertUrl(String url) {
        PlaywrightAssertions.assertThat(page).hasURL(url);
    }
}
