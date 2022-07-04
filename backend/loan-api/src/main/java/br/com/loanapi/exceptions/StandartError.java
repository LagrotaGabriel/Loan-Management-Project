package br.com.loanapi.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StandartError {

    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;

}
