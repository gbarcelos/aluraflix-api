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
public class CategoriaInput {

  @ApiModelProperty(example = "Título da categoria", position = 1)
  @NotBlank
  private String titulo;

  @ApiModelProperty(example = "#3c3c3c", position = 2)
  @NotBlank
  private String cor;
}
