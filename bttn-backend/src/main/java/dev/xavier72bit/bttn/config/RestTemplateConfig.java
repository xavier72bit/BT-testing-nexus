package dev.xavier72bit.bttn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring 配置类，用于创建和配置 RestTemplate Bean。
 * <p>
 * 使用 {@link Configuration @Configuration} 标记这是一个配置类，
 * Spring 容器会扫描并处理其中的 {@link Bean @Bean} 方法。
 * 当其他类需要使用 RestTemplate 时，Spring 会自动注入这里创建的单例实例，
 * 开发者无需手动创建。这使得对 RestTemplate 的自定义（如添加拦截器、设置超时）
 * 变得集中且易于维护。
 */
@Configuration
public class RestTemplateConfig {

    /**
     *
     * 定义一个名为 restTemplate 的 Bean。
     * <p>
     * {@link Bean @Bean} 告诉 Spring 容器这个方法会返回一个由其管理的 RestTemplate 实例。
     * 任何需要 RestTemplate 的地方，Spring 都会自动注入这个 Bean。
     * 这里可以添加自定义配置，例如：
     * <pre>
     * {@code
     * // 使用 RestTemplateBuilder 设置连接超时和读取超时
     * return new RestTemplateBuilder()
     * .setConnectTimeout(Duration.ofSeconds(5))
     * .setReadTimeout(Duration.ofSeconds(5))
     * .build();
     * }
     * </pre>
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
