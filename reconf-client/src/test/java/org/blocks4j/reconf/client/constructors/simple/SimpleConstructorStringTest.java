/*
 *   Copyright 2013-2015 Blocks4J Team (www.blocks4j.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.blocks4j.reconf.client.constructors.simple;

import java.lang.reflect.Method;
import org.blocks4j.reconf.client.constructors.MethodData;
import org.blocks4j.reconf.client.constructors.SimpleConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SimpleConstructorStringTest {

    private MethodData data;
    private Method method;

    @Before
    public void prepare() throws Exception {
        method = SimpleConstructorStringTestTarget.class.getMethod("getString", new Class<?>[]{});
    }

    @Test
    public void test_class() throws Throwable {
        data = new MethodData(method, method.getReturnType(), "'t'");
        Object o = new SimpleConstructor().construct(data);
        Assert.assertTrue(o.getClass().equals(String.class));
    }

    @Test
    public void test_normal_string() throws Throwable {
        data = new MethodData(method, method.getReturnType(), "'String'");
        Object o = new SimpleConstructor().construct(data);
        Assert.assertEquals(new String("String"), o);
        Assert.assertEquals("String", o);
    }

    @Test
    public void test_untrimmed_string() throws Throwable {
        data = new MethodData(method, method.getReturnType(), "' String '");
        Object o = new SimpleConstructor().construct(data);
        Assert.assertEquals(new String(" String "), o);
        Assert.assertEquals(" String ", o);
    }
}

interface SimpleConstructorStringTestTarget {
    String getString();
}