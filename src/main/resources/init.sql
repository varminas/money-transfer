INSERT INTO User VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a10', 'Vytautas', 'Arminas', '+37060012345', 'M', parsedatetime('2019-01-02 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-03 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO User VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a11', 'John', 'Snow', '+37060012341', 'M', parsedatetime('2019-02-03 15:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-03-15 04:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a12', '1000001', 4000, 'EUR', 'bed6109f-ef8a-47ec-8fa4-e57c71415a10', parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-04 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a13', '1000002', 2000, 'USD', 'bed6109f-ef8a-47ec-8fa4-e57c71415a10', parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-05 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Account VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a14', '1000003', 1000, 'EUR', 'bed6109f-ef8a-47ec-8fa4-e57c71415a11', parsedatetime('2019-01-05 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'), parsedatetime('2019-01-06 02:03:04.69', 'yyyy-MM-dd hh:mm:ss.SS'));

INSERT INTO Transaction VALUES ('bed6109f-ef8a-47ec-8fa4-e57c71415a16', 'bed6109f-ef8a-47ec-8fa4-e57c71415a12', 'bed6109f-ef8a-47ec-8fa4-e57c71415a13', 30, parsedatetime('2019-01-03 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
INSERT INTO Transaction VALUES ('58fdb294-92f1-469c-80fd-270a1e9596df', 'bed6109f-ef8a-47ec-8fa4-e57c71415a12', 'bed6109f-ef8a-47ec-8fa4-e57c71415a14', 15, parsedatetime('2019-01-04 20:04:35.69', 'yyyy-MM-dd hh:mm:ss.SS'));
