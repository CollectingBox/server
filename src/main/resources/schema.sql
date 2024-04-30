-- collecting_box 테이블의 외래 키 제약 조건을 삭제합니다.
ALTER TABLE collecting_box DROP FOREIGN KEY FKsuc8jedi2qm3kwaw3ptm1jiw3;
-- review 테이블의 외래 키 제약 조건을 삭제합니다.
ALTER TABLE review DROP FOREIGN KEY FKdituvqscjan1gv6o3fpx1duay;

-- collecting_box, location, review 테이블을 삭제합니다. 존재하는 경우에만 삭제됩니다.
DROP TABLE IF EXISTS collecting_box;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS review;

-- collecting_box 테이블을 생성합니다.
CREATE TABLE collecting_box
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    location_id BIGINT,
    tag         ENUM ('CLOTHES','LAMP_BATTERY','MEDICINE','TRASH') NOT NULL,
    created_at  DATETIME(6),
    updated_at  DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- location 테이블을 생성합니다.
CREATE TABLE location
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255),
    point      POINT SRID 4326,
    sido       VARCHAR(255),
    sigungu    VARCHAR(255),
    dong       VARCHAR(255),
    road_name  VARCHAR(255),
    street_num VARCHAR(255),
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FULLTEXT INDEX ft_sigungu_idx (sigungu) WITH PARSER ngram,
    FULLTEXT INDEX ft_dong_idx (dong) WITH PARSER ngram,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- review 테이블을 생성합니다.
CREATE TABLE review
(
    id                BIGINT NOT NULL AUTO_INCREMENT,
    collecting_box_id BIGINT,
    tag               ENUM ('EXIST','DISAPPEAR') NOT NULL,
    created_at        DATETIME(6),
    updated_at        DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- collecting_box 테이블에 location_id에 대한 유니크 제약 조건과 외래 키 제약 조건을 추가합니다.
ALTER TABLE collecting_box
    ADD CONSTRAINT UK_bkm6f6gxm52ixb4yl2k6ef900 UNIQUE (location_id);
ALTER TABLE collecting_box
    ADD CONSTRAINT FKsuc8jedi2qm3kwaw3ptm1jiw3 FOREIGN KEY (location_id) REFERENCES location (id);

-- review 테이블에 collecting_box_id에 대한 외래 키 제약 조건을 추가합니다.
ALTER TABLE review
    ADD CONSTRAINT FKdituvqscjan1gv6o3fpx1duay FOREIGN KEY (collecting_box_id) REFERENCES collecting_box (id);
