package com.orange.email.repository;


import com.orange.email.entities.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTemplateRepository extends JpaRepository<MailTemplate, String> {

    MailTemplate findByNameAndLanguageCode(String name, String languageCode);

}
