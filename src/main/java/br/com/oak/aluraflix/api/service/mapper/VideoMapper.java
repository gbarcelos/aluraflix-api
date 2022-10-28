package br.com.oak.aluraflix.api.service.mapper;

import br.com.oak.aluraflix.api.entity.Video;
import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoMapper {

    private final ModelMapper modelMapper;

    public Video map(VideoInput source) {
        return modelMapper.map(source, Video.class);
    }

    public VideoDto unmap(Video source) {
        return modelMapper.map(source, VideoDto.class);
    }

    public List<VideoDto> unmap(Iterable<Video> videos) {

        List<VideoDto> list = new ArrayList<>();

        videos.forEach(
                lan -> {
                    list.add(unmap(lan));
                });

        return list;
    }
}
