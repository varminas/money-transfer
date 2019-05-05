INSERT INTO User VALUES (1, 'Vytautas', 'Arminas', parsedatetime('2019-01-02 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-03 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO User VALUES (2, 'John', 'Snow', parsedatetime('2019-02-03 15:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Account VALUES (1, '1000001', 4000, 'EUR', 1, parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-04 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Account VALUES (2, '1000002', 2000, 'USD', 1, parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-05 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Account VALUES (3, '1000003', 1000, 'EUR', 2, parsedatetime('2019-01-05 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-06 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Transaction VALUES (1, 1, 2, 30, parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Transaction VALUES (1, 1, 3, 30, parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
