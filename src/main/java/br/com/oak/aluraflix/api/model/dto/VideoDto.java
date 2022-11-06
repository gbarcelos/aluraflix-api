package br.com.oak.aluraflix.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class VideoDto {

  @ApiModelProperty(example = "452163", position = 1)
  private Long id;

  @ApiModelProperty(example = "Título do vídeo", position = 2)
  private String titulo;

  @ApiModelProperty(example = "Descrição do video", position = 3)
  private String descricao;

  @ApiModelProperty(example = "Url do video", position = 4)
  private String url;

  @ApiModelProperty(example = "Livre", position = 5)
  private String categoria;
}
