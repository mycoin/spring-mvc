package org.ionnic.common.view.directive;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.directive.Block;
import org.apache.velocity.runtime.parser.node.Node;
import org.ionnic.common.util.TemplateUtils;

/**
 * Directive that puts an unrendered AST block in the context
 * under the specified key, postponing rendering until the
 * reference is used and rendered.
 *
 * @author Andrew Tetlaw
 * @author Nathan Bubna
 * @version $Id: Define.java 686842 2008-08-18 18:29:31Z nbubna $
 */
public class BlockDirective extends Block {

	/**
	 * Return name of this directive.
	 */
	@Override
	public String getName() {
		return "block";
	}

	/**
	 * directive.render() simply makes an instance of the Block inner class
	 * and places it into the context as indicated.
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) {
		key = TemplateUtils.getFirstArg(node);
		maxDepth = 5;

		if (TemplateUtils.isRenderingLayout(context)) {
			Reference ref = (Reference) context.get(key);
			if (ref == null) {
				render(context, writer);
			} else {
				ref.render(context, writer);
			}
		} else {
			context.put(key, new Reference(context, this));
		}
		return true;
	}
}