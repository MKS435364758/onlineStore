package com.onlineShopping.Web.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class RoleUpdateRequest {


    @NonNull
    private String username;

    @NonNull
    private String email;

    private String id;

}
