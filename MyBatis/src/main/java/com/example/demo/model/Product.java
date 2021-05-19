package com.example.demo.model;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private Long prodId;

    @NonNull
    private String prodName;

    @NonNull
    private Integer prodPrice;

}
