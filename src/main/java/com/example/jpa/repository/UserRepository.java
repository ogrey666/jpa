package com.example.jpa.repository;

import com.example.jpa.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    // JPQL
    @Query("SELECT u FROM User u WHERE u.status = 1")
    Collection<User> findAllActiveUsersUsingJpql();

    // Native query
    @Query(value = "SELECT u.* FROM app_user u WHERE u.status = 1", nativeQuery = true)
    Collection<User> findAllActiveUsersUsingNativeQuery();

    // JPQL + 1 Param (Native query analogicznie)
    @Query("SELECT u FROM User u WHERE u.status = ?1")
    User findUserByStatus(Integer status);

    // JPQL + N Params (Native query analogicznie)
    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
    User findUserByStatusAndName(Integer status, String name);

    // JPQL + Named Params (RECOMMENDED!) (Native query analogicznie)
    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByStatusAdNameNamedParams(
            @Param("status") Integer status,
            @Param("name") String name
    );

    // Native query + Named Params (RECOMMENDED!)
    @Query(value = "SELECT u.* FROM app_user u WHERE u.status = :status and u.name = :name", nativeQuery = true)
    User findUserByStatusAdNameNamedParamsNative(
            @Param("status") Integer status,
            @Param("name") String name
    );

    @Query(value = "SELECT u FROM User u WHERE u.name IN :names")
    List<User> findUserByNameList(
            @Param("names") Collection<String> names
    );

    @Modifying // daje info Hibernate, ze ma odswiezyc cache po update (flush & clear)
    @Query(value = "update app_user set status = ? where name = ?",
            nativeQuery = true)
    int updateUserSetStatusForNameNative(Integer status, String name);

    @Modifying
    @Query(value = "insert into app_user (name, age, email, status) values (:name, :age, :email, :status)",
            nativeQuery = true)
    void insertUser(@Param("name") String name, @Param("age") Integer age, @Param("status") Integer status, @Param("email") String email);

    @Query("from User u where u.contactDate BETWEEN :startDate AND :endDate")
    Collection<User> findDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
