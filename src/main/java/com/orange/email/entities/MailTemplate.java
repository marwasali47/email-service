package com.orange.email.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mail_template")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class MailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @Column(name = "language_code")
    private String languageCode;

    private String subject;

    private String body;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy="mailTemplate")
    private List<MailTemplateVariable> variables;


    @Override
    public String toString() {
        return "MailTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", variables=" + variables +
                '}';
    }
}
