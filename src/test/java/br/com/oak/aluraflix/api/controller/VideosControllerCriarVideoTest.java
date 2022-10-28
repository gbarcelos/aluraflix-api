package br.com.oak.aluraflix.api.controller;

import br.com.oak.aluraflix.api.model.dto.VideoDto;
import br.com.oak.aluraflix.api.model.input.VideoInput;
import br.com.oak.aluraflix.api.service.VideoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class VideosControllerCriarVideoTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String VIDEOS_URL = "/v1/videos";

    @InjectMocks
    private VideosController videosController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    private Gson gson;

    private VideoInput videoInput;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        gson = getGsonBuilder();

        videoInput =
                VideoInput.builder()
                        .titulo("titulo")
                        .descricao("descricao")
                        .url("url")
                        .build();
    }

    @Test
    public void testCriarVideo_cenarioDeSucesso() throws Exception {
        String request = gson.toJson(videoInput);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(VIDEOS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request);

        VideoDto videoDto =
                VideoDto.builder()
                        .id(1L)
                        .titulo("titulo")
                        .descricao("descricao")
                        .url("url")
                        .build();

        when(videoService.inserir(any())).thenReturn(videoDto);

        mockMvc
                .perform(builder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response").exists())
                .andExpect(jsonPath("$.response.id").isNumber())
                .andExpect(jsonPath("$.response.titulo").value("titulo"))
                .andExpect(jsonPath("$.response.descricao").value("descricao"))
                .andExpect(jsonPath("$.response.url").value("url"));
    }

    private Gson getGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(
                        LocalDate.class,
                        (JsonSerializer<LocalDate>)
                                (json, type, JsonSerializationContext) ->
                                        new JsonPrimitive(DATE_FORMATTER.format(json)))
                .create();
    }
}
