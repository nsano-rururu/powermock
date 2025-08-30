/*
 *   Copyright 2016 the original author or authors.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.powermock.modules.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for JUnit5TestMethodChecker.
 */
public class JUnit5TestMethodCheckerTest {

    @Test
    public void testRecognizesTestAnnotation() throws NoSuchMethodException {
        Method testMethod = TestClass.class.getMethod("testMethod");
        JUnit5TestMethodChecker checker = new JUnit5TestMethodChecker(TestClass.class, testMethod);
        assertTrue(checker.isTestMethod());
    }

    @Test
    public void testRecognizesTestFactoryAnnotation() throws NoSuchMethodException {
        Method testMethod = TestClass.class.getMethod("testFactoryMethod");
        JUnit5TestMethodChecker checker = new JUnit5TestMethodChecker(TestClass.class, testMethod);
        assertTrue(checker.isTestMethod());
    }

    @Test
    public void testRecognizesRepeatedTestAnnotation() throws NoSuchMethodException {
        Method testMethod = TestClass.class.getMethod("repeatedTestMethod");
        JUnit5TestMethodChecker checker = new JUnit5TestMethodChecker(TestClass.class, testMethod);
        assertTrue(checker.isTestMethod());
    }

    @Test
    public void testDoesNotRecognizeNonTestMethod() throws NoSuchMethodException {
        Method nonTestMethod = TestClass.class.getMethod("nonTestMethod");
        JUnit5TestMethodChecker checker = new JUnit5TestMethodChecker(TestClass.class, nonTestMethod);
        assertFalse(checker.isTestMethod());
    }

    /**
     * Test class for method checker tests.
     */
    static class TestClass {
        @Test
        public void testMethod() {}

        @TestFactory
        public void testFactoryMethod() {}

        @RepeatedTest(3)
        public void repeatedTestMethod() {}

        public void nonTestMethod() {}
    }
}