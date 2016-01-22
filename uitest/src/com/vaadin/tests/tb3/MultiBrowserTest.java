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

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.parallel.Browser;

/**
 * Base class for tests which should be run on all supported browsers. The test
 * is automatically launched for multiple browsers in parallel by the test
 * runner.
 * 
 * Sub classes can, but typically should not, restrict the browsers used by
 * implementing a
 * 
 * <pre>
 * &#064;Parameters
 * public static Collection&lt;DesiredCapabilities&gt; getBrowsersForTest() {
 * }
 * </pre>
 * 
 * @author Vaadin Ltd
 */
public abstract class MultiBrowserTest extends PrivateTB3Configuration {

    protected List<DesiredCapabilities> getBrowsersSupportingWebSocket() {
        // No WebSocket support in IE8-9 and PhantomJS
        return getBrowserCapabilities(Browser.IE10, Browser.IE11,
                Browser.FIREFOX, Browser.CHROME);
    }

    protected List<DesiredCapabilities> getBrowsersExcludingPhantomJS() {
        return getBrowserCapabilities(Browser.IE8, Browser.IE9, Browser.IE10,
                Browser.IE11, Browser.CHROME, Browser.FIREFOX);
    }

    protected List<DesiredCapabilities> getBrowsersExcludingIE() {
        return getBrowserCapabilities(Browser.FIREFOX, Browser.CHROME);
    }

    protected List<DesiredCapabilities> getBrowsersExcludingIE8() {
        return getBrowserCapabilities(Browser.IE9, Browser.IE10, Browser.IE11,
                Browser.FIREFOX, Browser.CHROME);
    }

    protected List<DesiredCapabilities> getBrowsersSupportingShiftClick() {
        return getBrowserCapabilities(Browser.IE8, Browser.IE9, Browser.IE10,
                Browser.IE11, Browser.CHROME);
    }

    protected List<DesiredCapabilities> getIEBrowsersOnly() {
        return getBrowserCapabilities(Browser.IE8, Browser.IE9, Browser.IE10,
                Browser.IE11);
    }

    protected List<DesiredCapabilities> getBrowsersSupportingContextMenu() {
        // context menu doesn't work in phantom JS and works weirdly with IE8
        // and selenium.
        return getBrowserCapabilities(Browser.IE9, Browser.IE10, Browser.IE11,
                Browser.FIREFOX, Browser.CHROME);
    }

    @Override
    public List<DesiredCapabilities> getBrowsersToTest() {
        // Uncomment Safari and Opera if those become tested browsers again.
        return getBrowserCapabilities(Browser.IE8, Browser.IE9, Browser.IE10,
                Browser.IE11, Browser.FIREFOX, Browser.CHROME
        /* , Browser.PHANTOMJS, Browser.SAFARI, Browser.OPERA */);
    }

    protected List<DesiredCapabilities> getBrowserCapabilities(
            Browser... browsers) {
        List<DesiredCapabilities> capabilities = new ArrayList<DesiredCapabilities>();
        for (Browser browser : browsers) {
            capabilities.add(browser.getDesiredCapabilities());
        }
        return capabilities;
    }
}
