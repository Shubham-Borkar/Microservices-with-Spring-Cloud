package com.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Alternate to @Value to declare various properties we can use this
@ConfigurationProperties(prefix="company-info")
@Component
@Getter
@Setter
@ToString
public class ConfigClass {
	private String name;
	private String location;

}
