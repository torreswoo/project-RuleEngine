INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(1, '2018-01-03 10:11:37', 10021); -- 10021이 계좌개셜
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(2, '2018-01-04 10:11:37', 10022); -- 10022이 계좌개셜
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(3, '2018-01-04 11:12:50', 10023); -- 10023이 계좌개셜
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(4, '2018-01-06 10:11:37', 10024); -- 10024이 계좌개셜
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(5, '2018-01-06 18:12:50', 10023); -- 10023이 1,000,000원을 카카오계좌로 충전
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(6, '2018-01-07 11:12:50', 10023); -- 10023가 -> 10021에게 20,000원을 송금
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(7, '2018-01-07 11:12:51', 10021); -- 10023가 -> 10021에게 20,000원을 받기
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(8, '2018-01-07 12:12:50', 10024); -- 10024가 200,000원을 카카오계좌로 충전

-- 10023 -> 10021이 계좌개설하고 7일이내 10만원금액을 5번 받음
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(9,  '2018-01-07 12:12:50', 10023);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(10, '2018-01-07 12:12:51', 10021);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(11, '2018-01-07 13:12:50', 10023);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(12, '2018-01-07 13:12:51', 10021);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(13, '2018-01-08 14:12:50', 10023);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(14, '2018-01-08 14:12:51', 10021);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(15, '2018-01-08 15:12:50', 10023);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(16, '2018-01-08 15:12:51', 10021);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(17, '2018-01-08 16:12:50', 10023);
INSERT INTO USER_ACTION_LOG(id, action_time, user_id) VALUES(18, '2018-01-08 16:12:51', 10021);


-- 계좌계설 --------------------------------------------------------------------
INSERT INTO SERVICE_ACCOUNT_LOG(user_action_log_id, account_number) VALUES (1, '100-111-000');
INSERT INTO SERVICE_ACCOUNT_LOG(user_action_log_id, account_number) VALUES (2, '200-111-000');
INSERT INTO SERVICE_ACCOUNT_LOG(user_action_log_id, account_number) VALUES (3, '300-111-000');
INSERT INTO SERVICE_ACCOUNT_LOG(user_action_log_id, account_number) VALUES (4, '400-111-000');

-- 송금 & 받기 -----------------------------------------------------------------
-- 10023가 -> 10021에게 20,000원을 송금
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money)
    VALUES(6, '300-111-000', 1000000, '100-111-000', 10021, 20000);
-- 10021가 <- 10023에게 20,000원을 받음
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)
    VALUES(7, '100-111-000', 0, '300-111-000', 10023, 20000);
-- 10023 -> 10021이 계좌개설하고 7일이내 10만원금액을 5번 받음
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money) VALUES(9,  '300-111-000', 980000, '100-111-000', 10021, 100000);
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)     VALUES(10, '100-111-000', 20000,  '300-111-000', 10023, 100000);
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money) VALUES(11, '300-111-000', 880000, '100-111-000', 10021, 100000);
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)     VALUES(12, '100-111-000', 120000, '300-111-000', 10023, 100000);
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money) VALUES(13, '300-111-000', 780000, '100-111-000', 10021, 100000);
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)     VALUES(14, '100-111-000', 220000, '300-111-000', 10023, 100000);
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money) VALUES(15, '300-111-000', 680000, '100-111-000', 10021, 100000);
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)     VALUES(16, '100-111-000', 320000, '300-111-000', 10023, 100000);
INSERT INTO TRANSFER_LOG(user_action_log_id, transfer_account_number, balance_before_transfer, receiving_account_number, receiving_user_id, transter_money) VALUES(17, '300-111-000', 580000, '100-111-000', 10021, 100000);
INSERT INTO MONEY_RECEIVING_LOG(user_action_log_id, account_number, balance_before_receiving, sending_account_number, sending_user_id, receiving_money)     VALUES(18, '100-111-000', 420000, '300-111-000', 10023, 100000);


-- 충전 ------------------------------------------------------------------------
-- 10023가 1,000,000원을 카카오계좌로 충전
INSERT INTO MONEY_CHARGING_LOG(user_action_log_id, account_number, charging_money, bank_account_number)
    VALUES(3, '300-111-000', 1000000, '300-999-000');
-- 10024가 200,000원을 카카오계좌로 충전
INSERT INTO MONEY_CHARGING_LOG(user_action_log_id, account_number, charging_money, bank_account_number)
    VALUES(8, '400-111-000', 200000, '400-999-000');

