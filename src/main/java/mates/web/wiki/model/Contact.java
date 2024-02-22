package mates.web.wiki.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Por favor, complete este campo.")
  @Size(max = 100, message = "No puede exceder los 100 caracteres")
  private String name;

  @NotBlank(message = "Por favor, complete este campo.")
  @Size(max = 100, message = "No puede exceder los 100 caracteres")
  private String lastName;

  @NotBlank(message = "Por favor, complete este campo.")
  @Email(message = "Email is not valid")
  @Size(max = 100, message = "No puede exceder los 100 caracteres")
  private String email;

  @NotNull(message = "Por favor, complete este campo.")
  @Min(value = 1, message = "Debe ingresar un semestre entre 1 y 16.")
  @Max(value = 16, message = "Debe ingresar un semestre entre 1 y 16.")
  private int semester;

  @NotBlank(message = "Por favor, complete este campo.")
  private String message;
}
