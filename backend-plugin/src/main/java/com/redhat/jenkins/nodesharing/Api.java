/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.redhat.jenkins.nodesharing;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.model.RootAction;
import jenkins.model.Jenkins;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

import javax.annotation.Nonnull;

/**
 * Receive and send REST commands from/to executor Jenkinses.
 */
@Extension
@Restricted(NoExternalUse.class)
public class Api implements RootAction {

    public static final String HIDDEN = null;

    public static @Nonnull Api getInstance() {
        ExtensionList<Api> list = Jenkins.getInstance().getExtensionList(Api.class);
        assert list.size() == 1;
        return list.iterator().next();
    }

    @Override public String getIconFileName() {
        return HIDDEN;
    }

    @Override public String getDisplayName() {
        return HIDDEN;
    }

    @Override public String getUrlName() {
        return "node-sharing";
    }

    //// Outgoing

    /**
     * Signal to executor Jenkins to start using particular node.
     */
    public void utilizeNode() {

    }

    //// Incomming
}
