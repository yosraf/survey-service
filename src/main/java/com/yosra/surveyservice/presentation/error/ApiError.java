package com.yosra.surveyservice.presentation.error;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
  private int status;
  private List<String> errors;
  private String message;
  private LocalDateTime date;
}
