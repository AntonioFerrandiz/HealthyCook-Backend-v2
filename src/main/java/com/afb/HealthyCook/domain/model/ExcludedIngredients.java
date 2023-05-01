package com.afb.HealthyCook.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "excluded_ingredients")
public class ExcludedIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excluded_ingredients_id")
    private Integer id;

    @Column(name = "excluded_ingredient")
    private String excludedIngredient;

    // User
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExcludedIngredient() {
        return excludedIngredient;
    }

    public void setExcludedIngredient(String excludedIngredient) {
        this.excludedIngredient = excludedIngredient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExcludedIngredients() {
    }

    public ExcludedIngredients(Integer id, String excludedIngredient, User user) {
        this.id = id;
        this.excludedIngredient = excludedIngredient;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExcludedIngredients{" +
                "id=" + id +
                ", excludedIngredient='" + excludedIngredient + '\'' +
                ", user=" + user +
                '}';
    }
}
