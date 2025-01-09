/*
 * Copyright 2014-2025 Blue Lotus Software, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotussoftware.example.jsf.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlCommandButton;

/**
 * Customized composite component {@link HtmlCommandButton}.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.1
 */
@FacesComponent("homeButton")
public class HomeButton extends HtmlCommandButton implements NamingContainer {

    public HomeButton() {
    }

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    public String homeAction() {
        System.out.println("homeAction() called, returning to index.xhtml");
        return "index";
    }

    public String getButtonName() {
        return "Home";
    }

    public String getJS() {
        return "alert('Going Home');";
    }
}
