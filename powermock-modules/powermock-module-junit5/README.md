# PowerMock JUnit 5 (Jupiter) Support

This module provides PowerMock integration with JUnit 5 (Jupiter) testing framework.

## Usage

### Basic Usage

To use PowerMock with JUnit 5, add the PowerMock JUnit 5 extension to your test class:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powermock.modules.junit5.PowerMockExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.*;

@ExtendWith(PowerMockExtension.class)
@PrepareForTest({StaticClass.class})
public class MyJUnit5Test {

    @Test
    public void testStaticMethod() {
        // Mock static method
        mockStatic(StaticClass.class);
        when(StaticClass.staticMethod()).thenReturn("mocked");
        
        // Test your code
        assertEquals("mocked", StaticClass.staticMethod());
        
        // Verify
        verifyStatic(StaticClass.class);
        StaticClass.staticMethod();
    }
}
```

### Dependencies

Add the following dependencies to your project:

#### Maven
```xml
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit5</artifactId>
    <version>2.0.10</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito2</artifactId>
    <version>2.0.10</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.3</version>
    <scope>test</scope>
</dependency>
```

#### Gradle
```gradle
testImplementation 'org.powermock:powermock-module-junit5:2.0.10'
testImplementation 'org.powermock:powermock-api-mockito2:2.0.10'
testImplementation 'org.junit.jupiter:junit-jupiter:5.9.3'
```

### Supported JUnit 5 Features

The PowerMock JUnit 5 extension supports:
- `@Test` annotated test methods
- `@TestFactory` for dynamic tests
- `@RepeatedTest` for repeated executions
- `@ParameterizedTest` for parameterized tests (requires proper configuration)
- `@TestTemplate` for templated tests (requires proper configuration)

### Migration from JUnit 4

When migrating from JUnit 4 to JUnit 5 with PowerMock:

1. Replace `@RunWith(PowerMockRunner.class)` with `@ExtendWith(PowerMockExtension.class)`
2. Update JUnit imports from `org.junit.*` to `org.junit.jupiter.api.*`
3. Replace assertion methods:
   - `Assert.assertEquals()` → `Assertions.assertEquals()`
   - `Assert.assertTrue()` → `Assertions.assertTrue()`
   - etc.

### Example Migration

**JUnit 4:**
```java
@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticClass.class})
public class MyTest {
    @Test
    public void test() {
        Assert.assertEquals("expected", actual);
    }
}
```

**JUnit 5:**
```java
@ExtendWith(PowerMockExtension.class)
@PrepareForTest({StaticClass.class})
public class MyTest {
    @Test
    public void test() {
        Assertions.assertEquals("expected", actual);
    }
}
```

### Notes

- This module requires Java 8 or later
- Compatible with JUnit 5.9.3 and later versions
- Works with Mockito 5.18.0 and later versions
- All existing PowerMock annotations (`@PrepareForTest`, etc.) work the same way

## Implementation Details

The JUnit 5 support is implemented through:
- `PowerMockExtension`: Main JUnit 5 extension providing PowerMock integration
- `JUnit5TestMethodChecker`: Identifies JUnit 5 test methods for processing
- `PowerMockRuleTestSuiteChunker`: Handles test suite chunking for PowerMock

For more information, see the PowerMock documentation and examples.