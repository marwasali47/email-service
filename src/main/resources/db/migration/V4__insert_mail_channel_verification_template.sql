INSERT INTO mail_template (id, name, language_code, subject, body) VALUES (13, 'Channel Verification Code', 'en', '[HubMe] Channel verification code', 'Hello %email%,<br/><br/>To complete adding <b>%channelName%</b> channel copy and paste the following verification code into <br/>your browser: %verificationCode%');
INSERT INTO mail_template (id, name, language_code, subject, body) VALUES (14, 'Channel Verification Code', 'fr', '[HubMe] Channel verification code', 'Hello %email%,<br/><br/>To complete adding <b>%channelName%</b> channel copy and paste the following verification code into <br/>your browser: %verificationCode%');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (19, 13, '%email%', 'User email', 'john.smih@domain.com');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (20, 13, '%channelName%', 'Channel name', 'Plazza');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (21, 13, '%verificationCode%', 'Verification code', 'generated verification code');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (22, 14, '%email%', 'User email', 'john.smih@domain.com');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (23, 14, '%channelName%', 'Channel name', 'Plazza');
INSERT INTO mail_template_variable (id, mail_template_id, name, description, example) VALUES (24, 14, '%verificationCode%', 'Verifcation code', 'generated verification code');