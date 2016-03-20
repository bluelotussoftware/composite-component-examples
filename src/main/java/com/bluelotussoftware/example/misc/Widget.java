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
package com.bluelotussoftware.example.misc;

import java.io.Serializable;

/**
 *
 * @author John Yeary <john.yeary@infor.com>
 * @version 1.0
 */
public class Widget implements Serializable {

    private static final long serialVersionUID = -8326160946885131439L;

    private String name;
    private String type;
    private String notes;

    public Widget() {
    }

    public Widget(String name, String type, String notes) {
        this.name = name;
        this.type = type;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Widget{" + "name=" + name + ", type=" + type + ", notes=" + notes + '}';
    }

}
