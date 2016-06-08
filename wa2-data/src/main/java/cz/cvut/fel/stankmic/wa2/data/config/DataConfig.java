package cz.cvut.fel.stankmic.wa2.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataSourceConfig.class, ServiceConfig.class})
public interface DataConfig {
}
