insert into USERS(USER_ID) VALUES (1);
insert into CANDIDATES(CANDIDATE_ID, FIRST_NAME, LAST_NAME, CREATION_TIME, USER_ID) values (1, 'Mykola', 'Kavf', CURDATE(), 1);

insert into PROJECTS
(
  CREATION_TIME,
  NAME,
  DESCRIPTION,
  USER_ID
)
VALUES
  (
    CURDATE(),
    'Test project',
    'Test project name',
    1
  );
insert into TAGS (KEYWORD) VALUES ('Javascript'), ('.NET'), ('Graph processing'), ('Neurobiology');

insert into VACANCIES
(
  CREATION_TIME,
  HOT_FLAG,
  DESCRIPTION,
  STATE,
  PROJECT_ID, USER_ID
)
VALUES
  (
    CURDATE(),
    FALSE,
    'Test vacancy',
    'OPEN',
    1,
    1

  );

insert into VACANCIES_TAGS (VACANCY_ID, TAG_ID) VALUES (1, 1), (1, 2), (1, 3);
insert into FLOWS(CREATION_TIME, ACTIVE_FLAG, VACANCY_ID, CANDIDATE_ID, USER_ID) values (CURDATE(), 1, 1, 1, 1);
commit;