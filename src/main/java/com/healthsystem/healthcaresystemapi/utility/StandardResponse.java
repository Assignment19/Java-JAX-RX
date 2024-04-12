package com.healthsystem.healthcaresystemapi.utility;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse<T> {
    public int statusCode;
    public String message;
    public T data;
    public boolean isSuccess;
    
    
}
