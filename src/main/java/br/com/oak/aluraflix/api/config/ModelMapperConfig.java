package br.com.oak.aluraflix.api.config;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .createTypeMap(Video.class, Video.class)
                .addMappings(
                        mapper -> {
                            mapper.skip(Video::setId);
                        });

        modelMapper
                .createTypeMap(VideoInput.class, Video.class)
                .addMappings(mapper -> mapper.skip(Video::setId));

        return modelMapper;
    }
}
