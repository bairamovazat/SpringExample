INSERT INTO owner(id, email, name, login, hash_password, role)
  SELECT 1, 'REDACTED', 'Администратор', 'admin', 'REDACTED', 'ADMIN'
  WHERE
    NOT EXISTS(
        SELECT id
        FROM owner
        WHERE id = 1
    );