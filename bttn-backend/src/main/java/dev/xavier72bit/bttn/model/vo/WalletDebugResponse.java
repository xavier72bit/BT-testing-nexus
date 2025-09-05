package dev.xavier72bit.bttn.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h2>DEV NOTE</h2>
 * <p><b>record vs Lombok 使用范围</b></p>
 * <h3>1. record（Java 16+ 引入）</h3>
 * <ul>
 *   <li><b>特点：</b> 不可变对象（字段 final），自动生成构造器、getter、equals、hashCode、toString。</li>
 *   <li><b>适用场景：</b> DTO、API 响应体、Value Object（只承载数据，不修改）。</li>
 *   <li><b>优点：</b> 语法简洁、线程安全、无额外依赖。</li>
 *   <li><b>限制：</b> 字段不可变（无 setter），不支持继承。</li>
 * </ul>
 *
 * <h3>2. Lombok</h3>
 * <ul>
 *   <li><b>特点：</b> 通过注解（@Data, @Builder, @Slf4j 等）减少样板代码，可生成 setter。</li>
 *   <li><b>适用场景：</b> 可变对象（Entity、POJO）、需要 builder、日志的业务类。</li>
 *   <li><b>优点：</b> 灵活，ORM 场景（JPA/MyBatis）更方便。</li>
 *   <li><b>限制：</b> 编译期注解处理，需要额外依赖。</li>
 * </ul>
 * <h3>建议</h3>
 * <ul>
 *   <li><b>API 请求/响应对象：</b> 使用 <code>record</code></li>
 *   <li><b>数据库实体/可变业务对象：</b> 使用 <code>Lombok</code></li>
 * </ul>
 */
public record WalletDebugResponse(
        Data data,
        String message,
        Boolean success
) {
    public record Data(
            @JsonProperty("error_type")
            int errorType,
            String message,
            boolean success
    ) {}
}
