package com.ups.oop.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter


public class ClientDTO extends PersonDTO{
        private String id;
        private String ClientCode;
        private String name;
        private String lastaname;
}
