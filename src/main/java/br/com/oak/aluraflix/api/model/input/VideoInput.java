package br.com.oak.aluraflix.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoInput {

  @ApiModelProperty(example = "Título do vídeo", position = 1, required = true)
  @NotBlank
  private String titulo;

  @ApiModelProperty(example = "Descrição do video", position = 2, required = true)
  @NotBlank
  private String descricao;

  @ApiModelProperty(example = "Url do video", position = 3, required = true)
  @NotBlank
  private String url;

  @ApiModelProperty(example = "1", position = 4)
  private Long categoriaId;
}
