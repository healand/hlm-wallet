package com.last.lastcoin.exception;

import static org.springframework.http.HttpStatus.OK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    /* 10000 Auth */

    // NOT FOUND 10000
    ACCOUNT_NOT_FOUND(10001, "사용자를 찾을 수 없습니다."),
    EMAIL_NOT_FOUND(10002, "이메일이 존재하지 않습니다."),
    REWARD_CODE_NOT_FOUND(10003, "해당 추천 코드를 가진 계정이 존재하지 않습니다."),
    MASTER_NOT_FOUND(10004, "회사 계정를 찾을 수 없습니다."),

    // 회원가입 10100
    DUPLICATED_MEMBER(10100, "이미 가입되어 있는 회원입니다."),
    SIGN_UP_FAIL(10102, "회원가입에 실패하였습니다."),

    // 인증 10200
    SIGN_IN_FAIL(10201, "아이디와 비밀번호가 일치하지 않습니다."),
    WRONG_PWD_FIVE(10202, "비밀번호 입력이 5회 이상 실패했습니다."),
    UNAUTHORIZED(10203, "권한이 없습니다."),
    INVALID_TOKEN(10204, "유효하지 않은 토큰입니다."),

    ACCOUNT_SUSPENSION(10205, "계정 정지된 사용자입니다."),
    ACCOUNT_LOCK(10206, "계정이 잠긴 사용자입니다."),
    LIMIT_SMS(10207, "하루 전송 가능한 개수를 초과했습니다."),
    SMS_SEND_FAIL(10208, "문자메세지 전송이 실패하였습니다."),
    SMS_WRONG_CODE(10209, "인증코드가 잘못됐습니다."),
    SMS_EXPIRED_CODE(10210, "인증코드가 만료되었습니다."),
    INVALID_PHONE_NUMBER(10211, "전화번호를 확인해주세요."),

    /* 20000 wallet */
    WALLET_CREATE_FAIL(20001, "지갑 생성에 실패하였습니다."),
    WALLET_KEY_FAIL(20002, "Key 인증에 실패하였습니다."),
    WALLET_NOT_FOUND(20003, "지갑을 찾을 수 없습니다."),
    WALLET_ADDRESS_INVALID(20004, "지갑 주소가 잘못되었습니다."),
    NOT_ENOUGH_BALANCE(20005, "잔액이 부족합니다. 수수료 포함한 금액이 잔고에 있어야합니다."),
    SYMBOL_INVALID(20007, "심볼이 상이합니다."),
    WALLET_MASTER_NOT_FOUND(20008, "회사지갑을 찾을 수 없습니다."),
    TRANSFER_INVALID_AMOUNT(20009, "1개 이상의 토큰부터 송금 가능합니다."),
    CREATE_WALLET_FAIL_NON_LOCKUP(20010, "코인지갑에는 락업 설정을 할수 없습니다.."),
    WALLET_CANT_MINUS(20011, "잔액은 음수가 될 수 없습니다."),
    OCTET_NOT_FOUND(21000, "블록체인 정보를 불러오지 못했습니다."),
    TRANSFER_NOT_FOUND(22000, "거래정보를 찾지 못했습니다."),
    TRANSFER_DUPLICATE(22001, "이미 등록된 거래입니다."),
    TRANSFER_DIFFERENT_ADDRESS(22001, "두개의 주소가 상이합니다."),

    WEBHOOK_DEPOSIT_COLLECT(22002, "집금 웹훅입니다."),


    LOCK_UP_BALANCE_ENOUGH(20101, "락업 물량이 부족합니다."),
    LOCKUP_RELEASE_INVALID_COMPANY(20102, "회사 지갑은 락업을 해제할 수 없습니다."),

    /* 50000 Notice, schedules */
    NOTICE_NOT_FOUND(50001, "내용을 찾을 수 없습니다"),
    BANNER_NOT_FOUND(50002, "배너를 찾을 수 없습니다"),

    /* 70000 Push */

    /* 80000 KeyValue */
    KEY_NOT_FOUND(80001, "데이터을 찾을 수 없습니다"),
    KEY_INVALID(80002, "키값이 잘못되었습니다"),

    /* 90000 공통 */
    INTERNAL_ERROR(500, "Internal server error"),
    BAD_REQUEST_ERROR(90400, "요청 데이터가 잘못됐습니다."),
    EXTERNAL_API_DOLLAR_RATE(90501, "환율 정보를 가져오는데 실패했습니다."),
    EXTERNAL_API_DOLLAR_RATE_OVER_COUNT(90502, "환율 정보를 1일 한도 횟수 초과"),
    BAD_REQUEST_DATE_ERROR(90100, "시작날짜가 종료날짜보다 늦습니다.");


    private final int code;
    private final String message;
    private final HttpStatus status = OK;
}
