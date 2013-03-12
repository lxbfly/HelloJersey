package com.sample.hello.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Staff {
    private String name;
    private Integer age;
    private String career;
    
    public Staff() {
        
    }
    
    public Staff(String name, Integer age, String career) {
        this.name = name;
        this.age = age;
        this.career = career;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getCareer() {
        return career;
    }
    
    public void setCareer(String career) {
        this.career = career;
    }

    @Override
    public String toString() {
        return "Staff [name=" + name + ", age=" + age + ", career=" + career + "]";
    }
}
