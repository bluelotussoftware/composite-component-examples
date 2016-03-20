/*
 * Copyright 2014-2016 Blue Lotus Software, LLC.
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
package com.bluelotussoftware.example.jsf;

import com.bluelotussoftware.example.misc.Widget;
import static com.bluelotussoftware.jsf.utils.JSFUtils.includeCompositeComponent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIColumn;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class Bean implements Serializable {

    private static final long serialVersionUID = -6070027895086601136L;

    private List<String> names;
    private final List<Widget> widgets = new ArrayList<>();
    private HtmlPanelGroup panel, panelGroup1;
    private UIColumn[] columns;
    private HtmlDataTable table;
    private String src = "example1";

    public Bean() {
        panel = (HtmlPanelGroup) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlPanelGroup.COMPONENT_TYPE);
        panelGroup1 = (HtmlPanelGroup) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlPanelGroup.COMPONENT_TYPE);
        table = (HtmlDataTable) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlDataTable.COMPONENT_TYPE);

        names = new ArrayList<>();
        names.add("John");
        names.add("Patty");
        names.add("Ethan");
        names.add("Sean");
        names.add("Java");

        widgets.add(new Widget("A", "Animal", "This is A"));
        widgets.add(new Widget("B", "Vegetable", "This is B"));
        widgets.add(new Widget("C", "Mineral", "This is C"));
        widgets.add(new Widget("D", "Spiritual", "This is D"));
        widgets.add(new Widget("E", "Vitamin", "This is E"));
        widgets.add(new Widget("V", "Virus", "This is a virus"));

        columns = new UIColumn[widgets.size()];
        for (int i = 0; i < widgets.size(); i++) {
            columns[i] = (HtmlColumn) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlColumn.COMPONENT_TYPE);
        }

        for (int i = 0; i < columns.length; i++) {
            HtmlOutputText columnHeaderName = (HtmlOutputText) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlOutputText.COMPONENT_TYPE);
            columnHeaderName.setValue("column_" + i);
            columns[i].setHeader(columnHeaderName);
            Map<String, Object> m = new HashMap<>();
            ValueExpression ve
                    = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{data}", Object.class);
            m.put("value", ve);
            includeCompositeComponent(columns[i], "http://xmlns.jcp.org/jsf/composite/ezcomp", "out", "alpha_" + i, m);
        }
        ValueExpression data
                = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{data}", Object.class);
        table.setValue(widgets);
        table.setVar("data");
        table.getChildren().addAll(Arrays.asList(columns));

    }

    @PostConstruct
    private void init() {
        HtmlForm form = (HtmlForm) FacesContext.getCurrentInstance().getApplication().createComponent(HtmlForm.COMPONENT_TYPE);
        includeCompositeComponent(form, "http://xmlns.jcp.org/jsf/composite/ezcomp", "home", "beta");
        panelGroup1.getChildren().add(form);
    }

    public UIColumn[] getColumns() {
        return columns;
    }

    public void setColumns(UIColumn[] columns) {
        this.columns = columns;
    }

    public HtmlDataTable getTable() {
        return table;
    }

    public void setTable(HtmlDataTable table) {
        this.table = table;
    }

    public HtmlPanelGroup getPanel() {
        return panel;
    }

    public void setPanel(HtmlPanelGroup panel) {
        this.panel = panel;
    }

    public HtmlPanelGroup getPanelGroup1() {
        return panelGroup1;
    }

    public void setPanelGroup1(HtmlPanelGroup panelGroup1) {
        this.panelGroup1 = panelGroup1;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void sayHello() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        String name = params.get("name_");
        httpServletResponse.addCookie(new Cookie("_cookieName", name));
        System.out.println("Hello " + name);
    }

    public String getSrc() {
        return src;
    }

    public void update(String source) {
        this.src = source;
    }

}
