package br.com.alura.funcionarios.api.config;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean(name = { "modelMapper" })
	public ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.addConverter(new AbstractConverter<String, LocalDate>() {

			@Override
			protected LocalDate convert(String source) {
				LocalDate date = null;
				try {
					date = LocalDate.parse(source);
				}
				catch (DateTimeParseException e) {
				}

				return date;
			}

		});

		return modelMapper;
	}
}
