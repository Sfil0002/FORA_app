package com.example.foraapp;

import androidx.annotation.NonNull;

public class Animal
{
    private String animal_name;
    private String file_number;
    private String breed;
    private String sex;
    private int approx_age;
    private String photo_url;
    private String description;
    private String builtQRCode;

    public Animal() {
    }

    public Animal(String animal_name, String file_number, String breed, String sex, int approx_age, String builtQRCode)
    {
        this.animal_name = animal_name;
        this.file_number = file_number;
        this.breed = breed;
        this.sex = sex;
        this.approx_age = approx_age;
        this.builtQRCode = qrCodeGen();
    }

    public Animal(String animal_name, String file_number, String breed, String sex, int approx_age, String photo_url, String description)
    {
        this.animal_name = animal_name;
        this.file_number = file_number;
        this.breed = breed;
        this.sex = sex;
        this.approx_age = approx_age;
        this.builtQRCode = qrCodeGen();
        this.photo_url = photo_url;
        this.description = description;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getFile_number() {
        return file_number;
    }

    public void setFile_number(String file_number) {
        this.file_number = file_number;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getApprox_age() {
        return approx_age;
    }

    public void setApprox_age(int approx_age) {
        this.approx_age = approx_age;
    }

    public String getBuiltQRCode() {
        return builtQRCode;
    }

    public void setBuiltQRCode(String builtQRCode) {
        this.builtQRCode = builtQRCode;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "Name: " + animal_name + '\n' +
                "File number: " + file_number + '\n' +
                "Breed: " + breed + '\n' +
                "Sex: " + sex + '\n' +
                "Approximate age: " + approx_age +'\n'+
                "Photo URL: " + photo_url + '\n' +
                "Description: " + description + '\n';


    }
    public String toStringShort()
    {
        return "Name: " + animal_name + '\n' +
                "Breed: " + breed + '\n' +
                "Sex: " + sex + '\n' +
                "Approximate age: " + approx_age +'\n';
    }
    public String toLoadImage()
    {
        return photo_url;
    }

    public String qrCodeGen()
    {
        builtQRCode = animal_name + file_number;
        return builtQRCode;
    }


}

