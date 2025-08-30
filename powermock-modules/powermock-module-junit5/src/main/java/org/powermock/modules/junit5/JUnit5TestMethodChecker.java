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

/**
 * JUnit 5 test method checker that identifies methods that should be treated as test methods.
 */
public class JUnit5TestMethodChecker {
    private final Class<?> testClass;
    private final Method potentialTestMethod;

    public JUnit5TestMethodChecker(Class<?> testClass, Method potentialTestMethod) {
        this.testClass = testClass;
        this.potentialTestMethod = potentialTestMethod;
    }

    public boolean isTestMethod() {
        return isJUnit5TestMethod();
    }

    protected boolean isJUnit5TestMethod() {
        return potentialTestMethod.isAnnotationPresent(Test.class) ||
               potentialTestMethod.isAnnotationPresent(TestFactory.class) ||
               potentialTestMethod.isAnnotationPresent(TestTemplate.class) ||
               potentialTestMethod.isAnnotationPresent(RepeatedTest.class) ||
               potentialTestMethod.isAnnotationPresent(ParameterizedTest.class);
    }
}