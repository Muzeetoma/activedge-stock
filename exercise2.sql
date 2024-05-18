--Answer to exercise two using postgres
SELECT id, name, age FROM T1
EXCEPT
SELECT id, name, age FROM T2
