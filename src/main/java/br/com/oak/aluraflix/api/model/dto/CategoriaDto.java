package br.com.oak.aluraflix.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CategoriaDto {

  @ApiModelProperty(example = "452163", position = 1)
  private Long id;

  @ApiModelProperty(example = "Título da categoria", position = 2)
  private String titulo;

  @ApiModelProperty(example = "#3c3c3c", position = 3)
  private String cor;
}
