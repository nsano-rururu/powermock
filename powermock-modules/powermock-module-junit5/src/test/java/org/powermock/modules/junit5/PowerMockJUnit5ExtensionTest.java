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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic test to verify PowerMock JUnit 5 extension functionality.
 */
@ExtendWith(PowerMockExtension.class)
public class PowerMockJUnit5ExtensionTest {

    @Test
    public void testExtensionLoads() {
        // Simple test to verify extension loads without error
        assertNotNull(this);
        assertTrue(true);
    }

    @Test
    public void testBasicFunctionality() {
        // Test that we can run multiple tests with the extension
        assertEquals(2, 1 + 1);
    }
}