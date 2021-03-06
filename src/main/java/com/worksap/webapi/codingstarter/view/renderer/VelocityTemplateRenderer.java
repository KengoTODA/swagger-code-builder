/*
 *   Copyright 2016 Works Applications Co.,Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.worksap.webapi.codingstarter.view.renderer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * TODO: Write Javadoc
 */
public class VelocityTemplateRenderer {
    private final VelocityEngine velocityEngine;
    private final VelocityContext velocityContext;

    public VelocityTemplateRenderer(VelocityEngine velocityEngine, VelocityContext velocityContext) {
        this.velocityEngine = velocityEngine;
        this.velocityContext = velocityContext;
    }

    public void writeFile(Path newFilePath, String templateName, Object params) throws IOException {
        Template template = velocityEngine.getTemplate(templateName);

        if (params != null) {
            velocityContext.put("params", params);
        }

        try (Writer writer = Files.newBufferedWriter(newFilePath)) {
            template.merge(velocityContext, writer);
        }
    }
}
