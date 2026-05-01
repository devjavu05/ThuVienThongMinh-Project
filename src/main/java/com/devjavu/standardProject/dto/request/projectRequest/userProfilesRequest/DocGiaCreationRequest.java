package com.devjavu.standardProject.dto.request.projectRequest.userProfilesRequest;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocGiaCreationRequest {
    String username;
    String password;
    String email;
    @JsonAlias({"fullname", "fullName"})
    String fullName;
}
