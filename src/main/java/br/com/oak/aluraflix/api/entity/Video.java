package br.com.oak.aluraflix.api.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Video {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  private String descricao;

  private String url;

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;
}
