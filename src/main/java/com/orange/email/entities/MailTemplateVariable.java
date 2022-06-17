package com.orange.email.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "mail_template_variable")
@Data
@EqualsAndHashCode(of = {"id"})
public class MailTemplateVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String description;

    private String example;

    @ManyToOne
    @JoinColumn(name = "mail_template_id")
    private MailTemplate mailTemplate;

    @Override
    public String toString() {
        return "MailTemplateVariable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
