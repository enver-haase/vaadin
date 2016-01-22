/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.tb3;

import org.openqa.selenium.Platform;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.shared.Version;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.DefaultBrowserFactory;

public class SauceLabsBrowserFactory extends DefaultBrowserFactory {

    @Override
    public DesiredCapabilities create(Browser browser) {
        String version = "";
        if (Browser.FIREFOX.equals(browser)) {
            version = "42";
        } else if (Browser.CHROME.equals(browser)) {
            // SauceLabs tends to be a bit behind with latest Chrome versions
            version = "46";
        }
        return create(browser, version, Platform.ANY);
    }

    @Override
    public DesiredCapabilities create(Browser browser, String version,
            Platform platform) {
        DesiredCapabilities caps;

        switch (browser) {
        case CHROME:
            caps = DesiredCapabilities.chrome();
            caps.setVersion(version);
            break;
        case PHANTOMJS:
            caps = DesiredCapabilities.phantomjs();
            caps.setVersion(version);
            caps.setPlatform(platform);
            break;
        case SAFARI:
            caps = DesiredCapabilities.safari();
            caps.setVersion(version);
            break;
        case IE8:
            caps = DesiredCapabilities.internetExplorer();
            caps.setVersion("8.0");
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
                    true);
            break;
        case IE9:
            caps = DesiredCapabilities.internetExplorer();
            caps.setVersion("9.0");
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
                    true);
            break;
        case IE10:
            caps = DesiredCapabilities.internetExplorer();
            caps.setVersion("10.0");
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
                    true);
            break;
        case IE11:
            caps = DesiredCapabilities.internetExplorer();
            caps.setVersion("11.0");
            // There are 2 capabilities ie.ensureCleanSession and
            // ensureCleanSession in Selenium
            // IE 11 uses ie.ensureCleanSession
            caps.setCapability("ie.ensureCleanSession", true);
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
                    true);
            break;
        case FIREFOX:
            caps = DesiredCapabilities.firefox();
            caps.setVersion(version);
            break;
        default:
            caps = DesiredCapabilities.firefox();
            caps.setVersion(version);
            caps.setPlatform(platform);
        }

        if (!Browser.PHANTOMJS.equals(browser)) {
            caps.setCapability("platform", "Windows 7");
        }

        // accept self-signed certificates
        caps.setCapability("acceptSslCerts", "true");

        // SauceLabs specific parts

        caps.setCapability("screenResolution", "1680x1050");

        // build and project for easy identification in SauceLabs UI
        // TODO check if these are correct for SauceLabs
        caps.setCapability("project", "vaadin");
        caps.setCapability("build", Version.getFullVersion());

        return caps;
    }

}
