INSERT INTO User VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a10', 'ob123', 'PBKDF2WithHmacSHA256:2048:jJRlCSnfOfrRUEBVHLZU2OzbWaeScvDc2ejmoq8VDyA=:Y4t8WJNCA4kF745wLARsNhrwX2BULR2qTLpGOfNuXhM=', 'Olivier', 'Bruce', '+37060012345', 'M', parsedatetime('2019-01-02 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-03 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO User VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a11', 'js456', 'PBKDF2WithHmacSHA256:2048:Dvy9IbwwIeaTPTYQXb64hIgAQ0QTqBUFJbptym5cAbo=:8eduhnKs3K6g9o2GxMtqCiOlGBbVK4bhx/w9uNfaTZw=', 'John', 'Snow', '+37060012341', 'M', parsedatetime('2019-02-03 15:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO user_roles VALUES('aafdb294-92f1-469c-80fd-270a1e959601', 'ob123', 'USER', parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO user_roles VALUES('aafdb294-92f1-469c-80fd-270a1e959603', 'ob123', 'ADMIN', parsedatetime('2019-03-16 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-16 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO user_roles VALUES('aafdb294-92f1-469c-80fd-270a1e959602', 'js456', 'USER', parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a12', '1000001', 4000, 'EUR', 'bed6109f-ef8a-47ec-8fa4-e57c71415a10', parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-04 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), 0);
INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a13', '1000002', 2000, 'USD', 'bed6109f-ef8a-47ec-8fa4-e57c71415a10', parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-05 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), 0);
INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a14', '1000003', 1000, 'EUR', 'bed6109f-ef8a-47ec-8fa4-e57c71415a11', parsedatetime('2019-01-05 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-06 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'), 0);

INSERT INTO Transaction VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a16', '1000001', '1000002', 30, parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Transaction VALUES ('58fdb294-92f1-469c-80fd-270a1e9596df', '1000001', '1000003', 15, parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
