package uz.pdp.springbootdemo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse {
    private String message;
    private boolean isSuccess;
    private Object data;
}
