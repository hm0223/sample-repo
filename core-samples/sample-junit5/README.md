# 🧪 JUnit 5 全面测试示例

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5.9+-green.svg)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)](https://maven.apache.org/)
[![Mockito](https://img.shields.io/badge/Mockito-5.x-blueviolet.svg)](https://site.mockito.org/)

## 📋 项目简介

本项目是一个全面的JUnit 5测试示例，涵盖了从基础测试到高级测试技术的完整实践。通过实际代码示例展示如何编写高效、可维护的单元测试、集成测试和端到端测试。

## 🎯 学习目标

- ✅ 掌握JUnit 5的核心特性和新功能
- ✅ 学习参数化测试和动态测试
- ✅ 理解测试生命周期和扩展模型
- ✅ 掌握Mockito和断言的高级用法
- ✅ 学习测试驱动开发(TDD)的最佳实践

## 🏗️ 项目结构

```
sample-junit5/
├── 📁 src/main/java/com/hm/junit5/
│   ├── 📁 calculator/        # 计算器示例
│   ├── 📁 service/          # 业务服务
│   ├── 📁 repository/       # 数据访问层
│   └── 📁 model/            # 领域模型
├── 📁 src/test/java/com/hm/junit5/
│   ├── 📁 basics/           # 基础测试示例
│   ├── 📁 advanced/         # 高级测试技术
│   ├── 📁 parameterized/    # 参数化测试
│   ├── 📁 integration/      # 集成测试
│   └── 📁 lifecycle/        # 测试生命周期
├── 📁 src/test/resources/     # 测试资源
└── 📄 pom.xml              # Maven配置
```

## 🚀 快速开始

### 📋 前置条件

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.3
- **IDE**: IntelliJ IDEA / Eclipse / VS Code

### ⚙️ 环境配置

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd sample-junit5
   ```

2. **运行测试**
   ```bash
   # 运行所有测试
   mvn test
   
   # 运行特定测试类
   mvn test -Dtest=CalculatorTest
   
   # 运行测试套件
   mvn test -Dtest=AllTests
   
   # 生成测试报告
   mvn surefire-report:report
   ```

### 🏃 验证测试

```bash
# 运行示例测试
mvn test -Dtest=BasicAssertionsTest

# 查看测试报告
open target/surefire-reports/index.html
```

## 📚 核心特性详解

### 🎯 1. JUnit 5基础测试

#### 简单断言测试
```java
@DisplayName("计算器基础测试")
class CalculatorTest {
    
    private Calculator calculator = new Calculator();
    
    @Test
    @DisplayName("测试加法运算")
    void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 应该等于 5");
    }
    
    @Test
    @DisplayName("测试除法异常")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        }, "除零应该抛出异常");
    }
}
```

#### 分组断言
```java
@Test
@DisplayName("测试多个断言")
void testMultipleAssertions() {
    Person person = new Person("Alice", 25);
    
    assertAll("person",
        () -> assertEquals("Alice", person.getName()),
        () -> assertEquals(25, person.getAge()),
        () -> assertTrue(person.isAdult())
    );
}
```

### 🎯 2. 参数化测试

#### 基本参数化测试
```java
@ParameterizedTest
@ValueSource(strings = {"racecar", "radar", "level"})
@DisplayName("测试回文字符串")
void testPalindrome(String word) {
    assertTrue(StringUtils.isPalindrome(word));
}
```

#### CSV源参数化
```java
@ParameterizedTest
@CsvSource({
    "2, 3, 5",
    "10, -5, 5",
    "0, 0, 0",
    "-3, -7, -10"
})
@DisplayName("测试加法运算 - CSV参数")
void testAdditionWithCsv(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

#### 方法源参数化
```java
@ParameterizedTest
@MethodSource("providePersonData")
@DisplayName("测试人员验证 - 方法源")
void testPersonValidation(Person person, boolean expectedValid) {
    assertEquals(expectedValid, person.isValid());
}

private static Stream<Arguments> providePersonData() {
    return Stream.of(
        Arguments.of(new Person("Alice", 25), true),
        Arguments.of(new Person("Bob", 17), false),
        Arguments.of(new Person("", 30), false)
    );
}
```

### 🎯 3. 动态测试

#### 动态测试生成
```java
@TestFactory
@DisplayName("生成动态测试")
Stream<DynamicTest> generateDynamicTests() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    
    return numbers.stream()
        .map(num -> DynamicTest.dynamicTest(
            "测试数字: " + num,
            () -> assertTrue(num > 0)
        ));
}
```

#### 基于文件的动态测试
```java
@TestFactory
@DisplayName("从文件生成测试")
Stream<DynamicTest> generateTestsFromFile() throws IOException {
    return Files.lines(Paths.get("src/test/resources/test-data.txt"))
        .map(line -> DynamicTest.dynamicTest(
            "测试行: " + line,
            () -> assertFalse(line.isBlank())
        ));
}
```

### 🎯 4. 测试生命周期

#### 测试前和测试后
```java
class DatabaseTest {
    
    private Database database;
    
    @BeforeEach
    void setUp() {
        database = new Database();
        database.connect();
    }
    
    @AfterEach
    void tearDown() {
        if (database != null) {
            database.disconnect();
        }
    }
    
    @Test
    void testConnection() {
        assertTrue(database.isConnected());
    }
}
```

#### 嵌套测试
```java
@DisplayName("订单测试")
class OrderTest {
    
    Order order;
    
    @BeforeEach
    void setUp() {
        order = new Order();
    }
    
    @Nested
    @DisplayName("当订单为空时")
    class WhenOrderIsEmpty {
        
        @Test
        @DisplayName("总价应为0")
        void testTotalPrice() {
            assertEquals(0, order.getTotalPrice());
        }
        
        @Test
        @DisplayName("不应包含任何商品")
        void testItemCount() {
            assertTrue(order.getItems().isEmpty());
        }
    }
    
    @Nested
    @DisplayName("当添加商品后")
    class WhenItemIsAdded {
        
        @BeforeEach
        void addItem() {
            order.addItem(new Item("Book", 20.0));
        }
        
        @Test
        @DisplayName("总价应为商品价格")
        void testTotalPrice() {
            assertEquals(20.0, order.getTotalPrice());
        }
    }
}
```

### 🎯 5. Mock测试

#### Mockito集成
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    @DisplayName("测试用户创建")
    void testUserCreation() {
        User user = new User("alice@example.com", "Alice");
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User createdUser = userService.createUser("alice@example.com", "Alice");
        
        assertNotNull(createdUser);
        assertEquals("alice@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
```

#### BDD风格测试
```java
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private NotificationService notificationService;
    
    @Test
    @DisplayName("测试邮件通知发送")
    void testEmailNotification() {
        // Given
        String recipient = "user@example.com";
        String message = "Hello World";
        
        given(emailService.sendEmail(recipient, message)).willReturn(true);
        
        // When
        boolean result = notificationService.sendNotification(recipient, message);
        
        // Then
        assertTrue(result);
        then(emailService).should().sendEmail(recipient, message);
    }
}
```

### 🎯 6. 集成测试

#### Spring Boot测试
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @DisplayName("测试用户API")
    void testUserApi() throws Exception {
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0))))
            .andExpect(jsonPath("$[0].name", notNullValue()));
    }
}
```

#### 测试数据库
```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @DisplayName("测试用户保存")
    void testUserSave() {
        User user = new User("test@example.com", "Test User");
        User savedUser = userRepository.save(user);
        
        assertNotNull(savedUser.getId());
        assertEquals("test@example.com", savedUser.getEmail());
    }
}
```

## 🧪 测试策略

### ✅ 测试金字塔

```
        /\
       /  \    UI Tests (端到端)
      /____\
     /      \  Service Tests (集成)
    /________\
   /          \ Unit Tests (单元)
  /____________\
```

### ✅ 测试覆盖率

使用JaCoCo生成测试覆盖率报告：

```bash
# 运行测试并生成覆盖率报告
mvn clean test jacoco:report

# 查看覆盖率报告
open target/site/jacoco/index.html
```

### ✅ 测试数据管理

#### 测试数据构建器模式
```java
public class UserBuilder {
    private String email = "test@example.com";
    private String name = "Test User";
    private int age = 25;
    
    public static UserBuilder aUser() {
        return new UserBuilder();
    }
    
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public User build() {
        return new User(email, name, age);
    }
}

// 使用示例
User user = UserBuilder.aUser()
    .withEmail("alice@example.com")
    .withName("Alice")
    .build();
```

## 🎓 最佳实践

### ✅ 推荐做法
- **测试命名**: 使用描述性名称，清晰表达测试意图
- **测试结构**: 遵循Given-When-Then模式
- **断言消息**: 提供有意义的断言失败消息
- **测试隔离**: 确保测试之间相互独立
- **边界测试**: 测试边界条件和异常情况
- **性能测试**: 关注测试执行时间

### ❌ 注意事项
- 避免测试之间的依赖关系
- 不要在测试中包含业务逻辑
- 避免过度mock，保持测试的真实性
- 及时清理测试数据
- 不要在测试中硬编码环境特定的值

## 📊 测试报告

### 📈 测试执行统计
运行测试后会生成详细的测试报告：

```bash
# 查看测试摘要
mvn test -q

# 生成HTML报告
mvn surefire-report:report-only

# 查看详细日志
mvn test -X
```

### 📈 测试指标
- **测试总数**: 显示所有执行的测试数量
- **成功率**: 通过的测试百分比
- **执行时间**: 测试套件的总执行时间
- **失败详情**: 详细的失败信息和堆栈跟踪

## 🔧 高级配置

### ⚙️ Maven配置优化
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <includes>
                    <include>**/*Test.java</include>
                    <include>**/*Tests.java</include>
                </includes>
                <excludes>
                    <exclude>**/integration/**</exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### ⚙️ 测试配置
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
```

## 📖 相关资源

- [JUnit 5官方文档](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito文档](https://site.mockito.org/)
- [Spring Boot测试指南](https://spring.io/guides/gs/testing-web/)
- [测试驱动开发(TDD)](https://www.agilealliance.org/glossary/tdd/)

## 🤝 贡献指南

如果您发现了问题或有改进建议，欢迎：
- 提交Issue报告问题
- 创建Pull Request贡献测试用例
- 完善文档和示例
- 分享您的测试最佳实践

## 📄 许可证

本项目采用MIT许可证，详见[LICENSE](../../LICENSE)文件。