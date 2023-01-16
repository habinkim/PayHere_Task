create table if not exists HouseHold.User
(
    email    varchar(30)  not null comment '사용자 이메일'
    primary key,
    insDtm   datetime(6)  null comment '등록 일시',
    updDtm   datetime(6)  null comment '수정 일시',
    nickname varchar(20)  not null comment '사용자 닉네임',
    password varchar(100) not null comment '사용자 패스워드'
    );

create table if not exists HouseHold.HouseHold
(
    houseHoldId bigint auto_increment comment '가계부 내역 ID'
    primary key,
    insDtm      datetime(6)  null comment '등록 일시',
    updDtm      datetime(6)  null comment '수정 일시',
    insUserId   varchar(255) null comment '등록자',
    updUserId   varchar(255) null comment '수정자',
    amount      int          not null comment '금액',
    category    varchar(30)  not null comment '카테고리',
    isDeleted   bit          not null comment '삭제 여부',
    memo        varchar(500) null comment '메모',
    payment     varchar(30)  not null comment '결제 수단',
    userEmail   varchar(30)  null,
    constraint FKds78lbr0cg3ngbldyvo626scb
    foreign key (userEmail) references HouseHold.User (email)
    );

