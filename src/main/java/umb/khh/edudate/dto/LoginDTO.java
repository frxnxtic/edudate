package umb.khh.edudate.dto;

public record LoginDTO(String username, String password, String email) {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
