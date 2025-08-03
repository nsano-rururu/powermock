/*
 * Copyright 2008 the original author or authors.
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
package org.powermock.modules.junit5;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.powermock.core.MockRepository;
import org.powermock.tests.utils.MockPolicyInitializer;
import org.powermock.tests.utils.TestChunk;
import org.powermock.tests.utils.TestSuiteChunker;
import org.powermock.tests.utils.impl.MockPolicyInitializerImpl;

import java.lang.reflect.Method;

/**
 * PowerMock JUnit 5 Extension that enables PowerMock functionality for JUnit 5 tests.
 * This extension handles test class setup, mock initialization, and cleanup.
 * 
 * Usage:
 * <pre>
 * &#64;ExtendWith(PowerMockExtension.class)
 * &#64;PrepareForTest({StaticClass.class})
 * public class MyTest {
 *     // test methods
 * }
 * </pre>
 */
public class PowerMockExtension implements BeforeAllCallback, BeforeEachCallback, TestExecutionExceptionHandler {
    
    private static TestSuiteChunker testSuiteChunker;
    private static MockPolicyInitializer mockPolicyInitializer;
    private static Class<?> previousTestClass;
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        final Class<?> testClass = context.getRequiredTestClass();
        
        if (isNotInitialized(testClass)) {
            init(testClass);
        }
    }
    
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        final Method testMethod = context.getRequiredTestMethod();
        final Class<?> testClass = context.getRequiredTestClass();
        
        if (isNotInitialized(testClass)) {
            init(testClass);
        }
        
        // Clear mock repository before each test
        MockRepository.clear();
        
        // Initialize test method specific setup if needed
        final TestChunk testChunk = testSuiteChunker.getTestChunk(testMethod);
        if (testChunk != null) {
            testChunk.getTestMethodAnnotations();
        }
    }
    
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        // Re-throw the exception as-is for now
        // This could be enhanced to handle PowerMock-specific exceptions
        throw throwable;
    }
    
    private boolean isNotInitialized(Class<?> testClass) {
        return testSuiteChunker == null || previousTestClass != testClass;
    }
    
    private void init(Class<?> testClass) {
        previousTestClass = testClass;
        testSuiteChunker = new PowerMockRuleTestSuiteChunker(testClass);
        mockPolicyInitializer = new MockPolicyInitializerImpl(testClass);
    }
}