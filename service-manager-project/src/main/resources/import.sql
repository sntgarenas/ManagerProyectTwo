INSERT INTO project (project_name, project_identifier, description) VALUES('test', '123', 'hola');

INSERT INTO backlog (project_identifier, project_id) VALUES('hola', 1);

INSERT INTO projecttask (name, summary, acceptance_criteria, status, priority, hours, start_date, end_date, project_identifier, backlog_id) VALUES('tarea 1', 'nada hecho', 'crear tarea', 'In progress', 2, 4, NOW(), NOW(), '123', 1);