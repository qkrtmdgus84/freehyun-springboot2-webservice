package com.freehyun.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// ibatis 나 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자, JPA 에선 Repository 라고 부르며 인터페이스로 생성
// @Repository 를 추가할 필요없고, Entity 클래스와 기본 Entity Repository 는 함께 위치
public interface PostsRepository extends JpaRepository<Posts, Long> { //JpaRepository<Entity 클래스, PK 타입>

}
