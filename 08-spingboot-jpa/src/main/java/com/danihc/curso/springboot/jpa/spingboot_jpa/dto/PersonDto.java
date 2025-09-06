package com.danihc.curso.springboot.jpa.spingboot_jpa.dto;

public class PersonDto {

    private String name;
    private String lastname;


    public PersonDto(String lastname, String name) {
        this.lastname = lastname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("name=").append(name);
        sb.append(", lastname=").append(lastname);
        sb.append('}');
        return sb.toString();
    }


}
