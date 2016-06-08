package cz.cvut.fel.stankmic.wa2.frontend.config;

import cz.cvut.fel.stankmic.wa2.data.config.DataConfig;
import cz.cvut.fel.stankmic.wa2.rabbit.config.RabbitConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(
        basePackages = {"cz.cvut.fel.stankmic.wa2.frontend.controller"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)}
)
@Import({DataConfig.class, RabbitConfig.class})
public class RootConfig {
}
